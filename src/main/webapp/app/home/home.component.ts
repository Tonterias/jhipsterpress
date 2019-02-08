import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IFrontpageconfig } from 'app/shared/model/frontpageconfig.model';
import { FrontpageconfigService } from '../entities/frontpageconfig/frontpageconfig.service';
import { ICustomFrontpageconfig } from 'app/shared/model/customfrontpageconfig.model';

import { ITopic } from 'app/shared/model/topic.model';
import { TopicService } from '../entities/topic/topic.service';

import { ITEMS_PER_PAGE } from 'app/shared';

import { LoginModalService, AccountService, Account } from 'app/core';
import { DomSanitizer } from '@angular/platform-browser';
import { SafeResourceUrl } from '@angular/platform-browser';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit, OnDestroy {
    safeURL: SafeResourceUrl;
    account: Account;
    modalRef: NgbModalRef;

    frontpageconfigs: ICustomFrontpageconfig[];
    topics: ITopic[];

    currentAccount: any;
    eventSubscriber: Subscription;
    itemsPerPage: number;
    links: any;
    page: any;
    predicate: any;
    queryCount: any;
    reverse: any;
    totalItems: number;
    currentSearch: string;
    id: number;

    constructor(
        protected frontpageconfigService: FrontpageconfigService,
        protected topicService: TopicService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected parseLinks: JhiParseLinks,
        protected activatedRoute: ActivatedRoute,
        protected accountService: AccountService,
        protected loginModalService: LoginModalService,
        protected _sanitizer: DomSanitizer
    ) {
        this.frontpageconfigs = [];
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.page = 0;
        this.links = {
            last: 0
        };
        this.predicate = 'id';
        this.reverse = true;
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    saveUrl(url) {
        return this._sanitizer.bypassSecurityTrustResourceUrl(url);
    }

    loadAll() {
        this.frontpageconfigService
            .findIncludingPosts(1)
            .subscribe(
                (res: HttpResponse<ICustomFrontpageconfig>) => this.paginateFrontpageconfigs(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    reset() {
        this.page = 0;
        this.frontpageconfigs = [];
        this.loadAll();
    }

    loadPage(page) {
        this.page = page;
        this.loadAll();
    }

    clear() {
        this.frontpageconfigs = [];
        this.links = {
            last: 0
        };
        this.page = 0;
        this.predicate = 'id';
        this.reverse = true;
        this.currentSearch = '';
        this.loadAll();
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.frontpageconfigs = [];
        this.links = {
            last: 0
        };
        this.page = 0;
        this.predicate = '_score';
        this.reverse = false;
        this.currentSearch = query;
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.topicService.query().subscribe(
            (res: HttpResponse<ITopic[]>) => {
                this.topics = res.body;
                console.log('CONSOLOG: M:communitiesBlogs & O: this.blogs : ', this.topics);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.registerAuthenticationSuccess();
        this.registerChangeInFrontpageconfigs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IFrontpageconfig) {
        return item.id;
    }

    registerChangeInFrontpageconfigs() {
        this.eventSubscriber = this.eventManager.subscribe('frontpageconfigListModification', response => this.reset());
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.accountService.identity().then(account => {
                this.account = account;
            });
        });
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateFrontpageconfigs(data: ICustomFrontpageconfig, headers: HttpHeaders) {
        // this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = 1;
        this.frontpageconfigs.push(data);
        console.log('CONSOLOG: M:paginateFrontpageconfigs & O: this.frontpageconfigs : ', this.frontpageconfigs);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    isAuthenticated() {
        return this.accountService.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
