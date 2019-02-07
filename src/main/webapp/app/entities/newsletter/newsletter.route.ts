import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Newsletter } from 'app/shared/model/newsletter.model';
import { NewsletterService } from './newsletter.service';
import { NewsletterComponent } from './newsletter.component';
import { NewsletterDetailComponent } from './newsletter-detail.component';
import { NewsletterUpdateComponent } from './newsletter-update.component';
import { NewsletterDeletePopupComponent } from './newsletter-delete-dialog.component';
import { INewsletter } from 'app/shared/model/newsletter.model';

@Injectable({ providedIn: 'root' })
export class NewsletterResolve implements Resolve<INewsletter> {
    constructor(private service: NewsletterService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<INewsletter> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Newsletter>) => response.ok),
                map((newsletter: HttpResponse<Newsletter>) => newsletter.body)
            );
        }
        return of(new Newsletter());
    }
}

export const newsletterRoute: Routes = [
    {
        path: '',
        component: NewsletterComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterpressApp.newsletter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: NewsletterDetailComponent,
        resolve: {
            newsletter: NewsletterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.newsletter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: NewsletterUpdateComponent,
        resolve: {
            newsletter: NewsletterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.newsletter.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: NewsletterUpdateComponent,
        resolve: {
            newsletter: NewsletterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.newsletter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const newsletterPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: NewsletterDeletePopupComponent,
        resolve: {
            newsletter: NewsletterResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterpressApp.newsletter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
