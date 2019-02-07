import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICactivity } from 'app/shared/model/cactivity.model';

@Component({
    selector: 'jhi-cactivity-detail',
    templateUrl: './cactivity-detail.component.html'
})
export class CactivityDetailComponent implements OnInit {
    cactivity: ICactivity;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cactivity }) => {
            this.cactivity = cactivity;
        });
    }

    previousState() {
        window.history.back();
    }
}
