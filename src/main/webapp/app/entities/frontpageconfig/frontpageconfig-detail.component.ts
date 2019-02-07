import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFrontpageconfig } from 'app/shared/model/frontpageconfig.model';

@Component({
    selector: 'jhi-frontpageconfig-detail',
    templateUrl: './frontpageconfig-detail.component.html'
})
export class FrontpageconfigDetailComponent implements OnInit {
    frontpageconfig: IFrontpageconfig;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ frontpageconfig }) => {
            this.frontpageconfig = frontpageconfig;
        });
    }

    previousState() {
        window.history.back();
    }
}
