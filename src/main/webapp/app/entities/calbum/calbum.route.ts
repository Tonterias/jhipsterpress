import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Calbum } from 'app/shared/model/calbum.model';
import { CalbumService } from './calbum.service';
import { CalbumComponent } from './calbum.component';
import { CalbumDetailComponent } from './calbum-detail.component';
import { CalbumUpdateComponent } from './calbum-update.component';
import { CalbumDeletePopupComponent } from './calbum-delete-dialog.component';
import { ICalbum } from 'app/shared/model/calbum.model';

@Injectable({ providedIn: 'root' })
export class CalbumResolve implements Resolve<ICalbum> {
    constructor(private service: CalbumService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICalbum> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Calbum>) => response.ok),
                map((calbum: HttpResponse<Calbum>) => calbum.body)
            );
        }
        return of(new Calbum());
    }
}

export const calbumRoute: Routes = [
    {
        path: '',
        component: CalbumComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.calbum.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CalbumDetailComponent,
        resolve: {
            calbum: CalbumResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.calbum.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CalbumUpdateComponent,
        resolve: {
            calbum: CalbumResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.calbum.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CalbumUpdateComponent,
        resolve: {
            calbum: CalbumResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.calbum.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const calbumPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CalbumDeletePopupComponent,
        resolve: {
            calbum: CalbumResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.calbum.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
