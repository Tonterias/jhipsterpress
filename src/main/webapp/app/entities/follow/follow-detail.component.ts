import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFollow } from 'app/shared/model/follow.model';

@Component({
    selector: 'jhi-follow-detail',
    templateUrl: './follow-detail.component.html'
})
export class FollowDetailComponent implements OnInit {
    follow: IFollow;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ follow }) => {
            this.follow = follow;
        });
    }

    previousState() {
        window.history.back();
    }
}
