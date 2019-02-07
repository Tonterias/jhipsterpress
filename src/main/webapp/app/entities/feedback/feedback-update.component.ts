import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFeedback } from 'app/shared/model/feedback.model';
import { FeedbackService } from './feedback.service';

@Component({
    selector: 'jhi-feedback-update',
    templateUrl: './feedback-update.component.html'
})
export class FeedbackUpdateComponent implements OnInit {
    feedback: IFeedback;
    isSaving: boolean;
    creationDate: string;

    constructor(protected feedbackService: FeedbackService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ feedback }) => {
            this.feedback = feedback;
            this.creationDate = this.feedback.creationDate != null ? this.feedback.creationDate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.feedback.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.feedback.id !== undefined) {
            this.subscribeToSaveResponse(this.feedbackService.update(this.feedback));
        } else {
            this.subscribeToSaveResponse(this.feedbackService.create(this.feedback));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFeedback>>) {
        result.subscribe((res: HttpResponse<IFeedback>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
