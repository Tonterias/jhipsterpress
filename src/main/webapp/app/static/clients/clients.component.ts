import { Component, OnInit } from '@angular/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-clients',
    templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {
    currentAccount: any;

    constructor(private accountService: AccountService) {}

    ngOnInit() {
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
