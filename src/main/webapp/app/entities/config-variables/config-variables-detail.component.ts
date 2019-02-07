import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConfigVariables } from 'app/shared/model/config-variables.model';

@Component({
    selector: 'jhi-config-variables-detail',
    templateUrl: './config-variables-detail.component.html'
})
export class ConfigVariablesDetailComponent implements OnInit {
    configVariables: IConfigVariables;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configVariables }) => {
            this.configVariables = configVariables;
        });
    }

    previousState() {
        window.history.back();
    }
}
