import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IComment } from 'app/shared/model/comment.model';
import { CommentService } from './comment.service';
import { IUser, UserService } from 'app/core';
import { IPost } from 'app/shared/model/post.model';
import { PostService } from 'app/entities/post';

@Component({
    selector: 'jhi-comment-update',
    templateUrl: './comment-update.component.html'
})
export class CommentUpdateComponent implements OnInit {
    comment: IComment;
    isSaving: boolean;

    users: IUser[];

    posts: IPost[];
    creationDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected commentService: CommentService,
        protected userService: UserService,
        protected postService: PostService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ comment }) => {
            this.comment = comment;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.comment.creationDate = moment(this.creationDate);
        });
        //        this.userService.query().subscribe(
        //                (res: HttpResponse<IUser[]>) => {
        //                    this.users = res.body;
        //                },
        //                (res: HttpErrorResponse) => this.onError(res.message)
        //            );
        //            this.postService.query().subscribe(
        //                (res: HttpResponse<IPost[]>) => {
        //                    this.posts = res.body;
        //                },
        //                (res: HttpErrorResponse) => this.onError(res.message)
        //            );
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.postService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPost[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPost[]>) => response.body)
            )
            .subscribe((res: IPost[]) => (this.posts = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.comment.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.comment.id !== undefined) {
            this.subscribeToSaveResponse(this.commentService.update(this.comment));
        } else {
            this.subscribeToSaveResponse(this.commentService.create(this.comment));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IComment>>) {
        result.subscribe((res: HttpResponse<IComment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPostById(index: number, item: IPost) {
        return item.id;
    }
}
