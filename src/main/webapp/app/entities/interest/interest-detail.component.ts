import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInterest } from 'app/shared/model/interest.model';

@Component({
    selector: 'jhi-interest-detail',
    templateUrl: './interest-detail.component.html'
})
export class InterestDetailComponent implements OnInit {
    interest: IInterest;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ interest }) => {
            this.interest = interest;
            //            console.log('CONSOLOG: M:ngOnInit & O: this.interest : ', this.interest);
        });
    }

    previousState() {
        window.history.back();
    }
}
