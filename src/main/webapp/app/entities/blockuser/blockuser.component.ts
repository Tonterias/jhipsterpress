import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IBlockuser } from 'app/shared/model/blockuser.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { BlockuserService } from './blockuser.service';

@Component({
    selector: 'jhi-blockuser',
    templateUrl: './blockuser.component.html'
})
export class BlockuserComponent implements OnInit, OnDestroy {
    currentAccount: any;
    blockusers: IBlockuser[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    nameParamBlockUser: any;
    valueParamBlockUser: any;
    owner: any;
    isAdmin: boolean;
    zipZeroResults: any;

    constructor(
        protected blockuserService: BlockuserService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.blockeduserIdEquals != null) {
                this.nameParamBlockUser = 'blockinguserId.equals';
                this.valueParamBlockUser = params.blockeduserIdEquals;
            }
            if (params.cblockinguserIdEquals != null) {
                this.nameParamBlockUser = 'cblockinguserId.equals';
                this.valueParamBlockUser = params.cblockinguserIdEquals;
            }
        });
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.blockuserService
                .query({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IBlockuser[]>) => this.paginateBlockusers(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.blockuserService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IBlockuser[]>) => this.paginateBlockusers(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/blockuser'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate([
            '/blockuser',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.page = 0;
        this.currentSearch = query;
        this.router.navigate([
            '/blockuser',
            {
                search: this.currentSearch,
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.owner = account.id;
            this.isAdmin = this.accountService.hasAnyAuthority(['ROLE_ADMIN']);
        });
        this.registerChangeInBlockusers();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBlockuser) {
        return item.id;
    }

    registerChangeInBlockusers() {
        this.eventSubscriber = this.eventManager.subscribe('blockuserListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateBlockusers(data: IBlockuser[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.blockusers = data;
        console.log('CONSOLOG: M:paginateBlockusers & O: this.blockusers : ', this.blockusers);
        console.log('CONSOLOG: M:paginateBlockusers & O: this.owner : ', this.owner);
        console.log('CONSOLOG: M:paginateBlockusers & O: this.isAdmin : ', this.isAdmin);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
