import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPost } from 'app/shared/model/post.model';
import { PostService } from './post.service';
import { IUser, UserService } from 'app/core';
import { IBlog } from 'app/shared/model/blog.model';
import { BlogService } from 'app/entities/blog';
import { ITag } from 'app/shared/model/tag.model';
import { TagService } from 'app/entities/tag';
import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from 'app/entities/topic';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from '../../entities/community/community.service';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-post-update',
    templateUrl: './post-update.component.html'
})
export class PostUpdateComponent implements OnInit {
    private _post: IPost;
    isSaving: boolean;

    users: IUser[] = [];
    user: IUser;

    blogs: IBlog[];

    tags: ITag[];

    topics: ITopic[];

    communities: ICommunity[];

    loggeUserdId: number;
    creationDate: string;
    publicationDate: string;
    currentAccount: any;
    userLogin: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected postService: PostService,
        protected communityService: CommunityService,
        protected accountService: AccountService,
        protected userService: UserService,
        protected blogService: BlogService,
        protected tagService: TagService,
        protected topicService: TopicService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ post }) => {
            this.post = post;
            console.log('CONSOLOG: M:ngOnInit & O: this.post : ', this.post);
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.post.creationDate = moment(this.creationDate);
            this.post.publicationDate = moment(this.creationDate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.loggeUserdId = this.currentAccount.id;
            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount);
            this.userService.findById(this.currentAccount.id).subscribe(
                (res: HttpResponse<IUser>) => {
                    this.post.userId = res.body.id;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.myCommunities(this.currentAccount);
        });
        this.tagService.query().subscribe(
            (res: HttpResponse<ITag[]>) => {
                this.tags = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.topicService.query().subscribe(
            (res: HttpResponse<ITopic[]>) => {
                this.topics = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        //        this.userService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IUser[]>) => response.body)
        //            )
        //            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.blogService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IBlog[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IBlog[]>) => response.body)
        //            )
        //            .subscribe((res: IBlog[]) => (this.blogs = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.tagService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<ITag[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<ITag[]>) => response.body)
        //            )
        //            .subscribe((res: ITag[]) => (this.tags = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.topicService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<ITopic[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<ITopic[]>) => response.body)
        //            )
        //            .subscribe((res: ITopic[]) => (this.topics = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.dataUtils.clearInputImage(this.post, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.post.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        this.post.publicationDate = this.publicationDate != null ? moment(this.publicationDate, DATE_TIME_FORMAT) : null;
        if (this.post.id !== undefined) {
            this.subscribeToSaveResponse(this.postService.update(this.post));
        } else {
            this.subscribeToSaveResponse(this.postService.create(this.post));
        }
    }

    private myCommunities(currentAccount) {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.communityService.query(query).subscribe(
            (res: HttpResponse<ICommunity[]>) => {
                this.communities = res.body;
                console.log('CONSOLOG: M:myCommunities & O: this.communities : ', this.communities);
                this.communitiesBlogs(this.communities);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected communitiesBlogs(communities) {
        const query = {};
        if (this.communities != null) {
            const arrayCommmunities = [];
            this.communities.forEach(community => {
                arrayCommmunities.push(community.id);
            });
            query['communityId.in'] = arrayCommmunities;
        }
        this.blogService.query(query).subscribe(
            (res: HttpResponse<IBlog[]>) => {
                this.blogs = res.body;
                console.log('CONSOLOG: M:myCommunities & O: this.blogs : ', this.blogs);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPost>>) {
        result.subscribe((res: HttpResponse<IPost>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBlogById(index: number, item: IBlog) {
        return item.id;
    }

    trackTagById(index: number, item: ITag) {
        return item.id;
    }

    trackTopicById(index: number, item: ITopic) {
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

    get post() {
        return this._post;
    }

    set post(post: IPost) {
        this._post = post;
        this.creationDate = moment(post.creationDate).format(DATE_TIME_FORMAT);
        this.publicationDate = moment(post.publicationDate).format(DATE_TIME_FORMAT);
    }
}
