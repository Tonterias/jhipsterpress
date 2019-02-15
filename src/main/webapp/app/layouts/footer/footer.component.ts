import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { INewsletter } from 'app/shared/model/newsletter.model';
import { NewsletterService } from '../../entities/newsletter/newsletter.service';

@Component({
    selector: 'jhi-footer',
    templateUrl: './footer.component.html'
})
export class FooterComponent implements OnInit {
    newsletter: INewsletter;
    isSaving: boolean;
    creationDate: string;

    alerts: any[];

    constructor(
        private newsletterService: NewsletterService,
        private activatedRoute: ActivatedRoute,
        protected jhiAlertService: JhiAlertService
    ) {}

    ngOnInit() {
        this.creationDate = '';
        this.isSaving = false;
        this.creationDate = moment().format(DATE_TIME_FORMAT);
        this.newsletter = new Object();
        this.newsletter.creationDate = moment(this.creationDate);
        this.newsletter.email = '';
    }

    save() {
        this.isSaving = true;
        if (this.newsletter.id !== undefined) {
            this.subscribeToSaveResponse(this.newsletterService.update(this.newsletter));
        } else {
            this.onWarning('BLOCKED BY USER');
            this.subscribeToSaveResponse(this.newsletterService.create(this.newsletter));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INewsletter>>) {
        result.subscribe((res: HttpResponse<INewsletter>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onWarning(errorMessage: string) {
        console.log('CONSOLOG: M:onWarning & O:  errorMessage : ', errorMessage);
        this.alerts = [];
        console.log('CONSOLOG: M:onWarning & O:  this.alerts : ', this.alerts);
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
        console.log('CONSOLOG: M:onWarning & O:  this.alerts2 : ', this.alerts);
    }
}
