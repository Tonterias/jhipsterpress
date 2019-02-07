import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cceleb } from 'app/shared/model/cceleb.model';
import { CcelebService } from './cceleb.service';
import { CcelebComponent } from './cceleb.component';
import { CcelebDetailComponent } from './cceleb-detail.component';
import { CcelebUpdateComponent } from './cceleb-update.component';
import { CcelebDeletePopupComponent } from './cceleb-delete-dialog.component';
import { ICceleb } from 'app/shared/model/cceleb.model';

@Injectable({ providedIn: 'root' })
export class CcelebResolve implements Resolve<ICceleb> {
    constructor(private service: CcelebService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICceleb> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cceleb>) => response.ok),
                map((cceleb: HttpResponse<Cceleb>) => cceleb.body)
            );
        }
        return of(new Cceleb());
    }
}

export const ccelebRoute: Routes = [
    {
        path: '',
        component: CcelebComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.cceleb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CcelebDetailComponent,
        resolve: {
            cceleb: CcelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cceleb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CcelebUpdateComponent,
        resolve: {
            cceleb: CcelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cceleb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CcelebUpdateComponent,
        resolve: {
            cceleb: CcelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cceleb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ccelebPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CcelebDeletePopupComponent,
        resolve: {
            cceleb: CcelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.cceleb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
