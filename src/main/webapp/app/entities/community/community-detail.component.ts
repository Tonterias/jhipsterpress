import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { JhiAlertService, JhiDataUtils, JhiParseLinks, JhiEventManager } from 'ng-jhipster';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICommunity } from 'app/shared/model/community.model';
import { IBlog } from 'app/shared/model/blog.model';
import { BlogService } from 'app/entities/blog';
import { IFollow } from 'app/shared/model/follow.model';
import { FollowService } from '../follow/follow.service';
import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from 'app/entities/uprofile';
import { INotification } from 'app/shared/model/notification.model';
import { NotificationService } from '../notification/notification.service';
import { ICinterest } from 'app/shared/model/cinterest.model';
import { CinterestService } from '../cinterest/cinterest.service';
import { ICactivity } from 'app/shared/model/cactivity.model';
import { CactivityService } from '../cactivity/cactivity.service';
import { ICceleb } from 'app/shared/model/cceleb.model';
import { CcelebService } from '../cceleb/cceleb.service';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-community-detail',
    templateUrl: './community-detail.component.html'
})
export class CommunityDetailComponent implements OnInit {
    community: ICommunity;
    blogs: IBlog[];

    follows: IFollow[];
    private _follow: IFollow;

    profile: IUprofile;
    profiles: IUprofile[];

    cinterests: ICinterest[];
    cactivities: ICactivity[];
    ccelebs: ICceleb[];

    loggedProfile: IUprofile;

    currentAccount: any;
    isFollowing: boolean;

    loggedProfileId: number;
    creationDate: string;
    isSaving: boolean;

    userId: number;
    private _notification: INotification;
    notificationDate: string;
    notificationReason: any;
    owner: any;

