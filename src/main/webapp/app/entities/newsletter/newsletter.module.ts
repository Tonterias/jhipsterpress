import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    NewsletterComponent,
    NewsletterDetailComponent,
    NewsletterUpdateComponent,
    NewsletterDeletePopupComponent,
    NewsletterDeleteDialogComponent,
    newsletterRoute,
    newsletterPopupRoute
} from './';

const ENTITY_STATES = [...newsletterRoute, ...newsletterPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NewsletterComponent,
        NewsletterDetailComponent,
        NewsletterUpdateComponent,
        NewsletterDeleteDialogComponent,
        NewsletterDeletePopupComponent
    ],
    entryComponents: [NewsletterComponent, NewsletterUpdateComponent, NewsletterDeleteDialogComponent, NewsletterDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressNewsletterModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
