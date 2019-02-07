import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Urllink } from 'app/shared/model/urllink.model';
import { UrllinkService } from './urllink.service';
import { UrllinkComponent } from './urllink.component';
import { UrllinkDetailComponent } from './urllink-detail.component';
import { UrllinkUpdateComponent } from './urllink-update.component';
import { UrllinkDeletePopupComponent } from './urllink-delete-dialog.component';
import { IUrllink } from 'app/shared/model/urllink.model';

@Injectable({ providedIn: 'root' })
export class UrllinkResolve implements Resolve<IUrllink> {
    constructor(private service: UrllinkService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUrllink> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Urllink>) => response.ok),
                map((urllink: HttpResponse<Urllink>) => urllink.body)
            );
        }
        return of(new Urllink());
    }
}

export const urllinkRoute: Routes = [
    {
        path: '',
        component: UrllinkComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: UrllinkDetailComponent,
        resolve: {
            urllink: UrllinkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: UrllinkUpdateComponent,
        resolve: {
            urllink: UrllinkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: UrllinkUpdateComponent,
        resolve: {
            urllink: UrllinkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const urllinkPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: UrllinkDeletePopupComponent,
        resolve: {
            urllink: UrllinkResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.urllink.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
