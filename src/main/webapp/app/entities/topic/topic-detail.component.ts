import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';
import { Subscription } from 'rxjs';

import { ITopic } from 'app/shared/model/topic.model';

@Component({
    selector: 'jhi-topic-detail',
    templateUrl: './topic-detail.component.html'
})
export class TopicDetailComponent implements OnInit {
    topic: ITopic;

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

    constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ topic }) => {
            this.topic = topic;
            //            console.log('CONSOLOG: M:ngOnInit & O: this.topic : ', this.topic);
        });
    }

    previousState() {
        window.history.back();
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    loadPage(page) {
        this.previousPage = page;
        this.page = page;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    reload() {
        window.location.reload();
    }
}
