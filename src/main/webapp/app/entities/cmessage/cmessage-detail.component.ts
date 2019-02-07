import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICmessage } from 'app/shared/model/cmessage.model';

@Component({
    selector: 'jhi-cmessage-detail',
    templateUrl: './cmessage-detail.component.html'
})
export class CmessageDetailComponent implements OnInit {
    cmessage: ICmessage;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cmessage }) => {
            this.cmessage = cmessage;
        });
    }

    previousState() {
        window.history.back();
    }
}
