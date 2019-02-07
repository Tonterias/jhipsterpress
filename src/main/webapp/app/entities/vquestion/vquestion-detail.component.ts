import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVquestion } from 'app/shared/model/vquestion.model';

@Component({
    selector: 'jhi-vquestion-detail',
    templateUrl: './vquestion-detail.component.html'
})
export class VquestionDetailComponent implements OnInit {
    vquestion: IVquestion;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vquestion }) => {
            this.vquestion = vquestion;
        });
    }

    previousState() {
        window.history.back();
    }
}
