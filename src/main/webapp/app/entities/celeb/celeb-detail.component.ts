import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICeleb } from 'app/shared/model/celeb.model';

@Component({
    selector: 'jhi-celeb-detail',
    templateUrl: './celeb-detail.component.html'
})
export class CelebDetailComponent implements OnInit {
    celeb: ICeleb;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ celeb }) => {
            this.celeb = celeb;
        });
    }

    previousState() {
        window.history.back();
    }
}
