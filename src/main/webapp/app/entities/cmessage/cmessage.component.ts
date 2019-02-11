import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import * as moment from 'moment';
import { Observable } from 'rxjs';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICmessage } from 'app/shared/model/cmessage.model';
import { CmessageService } from './cmessage.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from '../.././../app/entities/community/community.service';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-cmessage',
    templateUrl: './cmessage.component.html'
})
export class CmessageComponent implements OnInit, OnDestroy {
    currentAccount: any;
    cmessages: ICmessage[];
    communities: ICommunity[];
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
    isSaving: boolean;
    creationDate: string;

    constructor(
        protected cmessageService: CmessageService,
        protected communityService: CommunityService,
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
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.cmessageService
                .query({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ICmessage[]>) => this.paginateCmessages(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.cmessageService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ICmessage[]>) => this.paginateCmessages(res.body, res.headers),
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
        this.router.navigate(['/cmessage'], {
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
            '/cmessage',
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
            '/cmessage',
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
            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount);
            this.myCmessages();
        });
        this.registerChangeInCmessages();
    }

    myCmessages() {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.communityService.query(query).subscribe(
            (res: HttpResponse<ICommunity[]>) => {
                this.communities = res.body;
                console.log('CONSOLOG: M:loginData & O: this.communities : ', this.communities);
                const query2 = {
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    sort: this.sort()
                };
                if (this.communities != null) {
                    const arrayCommmunities = [];
                    this.communities.forEach(community => {
                        arrayCommmunities.push(community.id);
                    });
                    query2['creceiverId.in'] = arrayCommmunities;
                    //                    query2['isDelivered.equals'] = 'false';
                }
                this.cmessageService.query(query2).subscribe(
                    (res2: HttpResponse<ICmessage[]>) => {
                        this.cmessages = res2.body;
                        this.paginateCmessages(res2.body, res2.headers);
                        console.log('CONSOLOG: M:myUserMessages & O: this.cmessages : ', this.cmessages);
                        this.isDeliveredUpdate(this.cmessages);
                    },
                    (res2: HttpErrorResponse) => this.onError(res2.message)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    isDeliveredUpdate(cmessages: ICmessage[]) {
        this.isSaving = true;
        this.cmessages.forEach(cmessage => {
            console.log('CONSOLOG: M:isDeliveredUpdate & O: cmessages PRE-Date : ', cmessages);
            this.creationDate = moment(cmessage.creationDate).format(DATE_TIME_FORMAT);
            console.log('CONSOLOG: M:isDeliveredUpdate & O: this.notificationDate : ', this.creationDate);
            console.log('CONSOLOG: M:isDeliveredUpdate & O: notifications POST-Date : ', cmessages);
            cmessage.isDelivered = true;
            //            this.notificationService.update(notification);
            this.subscribeToSaveResponse(this.cmessageService.update(cmessage));
            //            this.subscribeToSaveResponse(this.notificationService.update(notification));
            console.log('CONSOLOG: M:isDeliveredUpdate & O: cmessages : ', cmessages);
        });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICmessage>>) {
        result.subscribe((res: HttpResponse<ICmessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICmessage) {
        return item.id;
    }

    registerChangeInCmessages() {
        this.eventSubscriber = this.eventManager.subscribe('cmessageListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateCmessages(data: ICmessage[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        console.log(
            'CONSOLOG: M:paginateCmessages & O: parseInt(headers.get(X-Total-Count), 10) : ',
            parseInt(headers.get('X-Total-Count'), 10)
        );
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.cmessages = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
