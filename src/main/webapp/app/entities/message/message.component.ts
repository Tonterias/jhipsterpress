import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import * as moment from 'moment';
import { Observable } from 'rxjs';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMessage } from 'app/shared/model/message.model';
import { MessageService } from './message.service';

import { AccountService } from 'app/core';
import { ITEMS_PER_PAGE } from 'app/shared';

@Component({
    selector: 'jhi-message',
    templateUrl: './message.component.html'
})
export class MessageComponent implements OnInit, OnDestroy {
    currentAccount: any;
    messages: IMessage[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    isSaving: boolean;
    creationDate: string;

    constructor(
        protected messageService: MessageService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
        this.currentSearch =
            this.activatedRoute.snapshot && this.activatedRoute.snapshot.params['search']
                ? this.activatedRoute.snapshot.params['search']
                : '';
    }

    loadAll() {
        if (this.currentSearch) {
            const query = {
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            };
            query['messageText.contains'] = this.currentSearch;
            query['receiverId.equals'] = this.currentAccount.id;
            this.messageService.query(query).subscribe(
                (res: HttpResponse<IMessage[]>) => {
                    this.messages = res.body;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            return;
        }
        const query2 = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        query2['receiverId.equals'] = this.currentAccount.id;
        this.messageService.query(query2).subscribe(
            (res: HttpResponse<IMessage[]>) => {
                this.messages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        return;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/message'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                'messageText.contains': this.currentSearch,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.currentSearch = '';
        this.router.navigate([
            '/message',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.page = 0;
        this.currentSearch = query;
        this.router.navigate([
            '/message',
            {
                search: this.currentSearch,
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            console.log('CONSOLOG: M:ngOnInit & O: this.currentAccount : ', this.currentAccount);
            this.myMessages();
        });
        this.registerChangeInMessages();
    }

    myMessages() {
        const query = {
            page: this.page - 1,
            size: this.itemsPerPage,
            sort: this.sort()
        };
        console.log('CONSOLOG: M:myMessages & O: query : ', query, this.itemsPerPage);
        if (this.currentAccount.id != null) {
            query['receiverId.equals'] = this.currentAccount.id;
        }
        this.messageService.query(query).subscribe(
            (res: HttpResponse<IMessage[]>) => {
                this.messages = res.body;
                console.log('CONSOLOG: M:myMessages & O: this.messages : ', this.messages);
                this.isDeliveredUpdate(this.messages);
                this.paginateMessages(res.body, res.headers);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    isDeliveredUpdate(messages: IMessage[]) {
        this.isSaving = true;
        this.messages.forEach(message => {
            //            console.log('CONSOLOG: M:isDeliveredUpdate & O: messages PRE-Date : ', message);
            this.creationDate = moment(message.creationDate).format(DATE_TIME_FORMAT);
            //            console.log('CONSOLOG: M:isDeliveredUpdate & O: this.creationDate : ', this.creationDate);
            //            console.log('CONSOLOG: M:isDeliveredUpdate & O: messages POST-Date : ', message);
            message.isDelivered = true;
            //            this.notificationService.update(notification);
            this.subscribeToSaveResponse(this.messageService.update(message));
            //            this.subscribeToSaveResponse(this.notificationService.update(notification));
            //            console.log('CONSOLOG: M:isDeliveredUpdate & O: message : ', message);
        });
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMessage>>) {
        result.subscribe((res: HttpResponse<IMessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMessage) {
        return item.id;
    }

    registerChangeInMessages() {
        this.eventSubscriber = this.eventManager.subscribe('messageListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateMessages(data: IMessage[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.messages = data;
        console.log('CONSOLOG: M:paginateMessages & O: this.messages : ', this.messages);
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
