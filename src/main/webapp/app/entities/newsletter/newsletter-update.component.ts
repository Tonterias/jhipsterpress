import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { INewsletter } from 'app/shared/model/newsletter.model';
import { NewsletterService } from './newsletter.service';

@Component({
    selector: 'jhi-newsletter-update',
    templateUrl: './newsletter-update.component.html'
})
export class NewsletterUpdateComponent implements OnInit {
    newsletter: INewsletter;
    isSaving: boolean;
    creationDate: string;

    constructor(protected newsletterService: NewsletterService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ newsletter }) => {
            this.newsletter = newsletter;
            this.creationDate = this.newsletter.creationDate != null ? this.newsletter.creationDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.newsletter.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.newsletter.id !== undefined) {
            this.subscribeToSaveResponse(this.newsletterService.update(this.newsletter));
        } else {
            this.subscribeToSaveResponse(this.newsletterService.create(this.newsletter));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INewsletter>>) {
        result.subscribe((res: HttpResponse<INewsletter>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
