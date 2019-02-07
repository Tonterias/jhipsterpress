import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVanswer } from 'app/shared/model/vanswer.model';

@Component({
    selector: 'jhi-vanswer-detail',
    templateUrl: './vanswer-detail.component.html'
})
export class VanswerDetailComponent implements OnInit {
    vanswer: IVanswer;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vanswer }) => {
            this.vanswer = vanswer;
        });
    }

    previousState() {
        window.history.back();
    }
}
