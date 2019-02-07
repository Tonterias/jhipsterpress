import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICalbum } from 'app/shared/model/calbum.model';

@Component({
    selector: 'jhi-calbum-detail',
    templateUrl: './calbum-detail.component.html'
})
export class CalbumDetailComponent implements OnInit {
    calbum: ICalbum;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ calbum }) => {
            this.calbum = calbum;
        });
    }

    previousState() {
        window.history.back();
    }
}
