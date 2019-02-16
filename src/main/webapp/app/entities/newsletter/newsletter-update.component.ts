import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { JhiAlertService } from 'ng-jhipster';
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
    newsletters: INewsletter[];
    isSaving: boolean;
    creationDate: string;

    constructor(
        protected newsletterService: NewsletterService,
        protected jhiAlertService: JhiAlertService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ newsletter }) => {
            this.newsletter = newsletter;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.newsletter.creationDate = moment(this.creationDate);
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
            const query = {};
            if (this.newsletter.email != null) {
                query['email.equals'] = this.newsletter.email;
            }
            this.newsletterService.query(query).subscribe(
                (res: HttpResponse<INewsletter[]>) => {
                    if (res.body.length === 0) {
                        this.subscribeToSaveResponse(this.newsletterService.create(this.newsletter));
                    } else {
                        console.log('Ya tenemos tu email');
                        this.newsletter.email = '';
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INewsletter>>) {
        result.subscribe((res: HttpResponse<INewsletter>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        console.log('Deberia ir a la HOME!');
        this.router.navigate(['/']);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
