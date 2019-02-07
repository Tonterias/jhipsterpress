import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVthumb } from 'app/shared/model/vthumb.model';

@Component({
    selector: 'jhi-vthumb-detail',
    templateUrl: './vthumb-detail.component.html'
})
export class VthumbDetailComponent implements OnInit {
    vthumb: IVthumb;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vthumb }) => {
            this.vthumb = vthumb;
        });
    }

    previousState() {
        window.history.back();
    }
}
