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
    childrenSearchTerm: string;
    futureChildrenSearchTerm: string;
    petSearchTerm: boolean;
    sibblingsSearchTerm: number;

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

    ngOnInit() {}

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
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
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

    childrenSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.childrenSearchTerm : ', this.childrenSearchTerm);
        if (this.childrenSearchTerm) {
            const query = {};
            query['children.equals'] = this.childrenSearchTerm;
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

    futureChildrenSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.futureChildrenSearchTerm : ', this.futureChildrenSearchTerm);
        if (this.futureChildrenSearchTerm) {
            const query = {};
            query['futureChildren.equals'] = this.futureChildrenSearchTerm;
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

    petSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.petSearchTerm : ', this.petSearchTerm);
        if (this.petSearchTerm) {
            const query = {};
            query['pet.equals'] = this.petSearchTerm;
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

    sibblingsSearch() {
        console.log('CONSOLOG: M:loadAll & O: this.sibblingsSearchTerm : ', this.sibblingsSearchTerm);
        if (this.sibblingsSearchTerm) {
            const query = {};
            query['sibblings.equals'] = this.sibblingsSearchTerm;
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

    clear() {
        this.page = 0;
        this.bioSearchTerm = '';
        this.genderSearchTerm = '';
        this.civilStatusSearchTerm = '';
        this.lookingForSearchTerm = '';
        this.purposeSearchTerm = '';
        this.physicalSearchTerm = '';
        this.religionSearchTerm = '';
        this.ethnicGroupSearchTerm = '';
        this.studiesSearchTerm = '';
        this.eyesSearchTerm = '';
        this.smokerSearchTerm = '';
        this.childrenSearchTerm = '';
        this.futureChildrenSearchTerm = '';
        this.petSearchTerm = false;
        this.sibblingsSearchTerm = 0;
        //        this.router.navigate([
        //            '/uprofile',
        //            {
        //                page: this.page,
        //                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        //            }
        //        ]);
    }

    //    private filterArray(posts) {
    //        this.arrayAux = [];
    //        this.arrayIds = [];
    //        posts.map(x => {
    //            if (this.arrayIds.length >= 1 && this.arrayIds.includes(x.id) === false) {
    //                this.arrayAux.push(x);
    //                this.arrayIds.push(x.id);
    //            } else if (this.arrayIds.length === 0) {
    //                this.arrayAux.push(x);
    //                this.arrayIds.push(x.id);
    //            }
    //        });
    //        //        console.log('CONSOLOG: M:filterInterests & O: this.follows : ', this.arrayIds, this.arrayAux);
    //        return this.arrayAux;
    //    }

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
