import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Topic } from 'app/shared/model/topic.model';
import { TopicService } from './topic.service';
import { TopicComponent } from './topic.component';
import { TopicDetailComponent } from './topic-detail.component';
import { TopicUpdateComponent } from './topic-update.component';
import { TopicDeletePopupComponent } from './topic-delete-dialog.component';
import { ITopic } from 'app/shared/model/topic.model';

@Injectable({ providedIn: 'root' })
export class TopicResolve implements Resolve<ITopic> {
    constructor(private service: TopicService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITopic> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Topic>) => response.ok),
                map((topic: HttpResponse<Topic>) => topic.body)
            );
        }
        return of(new Topic());
    }
}

export const topicRoute: Routes = [
    {
        path: '',
        component: TopicComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: [],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.topic.home.title'
        }
        //        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: TopicDetailComponent,
        resolve: {
            topic: TopicResolve
        },
        data: {
            authorities: [],
            pageTitle: 'jhipsterpressApp.topic.home.title'
        }
        //        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: TopicUpdateComponent,
        resolve: {
            topic: TopicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.topic.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: TopicUpdateComponent,
        resolve: {
            topic: TopicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.topic.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const topicPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: TopicDeletePopupComponent,
        resolve: {
            topic: TopicResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.topic.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
