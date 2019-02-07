import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Vthumb } from 'app/shared/model/vthumb.model';
import { VthumbService } from './vthumb.service';
import { VthumbComponent } from './vthumb.component';
import { VthumbDetailComponent } from './vthumb-detail.component';
import { VthumbUpdateComponent } from './vthumb-update.component';
import { VthumbDeletePopupComponent } from './vthumb-delete-dialog.component';
import { IVthumb } from 'app/shared/model/vthumb.model';

@Injectable({ providedIn: 'root' })
export class VthumbResolve implements Resolve<IVthumb> {
    constructor(private service: VthumbService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVthumb> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Vthumb>) => response.ok),
                map((vthumb: HttpResponse<Vthumb>) => vthumb.body)
            );
        }
        return of(new Vthumb());
    }
}

export const vthumbRoute: Routes = [
    {
        path: '',
        component: VthumbComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.vthumb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VthumbDetailComponent,
        resolve: {
            vthumb: VthumbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vthumb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VthumbUpdateComponent,
        resolve: {
            vthumb: VthumbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vthumb.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VthumbUpdateComponent,
        resolve: {
            vthumb: VthumbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vthumb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vthumbPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VthumbDeletePopupComponent,
        resolve: {
            vthumb: VthumbResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vthumb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
