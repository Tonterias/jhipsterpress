import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVtopic } from 'app/shared/model/vtopic.model';
import { IVquestion } from 'app/shared/model/vquestion.model';
import { VquestionService } from '../vquestion/vquestion.service';
import { IVthumb } from 'app/shared/model/vthumb.model';
import { VthumbService } from '../vthumb/vthumb.service';

import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-vtopic-detail',
    templateUrl: './vtopic-detail.component.html'
})
export class VtopicDetailComponent implements OnInit {
    values: IVquestion[];
    vtopic: IVtopic;
    vquestions: IVquestion[];
    items = [];
    accc = true;

    menu = [];

    itemsPerPage: any;
    page: any;
    predicate: any;

    reverse: any;

    //    vthumb: IVthumb = {};
    private _vthumb: IVthumb = {};
    isSaving: boolean;
    creationDate: string;
    currentAccount: any;

    constructor(
        protected vquestionService: VquestionService,
        protected vthumbService: VthumbService,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vtopic }) => {
            this.vtopic = vtopic;
            const query = {
                //                    page: this.page - 1,
                //                    size: this.itemsPerPage,
                //                    sort: this.sort()
            };
            if (this.vtopic != null) {
                query['vtopicId.equals'] = vtopic.id;
            }
            this.vquestionService.query(query).subscribe(
                (res: HttpResponse<IVquestion[]>) => {
                    this.vquestions = res.body;
                    this.menu = Array(this.vquestions.length).fill(false);
                    console.log('CONSOLOG: M:ngOnInit & O: this.vquestions : ', this.vquestions);
                    //                        this.paginatePosts(res.body, res.headers);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        //        this.creationDate = this.vthumb.creationDate != null ? this.vthumb.creationDate.format(DATE_TIME_FORMAT) : null;
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            //            this.owner = account.id;
            //            console.log('CONSOLOG: M:paginateProfiles & O: this.owner : ', this.owner);
            //            this.currentLoggedProfile();
        });
    }

    changeMenu(i) {
        this.menu[i] = !this.menu[i];
    }

    registerQuestionThumbUp(number) {
        console.log('CONSOLOG: M:registerQuestionThumbUp & O: this.vthumb : ', this.vthumb);
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: number this.vquestion.id : ', number, this.vquestions[number]);
        this.isSaving = true;
        this.vthumb = this.vthumb;
        // let vthumb: any; // = this.vthumb;
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.vthumb : ', this.vthumb);
        if (number !== undefined) {
            this.vthumb.vthumbUp = true;
            this.vthumb.vthumbDown = false;
            this.vthumb.userId = this.currentAccount.id;
            this.vthumb.vquestionId = number;
            this.vthumb.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
            console.log('CONSOLOG: M:registerQuestionThumbUp & O: this.vthumb : ', this.vthumb);
            //            this.vquestions[number].vthumbs.push(this.vthumb);
            this.subscribeToSaveResponse(this.vthumbService.create(this.vthumb));
            //            const vthumb = {
            //                    "ID": 1010,
            //                    "CREATIONDATE": this.vthumb.creationDate,
            //                    "VTHUMBUP": TRUE,
            //                    "VTHUMBDOWN": FALSE,
            //                    "USERID": 3,
            //                    "VQUESTIONID": number,
            //                    "VANSWERID": NULL
            //                },
            console.log('EEEEEEEEEEEEEEEEEEEEE', this.vquestions[number]);
            //            this.vquestions[number].vthumbs.push(vthumb);
        } else {
            console.log('CONSOLOG: M:registerThumbUp & O: SIN number : ', number);
        }
    }

    registerQuestionThumbDown(number) {
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: number : ', number);
        this.isSaving = true;
        this.vthumb = this.vthumb;
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.vthumb : ', this.vthumb);
        if (number !== undefined) {
            this.vthumb.vthumbUp = false;
            this.vthumb.vthumbDown = true;
            this.vthumb.userId = this.currentAccount.id;
            this.vthumb.vquestionId = number;
            this.vthumb.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
            console.log('CONSOLOG: M:registerQuestionThumbDown & O: this.vthumb : ', this.vthumb);
            this.subscribeToSaveResponse(this.vthumbService.create(this.vthumb));
            this.vquestions[number].vthumbs.push(this.vthumb);
        } else {
            console.log('CONSOLOG: M:registerThumbUp & O: SIN number : ', number);
        }
    }

    registerAnswerThumbUp(number) {
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: number : ', number);
        this.isSaving = true;
        this.vthumb = this.vthumb;
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.vthumb : ', this.vthumb);
        if (number !== undefined) {
            this.vthumb.vthumbUp = true;
            this.vthumb.vthumbDown = false;
            this.vthumb.userId = this.currentAccount.id;
            this.vthumb.vanswerId = number;
            this.vthumb.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
            console.log('CONSOLOG: M:registerAnswerThumbUp & O: this.vthumb : ', this.vthumb);
            this.subscribeToSaveResponse(this.vthumbService.create(this.vthumb));
        } else {
            console.log('CONSOLOG: M:registerThumbUp & O: SIN number : ', number);
        }
    }

    registerAnswerThumbDown(number) {
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: number : ', number);
        this.isSaving = true;
        this.vthumb = this.vthumb;
        console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.vthumb : ', this.vthumb);
        if (number !== undefined) {
            this.vthumb.vthumbUp = false;
            this.vthumb.vthumbDown = true;
            this.vthumb.userId = this.currentAccount.id;
            this.vthumb.vanswerId = number;
            this.creationDate = this.vthumb.creationDate != null ? this.vthumb.creationDate.format(DATE_TIME_FORMAT) : null;
            console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.creationDate : ', this.creationDate);
            this.vthumb.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
            console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.vthumb.creationDate : ', this.vthumb.creationDate);
            console.log('CONSOLOG: M:registerAnswerThumbDown & O: this.vthumb : ', this.vthumb);
            this.subscribeToSaveResponse(this.vthumbService.create(this.vthumb));
        } else {
            console.log('CONSOLOG: M:registerThumbUp & O: SIN number : ', number);
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVthumb>>) {
        result.subscribe((res: HttpResponse<IVthumb>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        //        this.reload();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    get vthumb() {
        return this._vthumb;
    }

    set vthumb(vthumb: IVthumb) {
        this._vthumb = vthumb;
        this.creationDate = moment(vthumb.creationDate).format(DATE_TIME_FORMAT);
        this.vthumb.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        console.log('CONSOLOG: M:vthumb & O: this.creationDate : ', this.creationDate);
        console.log('CONSOLOG: M:vthumb & O: this.vthumb : ', this.vthumb);
        console.log('CONSOLOG: M:vthumb & O: this._vthumb : ', this._vthumb);
    }

    previousState() {
        window.history.back();
    }

    reload() {
        window.location.reload();
    }

    accordionAddItem(i) {
        if (this.items.includes(i)) {
            const index = this.items.indexOf(i);
            this.items.splice(index, 1);
        } else {
            this.items.push(i);
        }
    }

    accordionShowItem(i) {
        return this.items.includes(i);
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
