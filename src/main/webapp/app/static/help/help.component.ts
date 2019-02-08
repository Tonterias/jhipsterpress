import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-help',
    templateUrl: './help.component.html'
})
export class HelpComponent implements OnInit {
    currentAccount: any;

    constructor(protected accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
