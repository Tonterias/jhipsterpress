import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ICactivity } from 'app/shared/model/cactivity.model';
import { CactivityService } from './cactivity.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-cactivity-update',
    templateUrl: './cactivity-update.component.html'
})
export class CactivityUpdateComponent implements OnInit {
    cactivity: ICactivity;
    cactivities: ICactivity[];
    isSaving: boolean;
    isCreateDisabled = false;

    communities: ICommunity[];

    nameParamCommunityId: any;
    valueParamCommunityId: any;

    currentAccount: any;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any = 1;
    predicate: any = 'id';
    previousPage: any = 0;
    reverse: any = 'asc';
    id: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cactivityService: CactivityService,
        protected communityService: CommunityService,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.communityIdEquals != null) {
                this.nameParamCommunityId = 'community.id';
                this.valueParamCommunityId = params.communityIdEquals;
                console.log('CONSOLOG: M:constructor & O: this.nameParamUprofileId : ', this.nameParamCommunityId);
                console.log('CONSOLOG: M:constructor & O: this.valueParamUprofileId : ', this.valueParamCommunityId);
                console.log('CONSOLOG: M:constructor & O: this.itemsPerPage : ', this.itemsPerPage);
            }
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cactivity }) => {
            this.cactivity = cactivity;
            console.log('CONSOLOG: M:ngOnInit & O: this.cactivity : ', this.cactivity);
            console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myCommunityCactivities();
        });
        //        this.communityService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<ICommunity[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<ICommunity[]>) => response.body)
        //            )
        //            .subscribe((res: ICommunity[]) => (this.communities = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    private myCommunityCactivities() {
        const query = {};
        if (this.valueParamCommunityId != null) {
            query['id.equals'] = this.valueParamCommunityId;
        }
        this.communityService.query(query).subscribe(
            (res: HttpResponse<ICommunity[]>) => {
                this.communities = res.body;
                console.log('CONSOLOG: M:myUserActivities & O: res.body : ', res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cactivity.id !== undefined) {
            this.subscribeToSaveResponse(this.cactivityService.update(this.cactivity));
        } else {
            this.cactivity.communities = this.communities;
            console.log('CONSOLOG: M:save & O: this.communities : ', this.communities);
            this.subscribeToSaveResponse(this.cactivityService.create(this.cactivity));
        }
    }

    loadAll() {
        console.log('CONSOLOG: M:loadAll & O: this.currentSearch : ', this.currentSearch);
        if (this.currentSearch) {
            this.cactivityService
                .query({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ICactivity[]>) => this.paginateCinterests(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.cactivityService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ICactivity[]>) => this.paginateCinterests(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    addExistingProfileCinterest(cinterestId) {
        console.log(
            'CONSOLOG: M:addExistingProfileInterest & interestId: ',
            cinterestId,
            ', uprofileId : ',
            this.nameParamCommunityId,
            ' &:',
            this.valueParamCommunityId
        );
        this.isSaving = true;
        if (cinterestId !== undefined) {
            const query = {};
            query['id.equals'] = cinterestId;
            console.log('CONSOLOG: M:addExistingProfileInterest & O: query : ', query);
            this.cactivityService.query(query).subscribe(
                (res: HttpResponse<ICactivity[]>) => {
                    this.cactivities = res.body;
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: res.body : ', res.body);
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: this.cactivities : ', this.cactivities);
                    const query2 = {};
                    if (this.valueParamCommunityId != null) {
                        query2['id.equals'] = this.valueParamCommunityId;
                    }
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: query2 : ', query2);
                    this.communityService.query(query2).subscribe(
                        (res2: HttpResponse<ICommunity[]>) => {
                            this.cactivities[0].communities.push(res2.body[0]);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: res2.body : ', res2.body);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: this.cinterests : ', this.cactivities);
                            this.subscribeToSaveResponse(this.cactivityService.update(this.cactivities[0]));
                        },
                        (res2: HttpErrorResponse) => this.onError(res2.message)
                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/cactivity/new'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                search: this.currentSearch,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    search(query) {
        this.isCreateDisabled = true;
        if (!query) {
            return this.clear();
        }
        this.page = 0;
        this.currentSearch = query;
        this.router.navigate([
            '/cactivity/new',
            {
                search: this.currentSearch,
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate([
            '/cactivity/new',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    protected paginateCinterests(data: ICommunity[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.cactivities = data;
        if (this.queryCount === 0) {
            this.cactivity.activityName = this.currentSearch;
        }
        console.log('CONSOLOG: M:paginateActivities & O: this.totalItems : ', this.totalItems);
        console.log('CONSOLOG: M:paginateActivities & O: this.queryCount : ', this.queryCount);
        console.log('CONSOLOG: M:paginateActivities & O: this.interests : ', this.cactivities);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICactivity>>) {
        result.subscribe((res: HttpResponse<ICactivity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.router.navigate(['/community/', this.valueParamCommunityId, 'view']);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCommunityById(index: number, item: ICommunity) {
        return item.id;
    }

    trackId(index: number, item: ICactivity) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
