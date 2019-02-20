import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';
import { Subscription } from 'rxjs';

import { ITag } from 'app/shared/model/tag.model';

@Component({
    selector: 'jhi-tag-detail',
    templateUrl: './tag-detail.component.html'
})
export class TagDetailComponent implements OnInit {
    tag: ITag;

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
        this.activatedRoute.data.subscribe(({ tag }) => {
            this.tag = tag;
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
