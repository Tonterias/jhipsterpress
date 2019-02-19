import { Injectable } from '@angular/core';

import { AccountService } from 'app/core/auth/account.service';
import { AuthServerProvider } from 'app/core/auth/auth-session.service';

import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({ providedIn: 'root' })
export class LoginService {
    private loginStatus = new BehaviorSubject<boolean>(false);
    public loginCast = this.loginStatus.asObservable();

    constructor(private accountService: AccountService, private authServerProvider: AuthServerProvider) {}

    editLoginStatus(status) {
        this.loginStatus.next(status);
        //        console.log('CONSOLOG: M:editLoginStatus & O: this.loginStatus : ', this.loginStatus);
    }

    login(credentials, callback?) {
        const cb = callback || function() {};

        return new Promise((resolve, reject) => {
            this.authServerProvider.login(credentials).subscribe(
                data => {
                    this.accountService.identity(true).then(account => {
                        resolve(data);
                    });
                    return cb();
                },
                err => {
                    this.logout();
                    reject(err);
                    return cb(err);
                }
            );
        });
    }

    logout() {
        this.authServerProvider.logout().subscribe();
        this.accountService.authenticate(null);
    }
}
