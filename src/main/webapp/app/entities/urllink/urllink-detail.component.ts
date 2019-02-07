import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUrllink } from 'app/shared/model/urllink.model';

@Component({
    selector: 'jhi-urllink-detail',
    templateUrl: './urllink-detail.component.html'
})
export class UrllinkDetailComponent implements OnInit {
    urllink: IUrllink;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ urllink }) => {
            this.urllink = urllink;
        });
    }

    previousState() {
        window.history.back();
    }
}
