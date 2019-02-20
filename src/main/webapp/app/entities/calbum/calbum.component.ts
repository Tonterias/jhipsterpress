import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ICalbum } from 'app/shared/model/calbum.model';
import { CalbumService } from './calbum.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from '../community/community.service';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-calbum',
    templateUrl: './calbum.component.html'
})
export class CalbumComponent implements OnInit, OnDestroy {
    currentAccount: any;
    calbums: ICalbum[];
    communities: ICommunity[];
    arrayCommmunities = [];
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
    owner: any;
    isAdmin: boolean;

    constructor(
        protected calbumService: CalbumService,
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

    //    loadAll() {
    //        if (this.currentSearch) {
    //            this.calbumService
    //                .query({
    //                    page: this.page - 1,
    //                    'title.contains': this.currentSearch,
    //                    size: this.itemsPerPage,
    //                    sort: this.sort()
    //                })
    //                .subscribe(
    //                    (res: HttpResponse<ICalbum[]>) => this.paginateCalbums(res.body, res.headers),
    //                    (res: HttpErrorResponse) => this.onError(res.message)
    //                );
    //            return;
    //        }
    //        this.calbumService
    //            .query({
    //                page: this.page - 1,
    //                size: this.itemsPerPage,
    //                sort: this.sort()
    //            })
    //            .subscribe(
    //                (res: HttpResponse<ICalbum[]>) => this.paginateCalbums(res.body, res.headers),
    //                (res: HttpErrorResponse) => this.onError(res.message)
    //            );
    //    }

    loadAll() {
        if (this.currentSearch) {
            const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
            query['title.contains'] = this.currentSearch;
            query['creceiverId.in'] = this.arrayCommmunities;
            this.calbumService.query(query).subscribe(
                (res: HttpResponse<ICalbum[]>) => {
                    this.calbums = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
        const query2 = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        query2['creceiverId.in'] = this.arrayCommmunities;
        this.calbumService.query(query2).subscribe(
            (res: HttpResponse<ICalbum[]>) => {
                this.calbums = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        return;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/calbum'], {
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
            '/calbum',
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
            '/calbum',
            {
                search: this.currentSearch,
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
            this.isAdmin = this.accountService.hasAnyAuthority(['ROLE_ADMIN']);
            this.myCalbums();
        });
        this.loadAll();
        this.registerChangeInCalbums();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICalbum) {
        return item.id;
    }

    registerChangeInCalbums() {
        this.eventSubscriber = this.eventManager.subscribe('calbumListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    myCalbums() {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.communityService
            .query(query)
            .subscribe(
                (res: HttpResponse<ICommunity[]>) => this.paginateCommunities(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    private communitiesAlbums() {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        if (this.communities != null) {
            this.arrayCommmunities = [];
            this.communities.forEach(community => {
                this.arrayCommmunities.push(community.id);
                //                console.log('CONSOLOG: M:communitiesAlbums & O: this.arrayCommmunities : ', this.arrayCommmunities);
            });
            query['communityId.in'] = this.arrayCommmunities;
        }
        this.calbumService
            .query(query)
            .subscribe(
                (res: HttpResponse<ICalbum[]>) => this.paginateCalbums(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    protected paginateCalbums(data: ICalbum[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.calbums = data;
    }

    private paginateCommunities(data: ICommunity[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.communities = data;
        this.communitiesAlbums();
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
