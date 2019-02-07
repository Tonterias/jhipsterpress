import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IActivity } from 'app/shared/model/activity.model';

@Component({
    selector: 'jhi-activity-detail',
    templateUrl: './activity-detail.component.html'
})
export class ActivityDetailComponent implements OnInit {
    activity: IActivity;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ activity }) => {
            this.activity = activity;
        });
    }

    previousState() {
        window.history.back();
    }
}
