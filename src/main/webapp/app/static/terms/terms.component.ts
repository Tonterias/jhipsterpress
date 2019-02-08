import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-terms',
    templateUrl: './terms.component.html'
})
export class TermsComponent implements OnInit {
    currentAccount: any;

    constructor(protected accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
