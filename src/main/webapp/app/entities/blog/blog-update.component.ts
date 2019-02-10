import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IBlog } from 'app/shared/model/blog.model';
import { BlogService } from './blog.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-blog-update',
    templateUrl: './blog-update.component.html'
})
export class BlogUpdateComponent implements OnInit {
    blog: IBlog;
    isSaving: boolean;

    communities: ICommunity[];
    creationDate: string;

    currentAccount: any;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected blogService: BlogService,
        protected communityService: CommunityService,
        protected accountService: AccountService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ blog }) => {
            this.blog = blog;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.blog.creationDate = moment(this.creationDate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount);
            const query = {};
            if (this.currentAccount.id != null) {
                query['userId.equals'] = this.currentAccount.id;
            }
            this.communityService.query(query).subscribe(
                (res: HttpResponse<ICommunity[]>) => {
                    this.communities = res.body;
                    console.log('CONSOLOG: M:ngOnInit & O: this.blog : ', this.communities);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.communityService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICommunity[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICommunity[]>) => response.body)
            )
            .subscribe((res: ICommunity[]) => (this.communities = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.blog, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.blog.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        console.log('CONSOLOG: M:save & O: this.blog : ', this.blog);
        if (this.blog.id !== undefined) {
            this.subscribeToSaveResponse(this.blogService.update(this.blog));
        } else {
            this.subscribeToSaveResponse(this.blogService.create(this.blog));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlog>>) {
        result.subscribe((res: HttpResponse<IBlog>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
