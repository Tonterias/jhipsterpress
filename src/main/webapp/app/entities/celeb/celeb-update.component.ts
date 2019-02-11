import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ICeleb } from 'app/shared/model/celeb.model';
import { CelebService } from './celeb.service';
import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from 'app/entities/uprofile';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-celeb-update',
    templateUrl: './celeb-update.component.html'
})
export class CelebUpdateComponent implements OnInit {
    celeb: ICeleb;
    celebs: ICeleb[];
    isSaving: boolean;
    isCreateDisabled = false;

    uprofiles: IUprofile[];

    nameParamUprofileId: any;
    valueParamUprofileId: any;

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
        protected celebService: CelebService,
        protected uprofileService: UprofileService,
        protected accountService: AccountService,
        protected parseLinks: JhiParseLinks,
        protected router: Router,
        protected activatedRoute: ActivatedRoute,
        protected eventManager: JhiEventManager
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.uprofileIdEquals != null) {
                this.nameParamUprofileId = 'uprofile.userId';
                this.valueParamUprofileId = params.uprofileIdEquals;
                console.log('CONSOLOG: M:constructor & O: this.nameParamUprofileId : ', this.nameParamUprofileId);
                console.log('CONSOLOG: M:constructor & O: this.valueParamUprofileId : ', this.valueParamUprofileId);
            }
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ celeb }) => {
            this.celeb = celeb;
            console.log('CONSOLOG: M:ngOnInit & O: this.celeb : ', this.celeb);
            console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myUserCelebs(this.currentAccount);
        });
        this.uprofileService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUprofile[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUprofile[]>) => response.body)
            )
            .subscribe((res: IUprofile[]) => (this.uprofiles = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    private myUserCelebs(currentAccount) {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.uprofileService.query(query).subscribe(
            (res: HttpResponse<IUprofile[]>) => {
                this.uprofiles = res.body;
                console.log('CONSOLOG: M:myProfiles & O: res.body : ', res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.celeb.id !== undefined) {
            this.subscribeToSaveResponse(this.celebService.update(this.celeb));
        } else {
            this.celeb.uprofiles = this.uprofiles;
            console.log('CONSOLOG: M:save & O: this.celeb : ', this.celeb);
            this.subscribeToSaveResponse(this.celebService.create(this.celeb));
        }
    }

    loadAll() {
        if (this.currentSearch) {
            this.celebService
                .query({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ICeleb[]>) => this.paginateCelebs(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.celebService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ICeleb[]>) => this.paginateCelebs(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    addExistingProfileCeleb(celebId) {
        console.log(
            'CONSOLOG: M:addExistingProfileCeleb & celebId: ',
            celebId,
            ', uprofileId : ',
            this.nameParamUprofileId,
            ' &:',
            this.valueParamUprofileId
        );
        this.isSaving = true;
        if (celebId !== undefined) {
            const query = {};
            query['id.equals'] = celebId;
            console.log('CONSOLOG: M:addExistingProfileInterest & O: query : ', query);
            this.celebService.query(query).subscribe(
                (res: HttpResponse<ICeleb[]>) => {
                    this.celebs = res.body;
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: res.body : ', res.body);
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: this.celebs : ', this.celebs);
                    const query2 = {};
                    if (this.valueParamUprofileId != null) {
                        query2['id.equals'] = this.valueParamUprofileId;
                    }
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: query2 : ', query2);
                    this.uprofileService.query(query2).subscribe(
                        (res2: HttpResponse<IUprofile[]>) => {
                            this.celebs[0].uprofiles.push(res2.body[0]);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: res2.body : ', res2.body);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: this.celebs : ', this.celebs);
                            this.subscribeToSaveResponse(this.celebService.update(this.celebs[0]));
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
        this.router.navigate(['/celeb/new'], {
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
            '/celeb/new',
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
            '/celeb/new',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    private paginateCelebs(data: ICeleb[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.queryCount = this.totalItems;
        this.celebs = data;
        if (this.queryCount === 0) {
            this.celeb.celebName = this.currentSearch;
        }
        console.log('CONSOLOG: M:paginateActivities & O: this.totalItems : ', this.totalItems);
        console.log('CONSOLOG: M:paginateActivities & O: this.queryCount : ', this.queryCount);
        console.log('CONSOLOG: M:paginateActivities & O: this.celebs : ', this.celebs);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICeleb>>) {
        result.subscribe((res: HttpResponse<ICeleb>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.router.navigate(['/uprofile/', this.valueParamUprofileId, 'view']);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUprofileById(index: number, item: IUprofile) {
        return item.id;
    }

    trackId(index: number, item: ICeleb) {
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
