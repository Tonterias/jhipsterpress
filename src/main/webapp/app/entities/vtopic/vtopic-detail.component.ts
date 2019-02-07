import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVtopic } from 'app/shared/model/vtopic.model';

@Component({
    selector: 'jhi-vtopic-detail',
    templateUrl: './vtopic-detail.component.html'
})
export class VtopicDetailComponent implements OnInit {
    vtopic: IVtopic;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vtopic }) => {
            this.vtopic = vtopic;
        });
    }

    previousState() {
        window.history.back();
    }
}
