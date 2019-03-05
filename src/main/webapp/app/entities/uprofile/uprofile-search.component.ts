import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from './uprofile.service';
import { IUser, UserService } from 'app/core';
// import { IInterest } from 'app/shared/model/interest.model';
// import { InterestService } from 'app/entities/interest';
// import { IActivity } from 'app/shared/model/activity.model';
// import { ActivityService } from 'app/entities/activity';
// import { ICeleb } from 'app/shared/model/celeb.model';
// import { CelebService } from 'app/entities/celeb';

import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-uprofile-update',
    templateUrl: './uprofile-search.component.html'
})
export class UprofileSearchComponent implements OnInit {
    private _uprofile: IUprofile;
    //    uprofile: IUprofile;
    uprofiles: IUprofile[];

    users: IUser[];
    user: IUser;

    //    interests: IInterest[];
    //
    //    activities: IActivity[];
    //
    //    celebs: ICeleb[];
    creationDate: string;
    birthdate: string;

    currentAccount: any;
    links: any;
    totalItems: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    isSaving: boolean;

    arrayAux = [];
    arrayIds = [];

    bioSearchTerm: string;
    genderSearchTerm: string;
    civilStatusSearchTerm: string;
    lookingForSearchTerm: string;
    purposeSearchTerm: string;
    physicalSearchTerm: string;
    religionSearchTerm: string;
    ethnicGroupSearchTerm: string;
    studiesSearchTerm: string;
    eyesSearchTerm: string;
    smokerSearchTerm: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected uprofileService: UprofileService,
        protected userService: UserService,
        //        protected interestService: InterestService,
        //        protected activityService: ActivityService,
        //        protected celebService: CelebService,
        protected accountService: AccountService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected parseLinks: JhiParseLinks,
        protected eventManager: JhiEventManager
    ) {
        //        this.currentSearch =
        //            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
        //                ? this.activatedRoute.snapshot.params['search']
        //                : '';
    }

    ngOnInit() {
        //        this.isSaving = false;
        //        this.uprofile = new uprofile();
        //        this.activatedRoute.data.subscribe(( { uprofile } ) => {
        //            this.uprofile = uprofile;
        //            //            console.log('CONSOLOG: M:ngOnInit & O: this.uprofile : ', this.uprofile);
        //            this.creationDate = moment().format( DATE_TIME_FORMAT );
        //            this.uprofile.creationDate = moment( this.creationDate );
        //            this.birthdate = this.uprofile.birthdate != null ? this.uprofile.birthdate.format( DATE_TIME_FORMAT ) : null;
        //            this.accountService.identity().then( account => {
        //                this.currentAccount = account;
        //                //                console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount);
        //                this.userService.findById( this.currentAccount.id ).subscribe(
        //                    ( res: HttpResponse<IUser> ) => {
        //                        this.uprofile.userId = res.body.id;
        //                        //                        console.log('CONSOLOG: M:ngOnInit & O: this.user : ', this.user);
        //                    },
        //                    ( res: HttpErrorResponse ) => this.onError( res.message )
        //                );
        //            } );
        //        } );
        //        this.userService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IUser[]>) => response.body)
        //            )
        //            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.interestService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IInterest[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IInterest[]>) => response.body)
        //            )
        //            .subscribe((res: IInterest[]) => (this.interests = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.activityService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IActivity[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IActivity[]>) => response.body)
        //            )
        //            .subscribe((res: IActivity[]) => (this.activities = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.celebService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<ICeleb[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<ICeleb[]>) => response.body)
        //            )
        //            .subscribe((res: ICeleb[]) => (this.celebs = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    bioSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.currentSearch : ', this.bioSearchTerm);
        if (this.bioSearchTerm) {
            const query = {
                //                page: this.page - 1,
                //                size: this.itemsPerPage,
                //                sort: this.sort()
            };
            query['bio.contains'] = this.bioSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles : ', res.body);
                    this.uprofiles = res.body;
                    //                    const query2 = {
                    //                        page: this.page - 1,
                    //                        size: this.itemsPerPage,
                    //                        sort: this.sort()
                    //                    };
                    //                    query2['bodytext.contains'] = this.currentSearch;
                    //                    this.uprofileService.query(query2).subscribe(
                    //                        (res2: HttpResponse<IUprofile[]>) => {
                    //                            this.uprofiles = this.filterArray(this.uprofiles.concat(res2.body));
                    //                            const query3 = {
                    //                                page: this.page - 1,
                    //                                size: this.itemsPerPage,
                    //                                sort: this.sort()
                    //                            };
                    //                            query3['conclusion.contains'] = this.currentSearch;
                    //                            this.uprofileService.query(query3).subscribe(
                    //                                (res3: HttpResponse<IUprofile[]>) => {
                    //                                    this.uprofiles = this.filterArray(this.uprofiles.concat(res3.body));
                    //                                },
                    //                                (res3: HttpErrorResponse) => this.onError(res3.message)
                    //                            );
                    //                        },
                    //                        (res2: HttpErrorResponse) => this.onError(res2.message)
                    //                    );
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
        //        this.uprofileService
        //            .query({
        //                page: this.page - 1,
        //                size: this.itemsPerPage,
        //                sort: this.sort()
        //            })
        //            .subscribe(
        //                (res: HttpResponse<IPost[]>) => this.paginatePosts(res.body, res.headers),
        //                (res: HttpErrorResponse) => this.onError(res.message)
        //            );
    }

    genderSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.currentSearch2 : ', this.genderSearchTerm);
        if (this.genderSearchTerm) {
            const query = {};
            query['gender.equals'] = this.genderSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    civilStatusSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.civilStatusSearch : ', this.civilStatusSearchTerm);
        if (this.civilStatusSearchTerm) {
            const query = {};
            query['civilStatus.equals'] = this.civilStatusSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    lookingForSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.civilStatusSearch : ', this.lookingForSearchTerm);
        if (this.lookingForSearchTerm) {
            const query = {};
            query['lookingFor.equals'] = this.lookingForSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    purposeSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.purposeSearchTerm : ', this.purposeSearchTerm);
        if (this.purposeSearchTerm) {
            const query = {};
            query['purpose.equals'] = this.purposeSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    physicalSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.physicalSearchTerm : ', this.physicalSearchTerm);
        if (this.physicalSearchTerm) {
            const query = {};
            query['physical.equals'] = this.physicalSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    religionSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.religionSearchTerm : ', this.religionSearchTerm);
        if (this.religionSearchTerm) {
            const query = {};
            query['religion.equals'] = this.religionSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    ethnicGroupSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.ethnicGroupSearchTerm : ', this.ethnicGroupSearchTerm);
        if (this.ethnicGroupSearchTerm) {
            const query = {};
            query['ethnicGroup.equals'] = this.ethnicGroupSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    studiesSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.studiesSearchTerm : ', this.studiesSearchTerm);
        if (this.studiesSearchTerm) {
            const query = {};
            query['studies.equals'] = this.studiesSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    eyesSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.eyesSearchTerm : ', this.eyesSearchTerm);
        if (this.eyesSearchTerm) {
            const query = {};
            query['eyes.equals'] = this.eyesSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    smokerSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.smokerSearchTerm : ', this.smokerSearchTerm);
        if (this.smokerSearchTerm) {
            const query = {};
            query['smoker.equals'] = this.smokerSearchTerm;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    console.log('CONSOLOG: M:loadAll & O: res.body uprofiles2 : ', res.body);
                    this.uprofiles = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
    }

    private filterArray(posts) {
        this.arrayAux = [];
        this.arrayIds = [];
        posts.map(x => {
            if (this.arrayIds.length >= 1 && this.arrayIds.includes(x.id) === false) {
                this.arrayAux.push(x);
                this.arrayIds.push(x.id);
            } else if (this.arrayIds.length === 0) {
                this.arrayAux.push(x);
                this.arrayIds.push(x.id);
            }
        });
        //        console.log('CONSOLOG: M:filterInterests & O: this.follows : ', this.arrayIds, this.arrayAux);
        return this.arrayAux;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.uprofile, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.uprofile.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        this.uprofile.birthdate = this.birthdate != null ? moment(this.birthdate, DATE_TIME_FORMAT) : null;
        if (this.uprofile.id !== undefined) {
            this.subscribeToSaveResponse(this.uprofileService.update(this.uprofile));
        } else {
            this.subscribeToSaveResponse(this.uprofileService.create(this.uprofile));
        }
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IUprofile>>) {
        result.subscribe((res: HttpResponse<IUprofile>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    //    trackInterestById( index: number, item: IInterest ) {
    //        return item.id;
    //    }
    //
    //    trackActivityById( index: number, item: IActivity ) {
    //        return item.id;
    //    }
    //
    //    trackCelebById( index: number, item: ICeleb ) {
    //        return item.id;
    //    }

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

    get uprofile() {
        return this._uprofile;
    }

    set uprofile(uprofile: IUprofile) {
        this._uprofile = uprofile;
        console.log('CONSOLOG: M:set & O: this._uprofile : ', this._uprofile);
        //        this.creationDate = moment(uprofile.creationDate).format(DATE_TIME_FORMAT);
    }
}
