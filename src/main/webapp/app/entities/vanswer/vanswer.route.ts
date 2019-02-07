import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Vanswer } from 'app/shared/model/vanswer.model';
import { VanswerService } from './vanswer.service';
import { VanswerComponent } from './vanswer.component';
import { VanswerDetailComponent } from './vanswer-detail.component';
import { VanswerUpdateComponent } from './vanswer-update.component';
import { VanswerDeletePopupComponent } from './vanswer-delete-dialog.component';
import { IVanswer } from 'app/shared/model/vanswer.model';

@Injectable({ providedIn: 'root' })
export class VanswerResolve implements Resolve<IVanswer> {
    constructor(private service: VanswerService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVanswer> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Vanswer>) => response.ok),
                map((vanswer: HttpResponse<Vanswer>) => vanswer.body)
            );
        }
        return of(new Vanswer());
    }
}

export const vanswerRoute: Routes = [
    {
        path: '',
        component: VanswerComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.vanswer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VanswerDetailComponent,
        resolve: {
            vanswer: VanswerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vanswer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VanswerUpdateComponent,
        resolve: {
            vanswer: VanswerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vanswer.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VanswerUpdateComponent,
        resolve: {
            vanswer: VanswerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vanswer.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vanswerPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VanswerDeletePopupComponent,
        resolve: {
            vanswer: VanswerResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vanswer.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
