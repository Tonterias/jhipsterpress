import { Component, OnInit } from '@angular/core';
import { Principal } from 'app/core';

@Component({
    selector: 'jhi-commingsoon',
    templateUrl: './commingsoon.component.html'
})
export class CommingsoonComponent implements OnInit {
    currentAccount: any;

    constructor(private principal: Principal) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
