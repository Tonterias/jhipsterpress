import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cinterest } from 'app/shared/model/cinterest.model';
import { CinterestService } from './cinterest.service';
import { CinterestComponent } from './cinterest.component';
import { CinterestDetailComponent } from './cinterest-detail.component';
import { CinterestUpdateComponent } from './cinterest-update.component';
import { CinterestDeletePopupComponent } from './cinterest-delete-dialog.component';
import { ICinterest } from 'app/shared/model/cinterest.model';

@Injectable({ providedIn: 'root' })
export class CinterestResolve implements Resolve<ICinterest> {
    constructor(private service: CinterestService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICinterest> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cinterest>) => response.ok),
                map((cinterest: HttpResponse<Cinterest>) => cinterest.body)
            );
        }
        return of(new Cinterest());
    }
}

export const cinterestRoute: Routes = [
    {
        path: '',
        component: CinterestComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.cinterest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CinterestDetailComponent,
        resolve: {
            cinterest: CinterestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cinterest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CinterestUpdateComponent,
        resolve: {
            cinterest: CinterestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cinterest.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CinterestUpdateComponent,
        resolve: {
            cinterest: CinterestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cinterest.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cinterestPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CinterestDeletePopupComponent,
        resolve: {
            cinterest: CinterestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cinterest.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
