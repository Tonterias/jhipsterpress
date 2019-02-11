import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { ICceleb } from 'app/shared/model/cceleb.model';
import { CcelebService } from './cceleb.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-cceleb-update',
    templateUrl: './cceleb-update.component.html'
})
export class CcelebUpdateComponent implements OnInit {
    cceleb: ICceleb;
    ccelebs: ICceleb[];
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
        protected ccelebService: CcelebService,
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
        this.activatedRoute.data.subscribe(({ cceleb }) => {
            this.cceleb = cceleb;
            console.log('CONSOLOG: M:ngOnInit & O: this.cceleb : ', this.cceleb);
            console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myCommunityCcelebs();
        });
        this.communityService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICommunity[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICommunity[]>) => response.body)
            )
            .subscribe((res: ICommunity[]) => (this.communities = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    protected myCommunityCcelebs() {
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
        if (this.cceleb.id !== undefined) {
            this.subscribeToSaveResponse(this.ccelebService.update(this.cceleb));
        } else {
            this.subscribeToSaveResponse(this.ccelebService.create(this.cceleb));
        }
    }

    loadAll() {
        console.log('CONSOLOG: M:loadAll & O: this.currentSearch : ', this.currentSearch);
        if (this.currentSearch) {
            this.ccelebService
                .query({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ICceleb[]>) => this.paginateCinterests(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.ccelebService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ICceleb[]>) => this.paginateCinterests(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    addExistingProfileCceleb(ccelebId) {
        console.log(
            'CONSOLOG: M:addExistingProfileInterest & interestId: ',
            ccelebId,
            ', uprofileId : ',
            this.nameParamCommunityId,
            ' &:',
            this.valueParamCommunityId
        );
        this.isSaving = true;
        if (ccelebId !== undefined) {
            const query = {};
            query['id.equals'] = ccelebId;
            console.log('CONSOLOG: M:addExistingProfileInterest & O: query : ', query);
            this.ccelebService.query(query).subscribe(
                (res: HttpResponse<ICceleb[]>) => {
                    this.ccelebs = res.body;
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: res.body : ', res.body);
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: this.ccelebs : ', this.ccelebs);
                    const query2 = {};
                    if (this.valueParamCommunityId != null) {
                        query2['id.equals'] = this.valueParamCommunityId;
                    }
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: query2 : ', query2);
                    this.communityService.query(query2).subscribe(
                        (res2: HttpResponse<ICommunity[]>) => {
                            this.ccelebs[0].communities.push(res2.body[0]);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: res2.body : ', res2.body);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: this.ccelebs : ', this.ccelebs);
                            this.subscribeToSaveResponse(this.ccelebService.update(this.ccelebs[0]));
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
        this.router.navigate(['/cceleb/new'], {
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
            '/cceleb/new',
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
            '/cceleb/new',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    private paginateCinterests(data: ICommunity[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.ccelebs = data;
        if (this.queryCount === 0) {
            this.cceleb.celebName = this.currentSearch;
        }
        console.log('CONSOLOG: M:paginateActivities & O: this.totalItems : ', this.totalItems);
        console.log('CONSOLOG: M:paginateActivities & O: this.queryCount : ', this.queryCount);
        console.log('CONSOLOG: M:paginateActivities & O: this.ccelebs : ', this.ccelebs);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICceleb>>) {
        result.subscribe((res: HttpResponse<ICceleb>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
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

    trackId(index: number, item: ICceleb) {
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
