import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICinterest } from 'app/shared/model/cinterest.model';

@Component({
    selector: 'jhi-cinterest-detail',
    templateUrl: './cinterest-detail.component.html'
})
export class CinterestDetailComponent implements OnInit {
    cinterest: ICinterest;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cinterest }) => {
            this.cinterest = cinterest;
        });
    }

    previousState() {
        window.history.back();
    }
}
