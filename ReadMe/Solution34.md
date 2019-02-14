# Problem 34: How to sanitize Youtube links in Angular 7: DomSanitizer


This issue is quite specific, but if you want to use YouTube videos in the homepage brought from the database, you will have to modify the [src]="saveUrl(frontPageConfig.recentVideos1.linkURL)":


	          <!-- Article Video -->
          <div class="col-lg-4 col-sm-6 g-mb-30">
            <article>
              <figure class="u-shadow-v25 g-pos-rel g-mb-20">
              	<iframe class="img-fluid w-100" [src]="saveUrl(frontPageConfig.recentVideos1.linkURL)" width="560" height="315" frameborder="0" allowfullscreen></iframe>
              </figure>

              <h3 class="g-font-size-16 g-mb-10">
                <a class="u-link-v5 g-color-gray-dark-v1 g-color-primary--hover" href="{{frontPageConfig.recentVideos1.linkURL}}">{{frontPageConfig.recentVideos1.linkText}}</a>
              </h3>
            </article>
          </div>
          <!-- End Article Video -->


with a function that use the import { DomSanitizer } from '@angular/platform-browser'; create a variable like: safeURL: SafeResourceUrl; and use it in the method saveUrl(url):


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
	
	import { LoginModalService, Principal, Account } from 'app/core';
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
        private frontpageconfigService: FrontpageconfigService,
        private topicService: TopicService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private parseLinks: JhiParseLinks,
        private activatedRoute: ActivatedRoute,
        private principal: Principal,
        private loginModalService: LoginModalService,
        private _sanitizer: DomSanitizer
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
	
