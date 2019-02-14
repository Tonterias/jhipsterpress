# Problem 24: How to combine two entities together: Post and Comments (create and read).

You have a good example of how to combine the PostDetailComponent with the list of comments (comment.component.ts) in the post-detail.component.ts:

As you see, we added the IComment and CommentService adding what you can find in the comment.component.ts such as: paging variables (including constructor initialization), loadPage(), loadAll(), transition(), clear(), trackId(), registerChangeInComments(), sort() and paginateComments() methods, so basically it is a mix of the logic of both the post and comment components.

You can see the pre-mix files in the old commit files like: https://github.com/Tonterias/JhipsterPress/blob/e529325fcc553f5f0e6aa71ece13a31cc84c9b20/src/main/webapp/app/entities/post/post-detail.component.ts

- loadPage() changes to start with the initial page.
- loadAll() uses an observable to ask swagger for the comments that belong to this post.id (in pages).

As you can see the insert of a comment is included in the save() method, too.

	import { Component, OnInit } from '@angular/core';
	import { ActivatedRoute, Router } from '@angular/router';
	import { JhiDataUtils } from 'ng-jhipster';
	import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
	import { Observable } from 'rxjs';
	import * as moment from 'moment';
	import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
	import { ITEMS_PER_PAGE } from 'app/shared';
	import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
	import { Subscription } from 'rxjs';
	import { Principal } from 'app/core';
	
	import { IComment } from 'app/shared/model/comment.model';
	import { CommentService } from 'app/entities/comment';
	import { IPost } from 'app/shared/model/post.model';
	import { PostService } from 'app/entities/post';
	import { IProfile } from 'app/shared/model/profile.model';
	import { ProfileService } from 'app/entities/profile';
	
	@Component({
	    selector: 'jhi-post-detail',
	    templateUrl: './post-detail.component.html'
	})
	export class PostDetailComponent implements OnInit {
	    id: any;
	    private _comment: IComment;
	    isSaving: boolean;
	
	    post: any;
	    posts: IPost[];
	
	    profile: IProfile;
	    profiles: IProfile[];
	
	    currentAccount: any;
	    creationDate: string;
	
	    comments: IComment[];
	    error: any;
	    success: any;
	    eventSubscriber: Subscription;
	    routeData: any;
	    links: any;
	    totalItems: any;
	    queryCount: any;
	    itemsPerPage: any;
	    page: any = 1;
	    predicate: any = 'id';
	    previousPage: any = 0;
	    reverse: any = 'asc';
	
	    constructor(
	        private dataUtils: JhiDataUtils,
	        private parseLinks: JhiParseLinks,
	        private jhiAlertService: JhiAlertService,
	        private commentService: CommentService,
	        private postService: PostService,
	        private principal: Principal,
	        private profileService: ProfileService,
	        private activatedRoute: ActivatedRoute,
	        private router: Router,
	        private eventManager: JhiEventManager
	    ) {
	        this.itemsPerPage = ITEMS_PER_PAGE;
	        this.routeData = this.activatedRoute.data.subscribe(data => {
	            this.page = 0;
	            this.previousPage = 0;
	            this.reverse = false;
	            this.predicate = 'id';
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
	        this.loadAll();
	        this.principal.identity().then(account => {
	            this.currentAccount = account;
	//            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount.id);
	        });
	        this.comment = new Object();
	        this.comment.commentText = '';
	        this.registerChangeInComments();
	//        console.log('CONSOLOG: M:ngOnInit & O: this.comments : ', this.comments);
	    }
	
	    save() {
	        this.isSaving = true;
	        this.comment.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
	        if (this.comment.id !== undefined) {
	            this.subscribeToSaveResponse(this.commentService.update(this.comment));
	        } else {
	            this.comment.postId = this.post.id;
	            this.loggedProfile()
	            .subscribe(
	                    (res: HttpResponse<IProfile[]>) => {
	                        this.profiles = res.body;
	                        this.comment.profileId = this.profiles[0].id;
	                        this.comment.isOffensive = false;
	//                        console.log('CONSOLOG: M:save & O: this.comment : ', this.comment);
	                        this.subscribeToSaveResponse(this.commentService.create(this.comment));
	                    },
	                    (res: HttpErrorResponse) => this.onError(res.message)
	            );
	        }
	    }
	
	    private loggedProfile() {
	        const query = {
	            };
	        if ( this.currentAccount.id  != null) {
	            query['userId.equals'] = this.currentAccount.id;
	        }
	        return this.profileService
	            .query(query);
	    }
	
	    private subscribeToSaveResponse(result: Observable<HttpResponse<IComment>>) {
	        result.subscribe((res: HttpResponse<IComment>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
	    }
	
	    private onSaveSuccess() {
	        this.isSaving = false;
	        this.previousState();
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
	
	    trackProfileById(index: number, item: IProfile) {
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
	        this.commentService
	            .query(query)
	            .subscribe(
	                (res: HttpResponse<IComment[]>) => {
	                    console.log('CONSOLOG: M:loadAll & O: query : ', query);
	                    this.paginateComments(res.body, res.headers);
	//                    console.log('CONSOLOG: M:loadAll & O: res.body : ', res.body);
	                },
	                (res: HttpErrorResponse) => this.onError(res.message)
	            );
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
	        this.queryCount = this.totalItems;
	        this.comments = data;
	//        console.log('CONSOLOG: M:paginateComments & O: this.comments : ', this.comments);
	    }
	}










