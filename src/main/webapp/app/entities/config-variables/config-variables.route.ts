import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConfigVariables } from 'app/shared/model/config-variables.model';
import { ConfigVariablesService } from './config-variables.service';
import { ConfigVariablesComponent } from './config-variables.component';
import { ConfigVariablesDetailComponent } from './config-variables-detail.component';
import { ConfigVariablesUpdateComponent } from './config-variables-update.component';
import { ConfigVariablesDeletePopupComponent } from './config-variables-delete-dialog.component';
import { IConfigVariables } from 'app/shared/model/config-variables.model';

@Injectable({ providedIn: 'root' })
export class ConfigVariablesResolve implements Resolve<IConfigVariables> {
    constructor(private service: ConfigVariablesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IConfigVariables> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ConfigVariables>) => response.ok),
                map((configVariables: HttpResponse<ConfigVariables>) => configVariables.body)
            );
        }
        return of(new ConfigVariables());
    }
}

export const configVariablesRoute: Routes = [
    {
        path: '',
        component: ConfigVariablesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: [],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.configVariables.home.title'
        }
        //        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ConfigVariablesDetailComponent,
        resolve: {
            configVariables: ConfigVariablesResolve
        },
        data: {
            authorities: [],
            pageTitle: 'jhipsterpressApp.configVariables.home.title'
        }
        //        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ConfigVariablesUpdateComponent,
        resolve: {
            configVariables: ConfigVariablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.configVariables.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ConfigVariablesUpdateComponent,
        resolve: {
            configVariables: ConfigVariablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.configVariables.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const configVariablesPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ConfigVariablesDeletePopupComponent,
        resolve: {
            configVariables: ConfigVariablesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.configVariables.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
