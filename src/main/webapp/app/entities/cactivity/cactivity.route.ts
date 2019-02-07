import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cactivity } from 'app/shared/model/cactivity.model';
import { CactivityService } from './cactivity.service';
import { CactivityComponent } from './cactivity.component';
import { CactivityDetailComponent } from './cactivity-detail.component';
import { CactivityUpdateComponent } from './cactivity-update.component';
import { CactivityDeletePopupComponent } from './cactivity-delete-dialog.component';
import { ICactivity } from 'app/shared/model/cactivity.model';

@Injectable({ providedIn: 'root' })
export class CactivityResolve implements Resolve<ICactivity> {
    constructor(private service: CactivityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICactivity> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cactivity>) => response.ok),
                map((cactivity: HttpResponse<Cactivity>) => cactivity.body)
            );
        }
        return of(new Cactivity());
    }
}

export const cactivityRoute: Routes = [
    {
        path: '',
        component: CactivityComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.cactivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CactivityDetailComponent,
        resolve: {
            cactivity: CactivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cactivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CactivityUpdateComponent,
        resolve: {
            cactivity: CactivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cactivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CactivityUpdateComponent,
        resolve: {
            cactivity: CactivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cactivity.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cactivityPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CactivityDeletePopupComponent,
        resolve: {
            cactivity: CactivityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cactivity.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
