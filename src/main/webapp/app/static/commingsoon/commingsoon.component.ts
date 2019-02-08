import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-commingsoon',
    templateUrl: './commingsoon.component.html'
})
export class CommingsoonComponent implements OnInit {
    currentAccount: any;

    constructor(private accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
