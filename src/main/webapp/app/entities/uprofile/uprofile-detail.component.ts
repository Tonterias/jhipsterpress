import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from 'app/entities/uprofile';
import { FollowService } from '../follow/follow.service';
import { IFollow } from 'app/shared/model/follow.model';
import { IBlockuser } from 'app/shared/model/blockuser.model';
import { BlockuserService } from '../blockuser/blockuser.service';
import { INotification } from 'app/shared/model/notification.model';
import { NotificationService } from '../notification/notification.service';
import { IUser, UserService } from 'app/core';
import { IInterest } from 'app/shared/model/interest.model';
import { InterestService } from '../interest/interest.service';
import { IActivity } from 'app/shared/model/activity.model';
import { ActivityService } from '../activity/activity.service';
import { ICeleb } from 'app/shared/model/celeb.model';
import { CelebService } from '../celeb/celeb.service';

import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-uprofile-detail',
    templateUrl: './uprofile-detail.component.html'
})
export class UprofileDetailComponent implements OnInit {
    uprofile: IUprofile;
    uprofiles: IUprofile[] = [];
    loggedProfile: IUprofile[];

    follows: IFollow[];
    private _follow: IFollow;

    blockusers: IBlockuser[];
    private _blockuser: IBlockuser;

    interests: IInterest[] = [];
    activities: IActivity[];
    celebs: ICeleb[];

    consultedUser: IUser;
    consultedUserId: number;
    loggedProfileId: number;
    private _notification: INotification;
    notificationDate: string;
    notificationReason: any;

