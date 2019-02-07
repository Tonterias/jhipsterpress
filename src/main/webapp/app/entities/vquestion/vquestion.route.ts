import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Vquestion } from 'app/shared/model/vquestion.model';
import { VquestionService } from './vquestion.service';
import { VquestionComponent } from './vquestion.component';
import { VquestionDetailComponent } from './vquestion-detail.component';
import { VquestionUpdateComponent } from './vquestion-update.component';
import { VquestionDeletePopupComponent } from './vquestion-delete-dialog.component';
import { IVquestion } from 'app/shared/model/vquestion.model';

@Injectable({ providedIn: 'root' })
export class VquestionResolve implements Resolve<IVquestion> {
    constructor(private service: VquestionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IVquestion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Vquestion>) => response.ok),
                map((vquestion: HttpResponse<Vquestion>) => vquestion.body)
            );
        }
        return of(new Vquestion());
    }
}

export const vquestionRoute: Routes = [
    {
        path: '',
        component: VquestionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.vquestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: VquestionDetailComponent,
        resolve: {
            vquestion: VquestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vquestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: VquestionUpdateComponent,
        resolve: {
            vquestion: VquestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vquestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: VquestionUpdateComponent,
        resolve: {
            vquestion: VquestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vquestion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const vquestionPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: VquestionDeletePopupComponent,
        resolve: {
            vquestion: VquestionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.vquestion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
