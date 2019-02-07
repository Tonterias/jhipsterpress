import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Follow } from 'app/shared/model/follow.model';
import { FollowService } from './follow.service';
import { FollowComponent } from './follow.component';
import { FollowDetailComponent } from './follow-detail.component';
import { FollowUpdateComponent } from './follow-update.component';
import { FollowDeletePopupComponent } from './follow-delete-dialog.component';
import { IFollow } from 'app/shared/model/follow.model';

@Injectable({ providedIn: 'root' })
export class FollowResolve implements Resolve<IFollow> {
    constructor(private service: FollowService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFollow> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Follow>) => response.ok),
                map((follow: HttpResponse<Follow>) => follow.body)
            );
        }
        return of(new Follow());
    }
}

export const followRoute: Routes = [
    {
        path: '',
        component: FollowComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.follow.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: FollowDetailComponent,
        resolve: {
            follow: FollowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.follow.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: FollowUpdateComponent,
        resolve: {
            follow: FollowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.follow.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: FollowUpdateComponent,
        resolve: {
            follow: FollowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.follow.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const followPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: FollowDeletePopupComponent,
        resolve: {
            follow: FollowResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.follow.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
