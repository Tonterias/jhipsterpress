import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Blockuser } from 'app/shared/model/blockuser.model';
import { BlockuserService } from './blockuser.service';
import { BlockuserComponent } from './blockuser.component';
import { BlockinguserComponent } from './blockinguser.component';
import { BlockeduserComponent } from './blockeduser.component';
import { BlockuserDetailComponent } from './blockuser-detail.component';
import { BlockuserUpdateComponent } from './blockuser-update.component';
import { BlockuserDeletePopupComponent } from './blockuser-delete-dialog.component';
import { IBlockuser } from 'app/shared/model/blockuser.model';

@Injectable({ providedIn: 'root' })
export class BlockuserResolve implements Resolve<IBlockuser> {
    constructor(private service: BlockuserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IBlockuser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Blockuser>) => response.ok),
                map((blockuser: HttpResponse<Blockuser>) => blockuser.body)
            );
        }
        return of(new Blockuser());
    }
}

export const blockuserRoute: Routes = [
    {
        path: '',
        component: BlockuserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.blockuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'blockinguser',
        component: BlockinguserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.blockuser.home.titleBlockingusers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'blockeduser',
        component: BlockeduserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.blockuser.home.titleBlockedusers'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: BlockuserDetailComponent,
        resolve: {
            blockuser: BlockuserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.blockuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: BlockuserUpdateComponent,
        resolve: {
            blockuser: BlockuserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.blockuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: BlockuserUpdateComponent,
        resolve: {
            blockuser: BlockuserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.blockuser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const blockuserPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: BlockuserDeletePopupComponent,
        resolve: {
            blockuser: BlockuserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.blockuser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