    error: any;
    success: any;
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
        protected blogService: BlogService,
        protected followService: FollowService,
        protected uprofileService: UprofileService,
        protected notificationService: NotificationService,
        protected cinterestService: CinterestService,
        protected cactivityService: CactivityService,
        protected ccelebService: CcelebService,
        protected dataUtils: JhiDataUtils,
        protected parseLinks: JhiParseLinks,
        protected accountService: AccountService,
        protected jhiAlertService: JhiAlertService,
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
        this.activatedRoute.data.subscribe(({ community }) => {
            this.community = community;
            this.communitiesBlogs(community);
            this.userId = community.userId;
            //                        console.log('CONSOLOG: M:ngOnInit & O: this.community : ', this.community);
        });
        this.communityInterests();
        this.communityActivities();
        this.communityCelebs();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.owner = account.id;
            //                        console.log('CONSOLOG: M:paginateProfiles & O: this.owner : ', this.owner);
            this.isFollower();
        });
        this.isSaving = false;
        this.follow = new Object();
    }

    private communitiesBlogs(community) {
        const query = {};
        if (this.community != null) {
            query['communityId.in'] = community.id;
        }
        this.blogService.query(query).subscribe(
            (res: HttpResponse<IBlog[]>) => {
                //                this.blogs = res.body;
                this.paginateBlogs(res.body, res.headers);
                //                console.log('CONSOLOG: M:communitiesBlogs & O: this.blogs : ', this.blogs);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private communityInterests() {
        const query2 = {};
        query2['communityId.equals'] = this.community.id;
        return this.cinterestService.query(query2).subscribe(
            (res: HttpResponse<ICinterest[]>) => {
                this.cinterests = res.body;
                //                console.log('CONSOLOG: M:umxmInterests & O: this.cinterests : ', this.cinterests);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private communityActivities() {
        const query3 = {};
        query3['communityId.equals'] = this.community.id;
        return this.cactivityService.query(query3).subscribe(
            (res: HttpResponse<ICactivity[]>) => {
                this.cactivities = res.body;
                //                console.log('CONSOLOG: M:umxmActivities & O: this.cactivities : ', this.cactivities);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private communityCelebs() {
        const query4 = {};
        query4['communityId.equals'] = this.community.id;
        return this.ccelebService.query(query4).subscribe(
            (res: HttpResponse<ICceleb[]>) => {
                this.ccelebs = res.body;
                //                console.log('CONSOLOG: M:umxmCelebs & O: this.ccelebs : ', this.ccelebs);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private isFollower() {
        this.isFollowing = false;
        const query = {};
        if (this.currentAccount.id != null) {
            query['followedId.in'] = this.currentAccount.id;
            query['cfollowingId.in'] = this.community.id;
        }
        return this.followService.query(query);
    }

    following() {
        this.isSaving = true;
        this.follow.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.follow.followedId = this.currentAccount.id;
        this.follow.cfollowingId = this.community.id;
        if (this.isFollowing === false) {
            //            console.log('CONSOLOG: M:following & O: this.follow : ', this.follow);
            this.subscribeToSaveResponse(this.followService.create(this.follow));
            this.notificationReason = 'FOLLOWING';
            this.createNotification(this.notificationReason);
            this.isFollowing = true;
        }
    }

    unFollowing() {
        if (this.isFollowing === true) {
            this.isFollower().subscribe(
                (res: HttpResponse<IFollow[]>) => {
                    this.follows = res.body;
                    if (this.follows.length > 0) {
                        this.isFollowing = true;
                        // return this.follows[0];
                        //                        console.log('CONSOLOG: M:unFollowing & O: this.follows[0].id : ', this.follows[0].id);
                        this.followService.delete(this.follows[0].id).subscribe(response => {
                            this.notificationReason = 'UNFOLLOWING';
                            this.createNotification(this.notificationReason);
                            this.isFollowing = false;
                        });
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    deleteItemFromList() {
        //        console.log('CONSOLOG: M:deleteItemFromList : ');
    }

    private createNotification(notificationReason) {
        this.notification = new Object();
        //        console.log('CONSOLOG: M:createNotification & O: this.notification : ', this.notification);
        //        console.log('CONSOLOG: M:createNotification & O: this.userId : ', this.userId);
        this.isSaving = true;
        this.notification.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.notification.notificationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.notification.notificationReason = notificationReason;
        //        this.notification.notificationText = notificationReason + ': ' + this.profile.lastName + ' ' + profile.lastName;
        this.notification.notificationText = notificationReason;
        this.notification.isDelivered = false;
        this.notification.userId = this.userId;
        if (this.notification.id !== undefined) {
            this.subscribeToSaveResponse2(this.notificationService.update(this.notification));
        } else {
            //            console.log('CONSOLOG: M:createNotification & O: this.notification: ', this.notification);
            this.subscribeToSaveResponse2(this.notificationService.create(this.notification));
        }
    }

    removeCommunityCinterest(cinterestId, communityId) {
        //        console.log('CONSOLOG: M:removeProfileInterest & cinterestId: ', cinterestId, ', uprofileId : ', communityId);
        //        console.log('CONSOLOG: M:removeProfileInterest & O: this.interests : ', this.cinterests);
        this.cinterests.forEach(cinterest => {
            //            console.log( 'CONSOLOG: M:removeProfileInterest; & this.interest: ', interest );
            if (cinterest.id === cinterestId) {
                //                console.log( 'CONSOLOG: M:removeProfileInterest; & interest.id COINCIDENTE: ', interest.id, 'interest:', interest );
                cinterest.communities.forEach(community => {
                    //                    console.log( 'CONSOLOG: M:removeProfileInterest; & this.uprofile: ', uprofile );
                    if (community.id === communityId) {
                        //                        console.log('CONSOLOG: M:removeProfileInterest; INDEX!!!!!: ', cinterest.communities.indexOf(community));
                        cinterest.communities.splice(cinterest.communities.indexOf(community), 1);
                        this.subscribeToSaveResponse3(this.cinterestService.update(cinterest));
                        this.cinterests.splice(cinterest.communities.indexOf(community), 1);
                    }
                });
            }
        });
    }

    removeCommunityCactivity(cactivityId, communityId) {
        //        console.log('CONSOLOG: M:removeProfileInterest & cactivityId: ', cactivityId, ', uprofileId : ', communityId);
        //        console.log('CONSOLOG: M:removeProfileInterest & O: this.cactivities : ', this.cactivities);
        this.cactivities.forEach(cactivity => {
            //            console.log( 'CONSOLOG: M:removeProfileInterest; & this.interest: ', interest );
            if (cactivity.id === cactivityId) {
                //                console.log( 'CONSOLOG: M:removeProfileInterest; & interest.id COINCIDENTE: ', interest.id, 'interest:', interest );
                cactivity.communities.forEach(community => {
                    //                    console.log( 'CONSOLOG: M:removeProfileInterest; & this.uprofile: ', uprofile );
                    if (community.id === communityId) {
                        //                        console.log('CONSOLOG: M:removeProfileInterest; INDEX!!!!!: ', cactivity.communities.indexOf(community));
                        cactivity.communities.splice(cactivity.communities.indexOf(community), 1);
                        this.subscribeToSaveResponse3(this.cactivityService.update(cactivity));
                        this.cactivities.splice(cactivity.communities.indexOf(community), 1);
                    }
                });
            }
        });
    }

    removeCommunityCceleb(ccelebId, communityId) {
        //        console.log('CONSOLOG: M:removeProfileInterest & ccelebId: ', ccelebId, ', uprofileId : ', communityId);
        //        console.log('CONSOLOG: M:removeProfileInterest & O: this.ccelebs : ', this.ccelebs);
        this.ccelebs.forEach(cceleb => {
            //            console.log( 'CONSOLOG: M:removeProfileInterest; & this.interest: ', interest );
            if (cceleb.id === ccelebId) {
                //                console.log( 'CONSOLOG: M:removeProfileInterest; & interest.id COINCIDENTE: ', interest.id, 'interest:', interest );
                cceleb.communities.forEach(community => {
                    //                    console.log( 'CONSOLOG: M:removeProfileInterest; & this.uprofile: ', uprofile );
                    if (community.id === communityId) {
                        //                        console.log('CONSOLOG: M:removeProfileInterest; INDEX!!!!!: ', cceleb.communities.indexOf(community));
                        cceleb.communities.splice(cceleb.communities.indexOf(community), 1);
                        this.subscribeToSaveResponse3(this.ccelebService.update(cceleb));
                        this.ccelebs.splice(cceleb.communities.indexOf(community), 1);
                    }
                });
            }
        });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFollow>>) {
        result.subscribe((res: HttpResponse<IFollow>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private subscribeToSaveResponse2(result: Observable<HttpResponse<INotification>>) {
        result.subscribe((res: HttpResponse<INotification>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private subscribeToSaveResponse3(result: Observable<HttpResponse<ICinterest>>) {
        result.subscribe((res: HttpResponse<ICinterest>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private subscribeToSaveResponse4(result: Observable<HttpResponse<ICactivity>>) {
        result.subscribe((res: HttpResponse<ICactivity>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private subscribeToSaveResponse5(result: Observable<HttpResponse<ICceleb>>) {
        result.subscribe((res: HttpResponse<ICceleb>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveSuccess2() {
        this.isSaving = false;
        //        this.reload();
    }

    private onSaveError() {
        this.isSaving = false;
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

    private paginateBlogs(data: IBlog[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.blogs = data;
        //        console.log('CONSOLOG: M:paginateBlogs & O: this.blogs : ', this.blogs);
    }

    loadPage(page) {
        this.previousPage = page;
        this.page = page;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    get follow() {
        return this._follow;
    }

    set follow(follow: IFollow) {
        this._follow = follow;
        this.creationDate = moment().format(DATE_TIME_FORMAT);
    }

    get notification() {
        return this._notification;
    }

    set notification(notification: INotification) {
        this._notification = notification;
        this.creationDate = moment().format(DATE_TIME_FORMAT);
        this.notificationDate = moment().format(DATE_TIME_FORMAT);
        this.notificationReason = '';
    }
}
