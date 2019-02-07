import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Community } from 'app/shared/model/community.model';
import { CommunityService } from './community.service';
import { CommunityComponent } from './community.component';
import { CommunityDetailComponent } from './community-detail.component';
import { CommunityUpdateComponent } from './community-update.component';
import { CommunityDeletePopupComponent } from './community-delete-dialog.component';
import { ICommunity } from 'app/shared/model/community.model';

@Injectable({ providedIn: 'root' })
export class CommunityResolve implements Resolve<ICommunity> {
    constructor(private service: CommunityService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICommunity> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Community>) => response.ok),
                map((community: HttpResponse<Community>) => community.body)
            );
        }
        return of(new Community());
    }
}

export const communityRoute: Routes = [
    {
        path: '',
        component: CommunityComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.community.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CommunityDetailComponent,
        resolve: {
            community: CommunityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.community.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CommunityUpdateComponent,
        resolve: {
            community: CommunityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.community.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CommunityUpdateComponent,
        resolve: {
            community: CommunityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.community.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const communityPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CommunityDeletePopupComponent,
        resolve: {
            community: CommunityResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.community.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
