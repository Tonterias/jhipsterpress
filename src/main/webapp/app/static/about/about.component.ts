import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-about',
    templateUrl: './about.component.html'
})
export class AboutComponent implements OnInit {
    currentAccount: any;

    constructor(private accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
