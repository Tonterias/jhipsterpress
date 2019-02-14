import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { Subscription } from 'rxjs';

import { IComment } from 'app/shared/model/comment.model';
import { CommentService } from 'app/entities/comment';
import { IPost } from 'app/shared/model/post.model';
import { PostService } from 'app/entities/post';
import { IUser, UserService } from 'app/core';
import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from 'app/entities/uprofile';
import { ITag } from 'app/shared/model/tag.model';
import { TagService } from 'app/entities/tag';
import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from 'app/entities/topic';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-post-detail',
    templateUrl: './post-detail.component.html'
})
export class PostDetailComponent implements OnInit {
    private _comment: IComment;
    isSaving: boolean;

    post: IPost;
    posts: IPost[];
    comments: IComment[];
    tags: ITag[];
    topics: ITopic[];

    uprofile: IUprofile;
    uprofiles: IUprofile[];

    user: IUser;
    users: IUser[];

    currentAccount: any;
    creationDate: string;
    owner: any = 0;
    postUserFirstName: string;
    postUserLastName: string;

    error: any;
    success: any;
    eventSubscriber: Subscription;
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
        protected dataUtils: JhiDataUtils,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected commentService: CommentService,
        protected postService: PostService,
        protected uprofileService: UprofileService,
        protected tagService: TagService,
        protected topicService: TopicService,
        protected accountService: AccountService,
        protected userService: UserService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            //  this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams ? data.pagingParams.page : 0;
            this.reverse = data.pagingParams ? data.pagingParams.ascending : 'asc';
            this.predicate = data.pagingParams ? data.pagingParams.predicate : 'id';
        });
    }

    ngOnInit() {
        console.log('CONSOLOG: M:ngOnInit & O: this.page : ', this.page);
        console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        console.log('CONSOLOG: M:ngOnInit & O: this.previousPage : ', this.previousPage);
        console.log('CONSOLOG: M:ngOnInit & O: this.reverse : ', this.reverse);
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ post }) => {
            this.post = post;
            console.log('CONSOLOG: M:ngOnInit & O: this.post : ', this.post);
        });
        this.accountService.identity().then(account => {
            if (account != null) {
                this.currentAccount = account;
                this.owner = account.id;
                console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount.id);
                console.log('CONSOLOG: M:ngOnInit & O: this.owner : ', this.owner);
                this.loadAll();
                this.comment = new Object();
                this.comment.commentText = '';
                this.registerChangeInComments();
                console.log('CONSOLOG: M:ngOnInit & O: this.comments : ', this.comments);
            }
            console.log('CONSOLOG: M:ngOnInit & O: this.owner : ', this.owner);
        });
    }

    saveComment() {
        this.isSaving = true;
        this.comment.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        if (this.comment.id !== undefined) {
            this.subscribeToSaveResponse(this.commentService.update(this.comment));
        } else {
            this.userService.findById(this.currentAccount.id).subscribe(
                (res: HttpResponse<IUser>) => {
                    this.comment.userId = res.body.id;
                    this.comment.isOffensive = false;
                    this.comment.postId = this.post.id;
                    console.log('CONSOLOG: M:save & O: this.comment : ', this.comment);
                    this.subscribeToSaveResponse(this.commentService.create(this.comment));
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IComment>>) {
        result.subscribe((res: HttpResponse<IComment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.loadAll();
        this.comment.commentText = '';
        //        this.reload();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPostById(index: number, item: IPost) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    get comment() {
        return this._comment;
    }

    set comment(comment: IComment) {
        this._comment = comment;
        this.creationDate = moment(comment.creationDate).format(DATE_TIME_FORMAT);
        //        this._comment.id = undefined;
        //        console.log('CONSOLOG: M:set comment & O: this.comment : ', this.comment);
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    reload() {
        window.location.reload();
    }

    loadPage(page) {
        this.previousPage = page;
        this.page = page;
        this.loadAll();
    }

    loadAll() {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        query['postId.equals'] = this.post.id;
        this.commentService.query(query).subscribe(
            (res: HttpResponse<IComment[]>) => {
                console.log('CONSOLOG: M:loadAll & O: query : ', query);
                this.paginateComments(res.body, res.headers);
                console.log('CONSOLOG: M:loadAll & O: commentService res.body : ', res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        console.log('CONSOLOG: M:loadAll & O: this.post.userId : ', this.post.userId);
        const query2 = {};
        if (this.post.userId != null) {
            query2['userId.equals'] = this.post.userId;
        }
        this.uprofileService.query(query2).subscribe(
            (res: HttpResponse<IUprofile[]>) => {
                this.uprofiles = res.body;
                this.uprofile = res.body[0];
                console.log('CONSOLOG: M:ngOnInit & O: this.profile : ', this.uprofile);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.findById(this.post.userId).subscribe(
            (res: HttpResponse<IUser>) => {
                this.user = res.body;
                this.postUserFirstName = res.body.firstName;
                this.postUserLastName = res.body.lastName;
                console.log('CONSOLOG: M:ngOnInit & O: this.user : ', this.user);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    removePostTag(tagId, postId) {
        console.log('CONSOLOG: M:removePostTag & tagId: ', tagId, ', postId : ', postId);
        console.log('CONSOLOG: M:removePostTag & O: this.post : ', this.post);
        this.post.tags.forEach(tag => {
            if (tag.id === tagId) {
                console.log('CONSOLOG: M:removePostTag; INDEX!!!!!: ', this.post.tags.indexOf(tag));
                this.post.tags.splice(this.post.tags.indexOf(tag), 1);
                console.log('CONSOLOG: M:removePostTag; & this.post: ', this.post);
                this.subscribeToSaveResponse2(this.tagService.update(tag));
            }
        });
    }

    removePostTopic(topicId, postId) {
        console.log('CONSOLOG: M:removePostTopic & topicId: ', topicId, ', postId : ', postId);
        console.log('CONSOLOG: M:removePostTopic & O: this.post : ', this.post);
        this.post.topics.forEach(topic => {
            if (topic.id === topicId) {
                console.log('CONSOLOG: M:removePostTopic; INDEX!!!!!: ', this.post.topics.indexOf(topic));
                this.post.topics.splice(this.post.topics.indexOf(topic), 1);
                console.log('CONSOLOG: M:removePostTopic; & this.post: ', this.post);
                this.subscribeToSaveResponse3(this.topicService.update(topic));
            }
        });
    }

    private subscribeToSaveResponse2(result: Observable<HttpResponse<ITag>>) {
        result.subscribe((res: HttpResponse<ITag>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private subscribeToSaveResponse3(result: Observable<HttpResponse<ITopic>>) {
        result.subscribe((res: HttpResponse<ITopic>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess2() {
        this.isSaving = false;
    }

    transition() {
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/comment',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    trackId(index: number, item: IComment) {
        return item.id;
    }

    registerChangeInComments() {
        this.eventSubscriber = this.eventManager.subscribe('commentListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    private paginateComments(data: IComment[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.comments = data;
        console.log('CONSOLOG: M:paginateComments & O: this.comments : ', this.comments);
    }
}
