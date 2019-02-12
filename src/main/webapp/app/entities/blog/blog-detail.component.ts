import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IBlog } from 'app/shared/model/blog.model';
import { IPost } from 'app/shared/model/post.model';
import { PostService } from 'app/entities/post';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-blog-detail',
    templateUrl: './blog-detail.component.html'
})
export class BlogDetailComponent implements OnInit, OnDestroy {
    blog: IBlog;

    post: any;
    posts: IPost[];

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
        protected postService: PostService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected dataUtils: JhiDataUtils,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ blog }) => {
            this.blog = blog;
            this.id = this.blog.id;
            console.log('CONSOLOG: M:ngOnInit & O: this.blog : ', this.blog);
            this.loadAll();
        });
        this.registerChangeInPosts();
    }

    protected communitiesPosts(blog) {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        if (this.blog != null) {
            query['blogId.equals'] = blog.id;
        }
        this.postService.query(query).subscribe(
            (res: HttpResponse<IPost[]>) => {
                this.posts = res.body;
                console.log('CONSOLOG: M:communitiesPosts & O: this.posts : ', this.posts);
                this.paginatePosts(res.body, res.headers);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    loadAll() {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        if (this.blog != null) {
            query['blogId.equals'] = this.blog.id;
        }
        this.postService.query(query).subscribe(
            (res: HttpResponse<IPost[]>) => {
                this.posts = res.body;
                console.log('CONSOLOG: M:communitiesPosts & O: this.posts : ', this.posts);
                this.paginatePosts(res.body, res.headers);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        //                if (this.currentSearch) {
        //                    this.postService
        //                        .search({
        //                            page: this.page - 1,
        //                            query: this.currentSearch,
        //                            size: this.itemsPerPage,
        //                            sort: this.sort()
        //                        })
        //                        .subscribe(
        //                            (res: HttpResponse<IPost[]>) => this.paginatePosts(res.body, res.headers),
        //                            (res: HttpErrorResponse) => this.onError(res.message)
        //                        );
        //                    return;
        //                }
        //                this.postService
        //                    .query({
        //                        page: this.page - 1,
        //                        size: this.itemsPerPage,
        //                        sort: this.sort()
        //                    })
        //                    .subscribe(
        //                        (res: HttpResponse<IPost[]>) => this.paginatePosts(res.body, res.headers),
        //                        (res: HttpErrorResponse) => this.onError(res.message)
        //                    );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/blog/', this.id, '/view'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                //                search: this.currentSearch,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        //        this.currentSearch = '';
        this.router.navigate([
            '/blog/',
            this.id,
            '/view',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    //    search(query) {
    //        if (!query) {
    //            return this.clear();
    //        }
    //        this.page = 0;
    //        this.currentSearch = query;
    //        this.router.navigate([
    //            '/post',
    //            {
    //                search: this.currentSearch,
    //                page: this.page,
    //                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
    //            }
    //        ]);
    //        this.loadAll();
    //    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPost) {
        return item.id;
    }

    registerChangeInPosts() {
        this.eventSubscriber = this.eventManager.subscribe('postListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginatePosts(data: IPost[], headers: HttpHeaders) {
        console.log('!!!!!!!!!!!!!', data, headers);
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.posts = data;
    }

    protected onError(errorMessage: string) {
        console.log('!!!!!!!!!****', errorMessage);
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
