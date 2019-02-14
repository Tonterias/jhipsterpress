import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiLanguageService } from 'ng-jhipster';
import { SessionStorageService } from 'ngx-webstorage';

import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
import { VERSION } from 'app/app.constants';
import { JhiLanguageHelper, AccountService, LoginModalService, LoginService } from 'app/core';

import { ProfileService } from '../profiles/profile.service';
import { INotification } from 'app/shared/model/notification.model';
import { NotificationService } from '../.././../app/entities/notification/notification.service';
import { IMessage } from 'app/shared/model/message.model';
import { MessageService } from '../.././../app/entities/message/message.service';
import { ICmessage } from 'app/shared/model/cmessage.model';
import { CmessageService } from '../.././../app/entities/cmessage/cmessage.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from '../.././../app/entities/community/community.service';

@Component({
    selector: 'jhi-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['navbar.css']
})
export class NavbarComponent implements OnInit {
    inProduction: boolean;
    isNavbarCollapsed: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    modalRef: NgbModalRef;
    version: string;

    currentAccount: any;
    loginName: string;

    numberOfNotifications: number;
    numberOfMessages: number;
    numberOfCmessages: number;
    numberOfCommunities: number;

    messages: IMessage[];
    cmessages: ICmessage[];
    communities: ICommunity[];

    constructor(
        private loginService: LoginService,
        private languageService: JhiLanguageService,
        private languageHelper: JhiLanguageHelper,
        private sessionStorage: SessionStorageService,
        private accountService: AccountService,
        private loginModalService: LoginModalService,
        private profileService: ProfileService,
        private notificationService: NotificationService,
        private communityService: CommunityService,
        private messageService: MessageService,
        private cmessageService: CmessageService,
        private jhiAlertService: JhiAlertService,
        private router: Router
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.isNavbarCollapsed = true;
    }

    ngOnInit() {
        this.accountService.getAuthenticationState().subscribe(state => {
            if (state) {
                this.loginService.editLoginStatus(true);
            }
        });
        this.loginService.loginCast.subscribe(status => {
            if (status) {
                this.loginData();
            }
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
        this.profileService.getProfileInfo().then(profileInfo => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
    }

    loginData() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.loginName = this.currentAccount.login;
            console.log('CONSOLOG: M:ngOnInit & O: this.loginName : ', this.loginName);
            this.notifications().subscribe(
                (res: HttpResponse<INotification[]>) => {
                    console.log('CONSOLOG: M:ngOnInit & O: notifications.res.body INotification : ', res.body);
                    console.log('CONSOLOG: M:ngOnInit & O: notifications.res.body.length INotification : ', res.body.length);
                    this.numberOfNotifications = res.body.length;
                    return this.numberOfNotifications;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.mymessages().subscribe(
                (res: HttpResponse<IMessage[]>) => {
                    console.log('CONSOLOG: M:ngOnInit & O: messages.res.body IMessage : ', res.body);
                    console.log('CONSOLOG: M:ngOnInit & O: notifications.res.body.length IMessage : ', res.body.length);
                    this.numberOfMessages = res.body.length;
                    return this.numberOfMessages;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.myMessagesCommunities().subscribe(
                (res2: HttpResponse<ICommunity[]>) => {
                    this.communities = res2.body;
                    console.log('CONSOLOG: M:loginData & O: this.communities : ', this.communities);
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.communitiesMessages().subscribe(
                (res3: HttpResponse<ICmessage[]>) => {
                    console.log('CONSOLOG: M:loginData & O: res3.body.length .numberOfMessages : ', res3.body.length);
                    console.log('CONSOLOG: M:loginData & O: res3.body : ', res3.body);
                    this.numberOfCmessages = res3.body.length;
                    console.log('CONSOLOG: M:loginData & O: this.numberOfMessages : ', this.numberOfCmessages);
                },
                (res3: HttpErrorResponse) => this.onError(res3.message)
            );
        });
    }

    changeLanguage(languageKey: string) {
        this.sessionStorage.store('locale', languageKey);
        this.languageService.changeLanguage(languageKey);
    }

    collapseNavbar() {
        this.isNavbarCollapsed = true;
    }

    isAuthenticated() {
        return this.accountService.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.collapseNavbar();
        this.loginService.logout();
        this.router.navigate(['']);
        this.loginName = '';
    }

    toggleNavbar() {
        this.isNavbarCollapsed = !this.isNavbarCollapsed;
    }

    getImageUrl() {
        return this.isAuthenticated() ? this.accountService.getImageUrl() : null;
    }

    private notifications() {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
            query['isDelivered.equals'] = 'false';
        }
        return this.notificationService.query(query);
    }

    private mymessages() {
        const query = {};
        if (this.currentAccount.id != null) {
            query['receiverId.equals'] = this.currentAccount.id;
            query['isDelivered.equals'] = 'false';
        }
        return this.messageService.query(query);
    }

    private myMessagesCommunities() {
        console.log('CONSOLOG: M:myMessagesCommunities & O: this.currentAccount : ', this.currentAccount);
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        return this.communityService.query(query);
    }

    private communitiesMessages() {
        const query = {};
        if (this.communities != null) {
            const arrayCommmunities = [];
            this.communities.forEach(community => {
                arrayCommmunities.push(community.id);
            });
            query['creceiverId.in'] = arrayCommmunities;
            query['isDelivered.equals'] = 'false';
        }
        return this.cmessageService.query(query);
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
