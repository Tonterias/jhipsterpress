import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IBlockuser } from 'app/shared/model/blockuser.model';
import { BlockuserService } from './blockuser.service';
import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from '../uprofile/uprofile.service';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-blockuser',
    templateUrl: './blockinguser.component.html'
})
export class BlockinguserComponent implements OnInit, OnDestroy {
    currentAccount: any;
    blockusers: IBlockuser[];
    uprofiles: IUprofile[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
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
    blockedUserId: number;

    constructor(
        protected blockuserService: BlockuserService,
        protected uprofileService: UprofileService,
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
            if (params.blockinguserIdEquals != null) {
                this.nameParamBlockUser = 'blockeduserId.equals';
                this.valueParamBlockUser = params.blockinguserIdEquals;
            }
            if (params.cblockedUserIdEquals != null) {
                this.nameParamBlockUser = 'cblockeduserId.equals';
                this.valueParamBlockUser = params.cblockeduserIdEquals;
            }
        });
    }

    loadAll() {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        query[this.nameParamBlockUser] = this.blockedUserId;
        this.blockuserService
            .query(query)
            .subscribe(
                (res: HttpResponse<IBlockuser[]>) => this.paginateBlockusers(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        console.log('CONSOLOG: M:loadAll & O: this.query : ', query);
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
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/blockuser',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.owner = account.id;
            //            this.accountService.hasAnyAuthority(['ROLE_ADMIN']).then(result => {
            //                this.isAdmin = result;
            this.isAdmin = this.accountService.hasAnyAuthority(['ROLE_ADMIN']);
            const query = {};
            if (this.currentAccount.id != null) {
                query['id.equals'] = this.valueParamBlockUser;
            }
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    this.uprofiles = res.body;
                    console.log('CONSOLOG: M:ngOnInit & O: this.uprofiles : ', this.uprofiles);
                    this.uprofiles.forEach(profile => {
                        this.blockedUserId = profile.userId;
                        console.log('CONSOLOG: M:ngOnInit & O: this.blockingUserId : ', this.blockedUserId);
                        this.loadAll();
                    });
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            //            });
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

    private paginateBlockusers(data: IBlockuser[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.blockusers = data;
        console.log('CONSOLOG: M:paginateBlockusers & O: this.follows : ', this.blockusers);
        console.log('CONSOLOG: M:paginateBlockusers & O: this.follows : ', this.owner);
        console.log('CONSOLOG: M:paginateBlockusers & O: this.follows : ', this.isAdmin);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
