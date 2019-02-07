import { Component, OnInit } from '@angular/core';
import { Principal } from 'app/core';

@Component({
    selector: 'jhi-error',
    templateUrl: './error.component.html'
})
export class ErrorComponent implements OnInit {
    currentAccount: any;

    constructor(private principal: Principal) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
