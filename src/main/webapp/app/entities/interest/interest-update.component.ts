import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IInterest } from 'app/shared/model/interest.model';
import { InterestService } from './interest.service';
import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from 'app/entities/uprofile';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-interest-update',
    templateUrl: './interest-update.component.html'
})
export class InterestUpdateComponent implements OnInit {
    interest: IInterest;
    interests: IInterest[];
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
    itemsPerPage: any;
    page: any = 1;
    predicate: any = 'id';
    previousPage: any = 0;
    reverse: any = 'asc';
    id: any;

    constructor(
        protected interestService: InterestService,
        protected uprofileService: UprofileService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
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
        this.activatedRoute.data.subscribe(({ interest }) => {
            this.interest = interest;
            console.log('CONSOLOG: M:ngOnInit & O: this.interest : ', this.interest);
            console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myUserInterests(this.currentAccount);
        });
        this.uprofileService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUprofile[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUprofile[]>) => response.body)
            )
            .subscribe((res: IUprofile[]) => (this.uprofiles = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    private myUserInterests(currentAccount) {
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
        if (this.interest.id !== undefined) {
            this.subscribeToSaveResponse(this.interestService.update(this.interest));
        } else {
            this.interest.uprofiles = this.uprofiles;
            console.log('CONSOLOG: M:save & O: this.interest : ', this.interest);
            this.subscribeToSaveResponse(this.interestService.create(this.interest));
        }
    }

    loadAll() {
        if (this.currentSearch) {
            this.interestService
                .query({
                    page: this.page - 1,
                    interestName: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IInterest[]>) => this.paginateInterests(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.interestService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IInterest[]>) => this.paginateInterests(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    addExistingProfileInterest(interestId) {
        console.log(
            'CONSOLOG: M:addExistingProfileInterest & interestId: ',
            interestId,
            ', uprofileId : ',
            this.nameParamUprofileId,
            ' &:',
            this.valueParamUprofileId
        );
        this.isSaving = true;
        if (interestId !== undefined) {
            const query = {};
            query['id.equals'] = interestId;
            console.log('CONSOLOG: M:addExistingProfileInterest & O: query : ', query);
            this.interestService.query(query).subscribe(
                (res: HttpResponse<IInterest[]>) => {
                    this.interests = res.body;
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: res.body : ', res.body);
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: this.interestss : ', this.interests);
                    const query2 = {};
                    if (this.valueParamUprofileId != null) {
                        query2['id.equals'] = this.valueParamUprofileId;
                    }
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: query2 : ', query2);
                    this.uprofileService.query(query2).subscribe(
                        (res2: HttpResponse<IUprofile[]>) => {
                            this.interests[0].uprofiles.push(res2.body[0]);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: res2.body : ', res2.body);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: this.interests : ', this.interests);
                            this.subscribeToSaveResponse(this.interestService.update(this.interests[0]));
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
        this.router.navigate(['/interest/new'], {
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
            '/interest/new',
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
            '/interest/new',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    private paginateInterests(data: IInterest[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.interests = data;
        if (this.totalItems === 0) {
            this.interest.interestName = this.currentSearch;
        }
        console.log('CONSOLOG: M:paginateInterests & O: this.totalItems : ', this.totalItems);
        console.log('CONSOLOG: M:paginateInterests & O: this.interests : ', this.interests);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IInterest>>) {
        result.subscribe((res: HttpResponse<IInterest>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUprofileById(index: number, item: IUprofile) {
        return item.id;
    }

    trackId(index: number, item: IInterest) {
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
