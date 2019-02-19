import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from './activity.service';
import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from 'app/entities/uprofile';

import { ITEMS_PER_PAGE } from 'app/shared';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-activity-update',
    templateUrl: './activity-update.component.html'
})
export class ActivityUpdateComponent implements OnInit {
    activity: IActivity;
    activities: IActivity[];
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
        protected jhiAlertService: JhiAlertService,
        protected activityService: ActivityService,
        protected uprofileService: UprofileService,
        protected parseLinks: JhiParseLinks,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService,
        protected router: Router,
        protected activatedRoute: ActivatedRoute
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.uprofileIdEquals != null) {
                this.nameParamUprofileId = 'uprofile.userId';
                this.valueParamUprofileId = params.uprofileIdEquals;
                //                console.log('CONSOLOG: M:constructor & O: this.nameParamUprofileId : ', this.nameParamUprofileId);
                //                console.log('CONSOLOG: M:constructor & O: this.valueParamUprofileId : ', this.valueParamUprofileId);
            }
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ activity }) => {
            this.activity = activity;
            //            console.log('CONSOLOG: M:ngOnInit & O: this.activity : ', this.activity);
            //            console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myUserActivities(this.currentAccount);
        });
    }

    protected myUserActivities(currentAccount) {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.uprofileService.query(query).subscribe(
            (res: HttpResponse<IUprofile[]>) => {
                this.uprofiles = res.body;
                //                console.log('CONSOLOG: M:myProfiles & O: res.body : ', res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.activity.id !== undefined) {
            this.subscribeToSaveResponse(this.activityService.update(this.activity));
        } else {
            this.activity.uprofiles = this.uprofiles;
            //            console.log('CONSOLOG: M:save & O: this.activity : ', this.activity);
            this.subscribeToSaveResponse(this.activityService.create(this.activity));
        }
    }

    loadAll() {
        if (this.currentSearch) {
            this.activityService
                .query({
                    page: this.page - 1,
                    'activityName.contains': this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<IActivity[]>) => this.paginateActivities(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.activityService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IActivity[]>) => this.paginateActivities(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    addExistingProfileActivity(activityId) {
        //        console.log(
        //            //            'CONSOLOG: M:addExistingProfileActivity & ActivityId: ',
        //            activityId,
        //            ', uprofileId : ',
        //            this.nameParamUprofileId,
        //            ' &:',
        //            this.valueParamUprofileId
        //        );
        this.isSaving = true;
        if (activityId !== undefined) {
            const query = {};
            query['id.equals'] = activityId;
            //            console.log('CONSOLOG: M:addExistingProfileInterest & O: query : ', query);
            this.activityService.query(query).subscribe(
                (res: HttpResponse<IActivity[]>) => {
                    this.activities = res.body;
                    //                    console.log('CONSOLOG: M:addExistingProfileActivity & O: res.body : ', res.body);
                    //                    console.log('CONSOLOG: M:addExistingProfileActivity & O: this.Activityss : ', this.activities);
                    const query2 = {};
                    if (this.valueParamUprofileId != null) {
                        query2['id.equals'] = this.valueParamUprofileId;
                    }
                    //                    console.log('CONSOLOG: M:addExistingProfileActivity & O: query2 : ', query2);
                    this.uprofileService.query(query2).subscribe(
                        (res2: HttpResponse<IUprofile[]>) => {
                            this.activities[0].uprofiles.push(res2.body[0]);
                            //                            console.log('CONSOLOG: M:addExistingProfileActivity & O: res2.body : ', res2.body);
                            //                            console.log('CONSOLOG: M:addExistingProfileActivity & O: this.Activitys : ', this.activities);
                            this.subscribeToSaveResponse(this.activityService.update(this.activities[0]));
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
        this.router.navigate(['/activity/new'], {
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
            '/activity/new',
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
            '/activity/new',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    protected paginateActivities(data: IActivity[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.activities = data;
        if (this.totalItems === 0) {
            this.activity.activityName = this.currentSearch;
        }
        //        console.log('CONSOLOG: M:paginateActivities & O: this.totalItems : ', this.totalItems);
        //        console.log('CONSOLOG: M:paginateActivities & O: this.activities : ', this.activities);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IActivity>>) {
        result.subscribe((res: HttpResponse<IActivity>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackId(index: number, item: IActivity) {
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
