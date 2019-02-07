import { Route } from '@angular/router';

import { HomeComponent } from './';
import { AboutComponent } from 'app/static/about/about.component';
import { ErrorComponent } from 'app/static/error/error.component';
import { HelpComponent } from 'app/static/help/help.component';
import { PrivacyComponent } from 'app/static/privacy/privacy.component';
import { TermsComponent } from 'app/static/terms/terms.component';
import { ClientsComponent } from 'app/static/clients/clients.component';
import { CommingsoonComponent } from 'app/static/commingsoon/commingsoon.component';
import { PricingComponent } from 'app/static/pricing/pricing.component';

export const HOME_ROUTE: Route = {
    path: '',
    component: HomeComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const ABOUT_ROUTE: Route = {
    path: 'about',
    component: AboutComponent,
    data: {
        authorities: [],
        pageTitle: 'global.menu.about'
    }
};

export const ERROR404_ROUTE: Route = {
    path: 'error404',
    component: ErrorComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const HELP_ROUTE: Route = {
    path: 'help',
    component: HelpComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const PRIVACY_ROUTE: Route = {
    path: 'privacy',
    component: PrivacyComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const TERMS_ROUTE: Route = {
    path: 'terms',
    component: TermsComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const CLIENTS_ROUTE: Route = {
    path: 'clients',
    component: ClientsComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const COMMINGSOON_ROUTE: Route = {
    path: 'commingsoon',
    component: CommingsoonComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};

export const PRICING_ROUTE: Route = {
    path: 'pricing',
    component: PricingComponent,
    data: {
        authorities: [],
        pageTitle: 'home.title'
    }
};
