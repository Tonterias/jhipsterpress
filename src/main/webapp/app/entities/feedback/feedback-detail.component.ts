import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFeedback } from 'app/shared/model/feedback.model';

@Component({
    selector: 'jhi-feedback-detail',
    templateUrl: './feedback-detail.component.html'
})
export class FeedbackDetailComponent implements OnInit {
    feedback: IFeedback;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ feedback }) => {
            this.feedback = feedback;
        });
    }

    previousState() {
        window.history.back();
    }
}
