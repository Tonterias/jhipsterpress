import { Component, OnInit } from '@angular/core';
import { Principal } from 'app/core';

@Component({
    selector: 'jhi-clients',
    templateUrl: './clients.component.html'
})
export class ClientsComponent implements OnInit {
    currentAccount: any;

    constructor(private principal: Principal) {}

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
    }
}
