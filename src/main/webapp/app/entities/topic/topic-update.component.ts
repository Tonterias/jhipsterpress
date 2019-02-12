import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from './topic.service';
import { IPost } from 'app/shared/model/post.model';
import { PostService } from 'app/entities/post';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-topic-update',
    templateUrl: './topic-update.component.html'
})
export class TopicUpdateComponent implements OnInit {
    topic: ITopic;
    topics: ITopic[];
    isSaving: boolean;
    isCreateDisabled = false;

    posts: IPost[];

    nameParamPost: any;
    valueParamPost: any;

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
        protected topicService: TopicService,
        protected postService: PostService,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.postIdEquals != null) {
                this.nameParamPost = 'postId.equals';
                this.valueParamPost = params.postIdEquals;
            }
            console.log('CONSOLOG: M:constructor & O: this.nameParamPost : ', this.nameParamPost);
            console.log('CONSOLOG: M:constructor & O: this.valueParamPost : ', this.valueParamPost);
            console.log('CONSOLOG: M:constructor & O: this.itemsPerPage : ', this.itemsPerPage);
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ topic }) => {
            this.topic = topic;
            console.log('CONSOLOG: M:ngOnInit & O: this.topic : ', this.topic);
            console.log('CONSOLOG: M:ngOnInit & O: this.predicate : ', this.predicate);
        });
        if (this.valueParamPost != null) {
            const query = {};
            query['id.equals'] = this.valueParamPost;
            this.postService.query(query).subscribe(
                (res: HttpResponse<IPost[]>) => {
                    this.posts = res.body;
                    console.log('CONSOLOG: M:ngOnInit & O: this.posts if1 : ', this.posts);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        } else {
            this.postService.query().subscribe(
                (res: HttpResponse<IPost[]>) => {
                    this.posts = res.body;
                    console.log('CONSOLOG: M:ngOnInit & O: this.posts else2 : ', this.posts);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
        //        this.postService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IPost[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IPost[]>) => response.body)
        //            )
        //            .subscribe((res: IPost[]) => (this.posts = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.topic.id !== undefined) {
            this.subscribeToSaveResponse(this.topicService.update(this.topic));
        } else {
            this.topic.posts = this.posts;
            console.log('CONSOLOG: M:save & O: this.posts : ', this.posts);
            this.subscribeToSaveResponse(this.topicService.create(this.topic));
        }
    }

    loadAll() {
        console.log('CONSOLOG: M:loadAll & O: this.currentSearch : ', this.currentSearch);
        if (this.currentSearch) {
            this.topicService
                .query({
                    page: this.page - 1,
                    query: this.currentSearch,
                    size: this.itemsPerPage,
                    sort: this.sort()
                })
                .subscribe(
                    (res: HttpResponse<ITopic[]>) => this.paginateCinterests(res.body, res.headers),
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
            return;
        }
        this.topicService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<ITopic[]>) => this.paginateCinterests(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    addExistingTopic2Post(topicId) {
        console.log(
            'CONSOLOG: M:addExistingProfileInterest & interestId: ',
            topicId,
            ', uprofileId : ',
            this.nameParamPost,
            ' &:',
            this.valueParamPost
        );
        this.isSaving = true;
        if (topicId !== undefined) {
            const query = {};
            query['id.equals'] = topicId;
            console.log('CONSOLOG: M:addExistingProfileInterest & O: query : ', query);
            this.topicService.query(query).subscribe(
                (res: HttpResponse<ITopic[]>) => {
                    this.topics = res.body;
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: res.body : ', res.body);
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: this.topics : ', this.topics);
                    const query2 = {};
                    if (this.valueParamPost != null) {
                        query2['id.equals'] = this.valueParamPost;
                    }
                    console.log('CONSOLOG: M:addExistingProfileInterest & O: query2 : ', query2);
                    this.postService.query(query2).subscribe(
                        (res2: HttpResponse<IPost[]>) => {
                            this.topics[0].posts.push(res2.body[0]);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: res2.body : ', res2.body);
                            console.log('CONSOLOG: M:addExistingProfileInterest & O: this.topics : ', this.topics);
                            this.subscribeToSaveResponse(this.topicService.update(this.topics[0]));
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
        this.router.navigate(['/topic/new'], {
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
            '/topic/new',
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
            '/topic/new',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    private paginateCinterests(data: IPost[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.topics = data;
        if (this.totalItems === 0) {
            this.topic.topicName = this.currentSearch;
        }
        console.log('CONSOLOG: M:paginateActivities & O: this.totalItems : ', this.totalItems);
        console.log('CONSOLOG: M:paginateActivities & O: this.interests : ', this.topics);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ITopic>>) {
        result.subscribe((res: HttpResponse<ITopic>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.router.navigate(['/post/', this.valueParamPost, 'view']);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPostById(index: number, item: IPost) {
        return item.id;
    }

    trackId(index: number, item: ITopic) {
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
