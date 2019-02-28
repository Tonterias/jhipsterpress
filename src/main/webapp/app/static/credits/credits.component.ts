import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-credits',
    templateUrl: './credits.component.html'
})
export class CreditsComponent implements OnInit {
    currentAccount: any;

    constructor(private accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
