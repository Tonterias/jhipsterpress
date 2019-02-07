import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    HOME_ROUTE,
    HomeComponent,
    ABOUT_ROUTE,
    ERROR404_ROUTE,
    HELP_ROUTE,
    PRIVACY_ROUTE,
    TERMS_ROUTE,
    CLIENTS_ROUTE,
    COMMINGSOON_ROUTE,
    PRICING_ROUTE
} from './';
import { AboutComponent } from '../static/about/about.component';
import { ErrorComponent } from 'app/static/error/error.component';
import { HelpComponent } from 'app/static/help/help.component';
import { PrivacyComponent } from 'app/static/privacy/privacy.component';
import { TermsComponent } from 'app/static/terms/terms.component';
import { ClientsComponent } from 'app/static/clients/clients.component';
import { CommingsoonComponent } from 'app/static/commingsoon/commingsoon.component';
import { PricingComponent } from 'app/static/pricing/pricing.component';

@NgModule({
    imports: [
        JhipsterpressSharedModule,
        RouterModule.forChild([
            HOME_ROUTE,
            ABOUT_ROUTE,
            ERROR404_ROUTE,
            HELP_ROUTE,
            PRIVACY_ROUTE,
            TERMS_ROUTE,
            CLIENTS_ROUTE,
            COMMINGSOON_ROUTE,
            PRICING_ROUTE
        ])
    ],
    declarations: [
        HomeComponent,
        AboutComponent,
        ErrorComponent,
        HelpComponent,
        PrivacyComponent,
        TermsComponent,
        ClientsComponent,
        CommingsoonComponent,
        PricingComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressHomeModule {}
