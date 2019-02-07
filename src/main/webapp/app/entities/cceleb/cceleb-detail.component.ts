import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICceleb } from 'app/shared/model/cceleb.model';

@Component({
    selector: 'jhi-cceleb-detail',
    templateUrl: './cceleb-detail.component.html'
})
export class CcelebDetailComponent implements OnInit {
    cceleb: ICceleb;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cceleb }) => {
            this.cceleb = cceleb;
        });
    }

    previousState() {
        window.history.back();
    }
}
