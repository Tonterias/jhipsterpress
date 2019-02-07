import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Celeb } from 'app/shared/model/celeb.model';
import { CelebService } from './celeb.service';
import { CelebComponent } from './celeb.component';
import { CelebDetailComponent } from './celeb-detail.component';
import { CelebUpdateComponent } from './celeb-update.component';
import { CelebDeletePopupComponent } from './celeb-delete-dialog.component';
import { ICeleb } from 'app/shared/model/celeb.model';

@Injectable({ providedIn: 'root' })
export class CelebResolve implements Resolve<ICeleb> {
    constructor(private service: CelebService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICeleb> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Celeb>) => response.ok),
                map((celeb: HttpResponse<Celeb>) => celeb.body)
            );
        }
        return of(new Celeb());
    }
}

export const celebRoute: Routes = [
    {
        path: '',
        component: CelebComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CelebDetailComponent,
        resolve: {
            celeb: CelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CelebUpdateComponent,
        resolve: {
            celeb: CelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CelebUpdateComponent,
        resolve: {
            celeb: CelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const celebPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CelebDeletePopupComponent,
        resolve: {
            celeb: CelebResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.celeb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
