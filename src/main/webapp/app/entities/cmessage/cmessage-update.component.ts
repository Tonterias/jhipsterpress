import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ICmessage } from 'app/shared/model/cmessage.model';
import { CmessageService } from './cmessage.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';
import { IUser, UserService } from 'app/core';
import { IFollow } from 'app/shared/model/follow.model';
import { FollowService } from '../follow/follow.service';
import { IBlockuser } from 'app/shared/model/blockuser.model';
import { BlockuserService } from '../blockuser/blockuser.service';

import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-cmessage-update',
    templateUrl: './cmessage-update.component.html'
})
export class CmessageUpdateComponent implements OnInit {
    //    cmessage: ICmessage;
    private _cmessage: ICmessage;
    isSaving: boolean;

    communities: ICommunity[] = [];
    community: ICommunity[];

    creationDate: string;

    follows: IFollow[];
    loggedUser: IUser;
    blockusers: IBlockuser[];

    currentAccount: any;
    isBlocked: boolean;

    routeData: any;
    nameParamFollows: any;
    valueParamFollows: number;
    blockedByUser: string;

    alerts: any[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cmessageService: CmessageService,
        protected userService: UserService,
        protected followService: FollowService,
        protected blockuserService: BlockuserService,
        protected accountService: AccountService,
        protected communityService: CommunityService,
        protected activatedRoute: ActivatedRoute
    ) {
        this.activatedRoute.queryParams.subscribe(params => {
            if (params.communityIdEquals != null) {
                this.nameParamFollows = 'communityId';
                this.valueParamFollows = params.communityIdEquals;
                //                console.log(
                //                    'CONSOLOG: M:Constructor & O: this.activatedRoute.queryParams : ',
                //                    this.nameParamFollows,
                //                    this.valueParamFollows
                //                );
            }
        });
    }

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cmessage }) => {
            this.cmessage = cmessage;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.cmessage.creationDate = moment(this.creationDate);
            //            console.log('CONSOLOG: M:ngOnInit & O: this.cmessage : ', this.cmessage);
            this.cmessage.creceiverId = Number(this.valueParamFollows);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.cmessage.csenderId = this.currentAccount.id;
            //            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount);
            this.isBlockUser().subscribe(
                (res: HttpResponse<IBlockuser[]>) => {
                    this.blockusers = res.body;
                    //                    console.log('CONSOLOG: M:currentLoggedProfile & O:  this.blockusers : ', this.blockusers);
                    if (this.blockusers.length > 0) {
                        this.isBlocked = true;
                        this.valueParamFollows = null;
                        this.onWarning('BLOCKED BY USER');
                        //                        console.log('CONSOLOG: M:currentLoggedProfile & O:  this.isBlocked : ', this.isBlocked);
                        return this.blockusers[0];
                    }
                },
                (res3: HttpErrorResponse) => this.onError(res3.message)
            );
        });

        this.communityService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICommunity[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICommunity[]>) => response.body)
            )
            .subscribe((res: ICommunity[]) => (this.communities = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.cmessage.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.cmessage.id !== undefined) {
            this.subscribeToSaveResponse(this.cmessageService.update(this.cmessage));
        } else {
            if (this.cmessage.creceiverId !== undefined) {
                if (this.isBlocked === false) {
                    //                    console.log('CONSOLOG: M:save & O: this.isBlockUser.length : NO-BLOCKED ', this.isBlockUser.length);
                    this.subscribeToSaveResponse(this.cmessageService.create(this.cmessage));
                }
            }
        }
    }

    private isBlockUser() {
        this.isBlocked = false;
        const query = {};
        if (this.currentAccount.id != null) {
            query['blockeduserId.in'] = Number(this.valueParamFollows);
            query['blockinguserId.in'] = this.currentAccount.id;
        }
        //        console.log('CONSOLOG: M:isBlockUser & O: query : ', query);
        return this.blockuserService.query(query);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICmessage>>) {
        result.subscribe((res: HttpResponse<ICmessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    private onWarning(errorMessage: string) {
        //        console.log('CONSOLOG: M:onWarning & O:  errorMessage : ', errorMessage);
        this.alerts = [];
        //        console.log('CONSOLOG: M:onWarning & O:  this.alerts : ', this.alerts);
        this.alerts.push(
            this.jhiAlertService.addAlert(
                {
                    type: 'info',
                    msg: errorMessage,
                    timeout: 5000,
                    toast: false,
                    scoped: true
                },
                this.alerts
            )
        );
        //        console.log('CONSOLOG: M:onWarning & O:  this.alerts2 : ', this.alerts);
    }

    trackCommunityById(index: number, item: ICommunity) {
        return item.id;
    }

    get cmessage() {
        return this._cmessage;
    }

    set cmessage(cmessage: ICmessage) {
        this._cmessage = cmessage;
        this.creationDate = moment(cmessage.creationDate).format(DATE_TIME_FORMAT);
    }
}
