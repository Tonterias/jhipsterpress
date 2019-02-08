import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-pricing',
    templateUrl: './pricing.component.html'
})
export class PricingComponent implements OnInit {
    currentAccount: any;

    constructor(protected accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