    currentAccount: any;
    isFollowing: boolean;
    isBlocked: boolean;
    loggedUserId: number;
    creationDate: string;
    isSaving: boolean;
    owner: any;
    nameParamUserId: any;
    valueParamUserId: any;
    uprofileUserId: any;
    uprofileUserFirstName: string;
    uprofileUserLastName: string;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected accountService: AccountService,
        protected uprofileService: UprofileService,
        protected interestService: InterestService,
        protected activityService: ActivityService,
        protected celebService: CelebService,
        protected userService: UserService,
        protected followService: FollowService,
        protected blockuserService: BlockuserService,
        protected notificationService: NotificationService,
        protected jhiAlertService: JhiAlertService,
        protected activatedRoute: ActivatedRoute
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.userIdEquals != null) {
                this.nameParamUserId = 'userId.equals';
                this.valueParamUserId = params.userIdEquals;
                console.log('CONSOLOG: M:activatedRoute & O: this.nameParamUserId : ', this.nameParamUserId);
                console.log('CONSOLOG: M:activatedRoute & O: this.valueParamUserId : ', this.valueParamUserId);
            }
        });
    }

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.owner = account.id;
            console.log('CONSOLOG: M:paginateProfiles & O: this.owner : ', this.owner);
            this.currentLoggedProfile();
        });
        if (this.valueParamUserId != null) {
            console.log('CONSOLOG: TENGO DATO DE UPROFILE VIA this.valueParamUserId consultedUserId');
            console.log('CONSOLOG: M:ngOnInit & O: this.valueParamUserId : ', this.valueParamUserId);
            this.consultedUserId = this.valueParamUserId;
            const query = {};
            query['userId.equals'] = this.valueParamUserId;
            this.uprofileService.query(query).subscribe(
                (res: HttpResponse<IUprofile[]>) => {
                    this.uprofile = res.body[0];
                    console.log('CONSOLOG: M:ngOnInit & O: this.uprofile1 : ', this.uprofile);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        } else {
            this.activatedRoute.data.subscribe(({ uprofile }) => {
                this.uprofile = uprofile;
                this.consultedUserId = uprofile.userId;
                console.log('CONSOLOG: M:ngOnInit & O: this.uprofile2 : ', this.uprofile);
            });
        }
        this.fillProfile();
        this.isSaving = false;
        this.follow = new Object();
        this.blockuser = new Object();
    }

    protected fillProfile() {
        console.log('CONSOLOG: M:fillProfile!!!!!!!!!!!!!!!!!!!!!!!!!! en fillProfile1');
        this.consultProfile().subscribe(
            (res: HttpResponse<IUser>) => {
                this.consultedUser = res.body;
                this.uprofileUserFirstName = res.body.firstName;
                this.uprofileUserLastName = res.body.lastName;
                console.log('CONSOLOG: M:fillProfile & O: this.consultedUser : ', this.consultedUser);
                console.log('CONSOLOG: M:fillProfile!!!!!!!!!!!!!!!!!!!!!!!!!! en fillProfile2');
                this.uprofileInterests();
                this.uprofileActivities();
                this.uprofileCelebs();
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected consultProfile() {
        console.log('CONSOLOG: M:consultProfile!!!!!!!!!!!!!!!!!!!!!!!!!! en consultedUserId', this.consultedUserId);
        return this.userService.findById(this.consultedUserId);
    }

    protected uprofileInterests() {
        const query2 = {};
        query2['uprofileId.equals'] = this.uprofile.id;
        return this.interestService.query(query2).subscribe(
            (res: HttpResponse<IInterest[]>) => {
                this.interests = res.body;
                console.log('CONSOLOG: M:uprofileInterests & O: this.interests : ', this.interests);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected uprofileActivities() {
        const query3 = {};
        query3['uprofileId.equals'] = this.uprofile.id;
        return this.activityService.query(query3).subscribe(
            (res: HttpResponse<IActivity[]>) => {
                this.activities = res.body;
                console.log('CONSOLOG: M:uprofileActivities & O: this.activities : ', this.activities);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected uprofileCelebs() {
        const query4 = {};
        query4['uprofileId.equals'] = this.uprofile.id;
        return this.celebService.query(query4).subscribe(
            (res: HttpResponse<ICeleb[]>) => {
                this.celebs = res.body;
                console.log('CONSOLOG: M:uprofileCelebs & O: this.celebs : ', this.celebs);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected currentLoggedProfile() {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.uprofileService.query(query).subscribe(
            (res: HttpResponse<IUprofile[]>) => {
                this.loggedProfile = res.body;
                this.loggedProfileId = res.body[0].id;
                console.log('CONSOLOG: M:currentLoggedProfile & O: this.loggedProfile : ', this.loggedProfile);
                this.loggedProfile.forEach(profile => {
                    this.loggedUserId = profile.userId;
                });
                this.isFollower().subscribe(
                    (res2: HttpResponse<IFollow[]>) => {
                        this.follows = res2.body;
                        if (this.follows.length > 0) {
                            this.isFollowing = true;
                            // return this.follows[0];
                        }
                    },
                    (res2: HttpErrorResponse) => this.onError(res2.message)
                );
                this.isBlockUser().subscribe(
                    (res3: HttpResponse<IBlockuser[]>) => {
                        this.blockusers = res3.body;
                        if (this.blockusers.length > 0) {
                            this.isBlocked = true;
                            return this.blockusers[0];
                        }
                    },
                    (res3: HttpErrorResponse) => this.onError(res3.message)
                );
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected isFollower() {
        this.isFollowing = false;
        const query2 = {};
        if (this.currentAccount.id != null) {
            query2['followedId.in'] = this.loggedUserId;
            query2['followingId.in'] = this.uprofile.userId;
        }
        return this.followService.query(query2);
    }

    following() {
        this.isSaving = true;
        this.follow.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.follow.followingId = this.uprofile.userId;
        this.follow.followedId = this.loggedUserId;
        if (this.isFollowing === false) {
            console.log('CONSOLOG: M:following & O: this.follow : ', this.follow);
            this.subscribeToSaveResponse(this.followService.create(this.follow));
            this.notificationReason = 'FOLLOWING';
            this.createNotification(this.notificationReason);
            this.isFollowing = true;
            //            this.reload();
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
                        console.log('CONSOLOG: M:unFollowing & O: this.follows[0].id : ', this.follows[0].id);
                        this.followService.delete(this.follows[0].id).subscribe(response => {
                            this.notificationReason = 'UNFOLLOWING';
                            this.createNotification(this.notificationReason);
                            this.isFollowing = false;
                        });
                        //                        this.reload();
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    protected createNotification(notificationReason) {
        this.notification = new Object();
        console.log('CONSOLOG: M:createNotification & O: this.notification : ', this.notification);
        console.log('CONSOLOG: M:createNotification & O: this.consultedUserId : ', this.consultedUserId);
        this.isSaving = true;
        this.notification.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.notification.notificationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.notification.notificationReason = notificationReason;
        this.notification.notificationText = notificationReason;
        this.notification.isDelivered = false;
        this.notification.userId = this.consultedUserId;
        if (this.notification.id !== undefined) {
            this.subscribeToSaveResponse2(this.notificationService.update(this.notification));
        } else {
            console.log('CONSOLOG: M:createNotification & O: this.notification: ', this.notification);
            this.subscribeToSaveResponse2(this.notificationService.create(this.notification));
        }
    }

    protected isBlockUser() {
        this.isBlocked = false;
        const query = {};
        if (this.currentAccount.id != null) {
            query['blockeduserId.in'] = this.uprofile.userId;
            query['blockinguserId.in'] = this.loggedUserId;
        }
        return this.blockuserService.query(query);
    }

    blocking() {
        this.isSaving = true;
        this.blockuser.creationDate = moment(this.creationDate, DATE_TIME_FORMAT);
        this.blockuser.blockeduserId = this.uprofile.userId;
        this.blockuser.blockinguserId = this.loggedUserId;
        if (this.isBlocked === false) {
            this.subscribeToSaveResponse(this.blockuserService.create(this.blockuser));
            this.isBlocked = true;
            //            this.reload();
        }
    }

    unBlocking() {
        if (this.isBlocked === true) {
            this.isBlockUser().subscribe(
                (res4: HttpResponse<IBlockuser[]>) => {
                    this.blockusers = res4.body;
                    if (this.blockusers.length > 0) {
                        this.isBlocked = true;
                        console.log('CONSOLOG: M:unBlocking & O2: this.blockusers[0].id : ', this.blockusers[0].id);
                        this.blockuserService.delete(this.blockusers[0].id).subscribe(response => {});
                        this.isBlocked = false;
                        //                        this.reload();
                    }
                },
                (res4: HttpErrorResponse) => this.onError(res4.message)
            );
        }
    }

    removeProfileInterest(interestId, uprofileId) {
        console.log('CONSOLOG: M:removeProfileInterest & interestId: ', interestId, ', uprofileId : ', uprofileId);
        console.log('CONSOLOG: M:removeProfileInterest & O: this.interests : ', this.interests);
        this.interests.forEach(interest => {
            //            console.log( 'CONSOLOG: M:removeProfileInterest; & this.interest: ', interest );
            if (interest.id === interestId) {
                //                console.log( 'CONSOLOG: M:removeProfileInterest; & interest.id COINCIDENTE: ', interest.id, 'interest:', interest );
                interest.uprofiles.forEach(uprofile => {
                    //                    console.log( 'CONSOLOG: M:removeProfileInterest; & this.uprofile: ', uprofile );
                    if (uprofile.id === uprofileId) {
                        console.log('CONSOLOG: M:removeProfileInterest; INDEX!!!!!: ', interest.uprofiles.indexOf(uprofile));
                        interest.uprofiles.splice(interest.uprofiles.indexOf(uprofile), 1);
                        this.subscribeToSaveResponse3(this.interestService.update(interest));
                        this.interests.splice(interest.uprofiles.indexOf(uprofile), 1);
                    }
                });
            }
        });
    }

    removeProfileActivity(actvityId, uprofileId) {
        console.log('CONSOLOG: M:removeProfileInterest & actvityId: ', actvityId, ', uprofileId : ', uprofileId);
        console.log('CONSOLOG: M:removeProfileInterest & O: this.activities : ', this.activities);
        this.activities.forEach(activity => {
            if (activity.id === actvityId) {
                activity.uprofiles.forEach(uprofile => {
                    if (uprofile.id === uprofileId) {
                        activity.uprofiles.splice(activity.uprofiles.indexOf(uprofile), 1);
                        this.subscribeToSaveResponse4(this.activityService.update(activity));
                        this.activities.splice(activity.uprofiles.indexOf(uprofile), 1);
                    }
                });
            }
        });
    }

    removeProfileCeleb(celebId, uprofileId) {
        console.log('CONSOLOG: M:removeProfileInterest & celebId: ', celebId, ', uprofileId : ', uprofileId);
        console.log('CONSOLOG: M:removeProfileInterest & O: this.celebs : ', this.celebs);
        this.celebs.forEach(celeb => {
            if (celeb.id === celebId) {
                celeb.uprofiles.forEach(uprofile => {
                    if (uprofile.id === uprofileId) {
                        console.log('CONSOLOG: M:removeProfileceleb; INDEX!!!!!: ', celeb.uprofiles.indexOf(uprofile));
                        celeb.uprofiles.splice(celeb.uprofiles.indexOf(uprofile), 1);
                        this.subscribeToSaveResponse5(this.celebService.update(celeb));
                        this.celebs.splice(celeb.uprofiles.indexOf(uprofile), 1);
                    }
                });
            }
        });
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFollow>>) {
        result.subscribe((res: HttpResponse<IFollow>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected subscribeToSaveResponse2(result: Observable<HttpResponse<INotification>>) {
        result.subscribe((res: HttpResponse<INotification>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected subscribeToSaveResponse3(result: Observable<HttpResponse<IInterest>>) {
        result.subscribe((res: HttpResponse<IInterest>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected subscribeToSaveResponse4(result: Observable<HttpResponse<IActivity>>) {
        result.subscribe((res: HttpResponse<IActivity>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected subscribeToSaveResponse5(result: Observable<HttpResponse<ICeleb>>) {
        result.subscribe((res: HttpResponse<ICeleb>) => this.onSaveSuccess2(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveSuccess2() {
        this.isSaving = false;
    }

    protected onSaveError() {
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    get follow() {
        return this._follow;
    }

    set follow(follow: IFollow) {
        this._follow = follow;
        this.creationDate = moment().format(DATE_TIME_FORMAT);
    }

    get blockuser() {
        return this._blockuser;
    }

    set blockuser(blockuser: IBlockuser) {
        this._blockuser = blockuser;
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
