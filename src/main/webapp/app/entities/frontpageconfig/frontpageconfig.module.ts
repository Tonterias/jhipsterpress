import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    FrontpageconfigComponent,
    FrontpageconfigDetailComponent,
    FrontpageconfigUpdateComponent,
    FrontpageconfigDeletePopupComponent,
    FrontpageconfigDeleteDialogComponent,
    frontpageconfigRoute,
    frontpageconfigPopupRoute
} from './';

const ENTITY_STATES = [...frontpageconfigRoute, ...frontpageconfigPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FrontpageconfigComponent,
        FrontpageconfigDetailComponent,
        FrontpageconfigUpdateComponent,
        FrontpageconfigDeleteDialogComponent,
        FrontpageconfigDeletePopupComponent
    ],
    entryComponents: [
        FrontpageconfigComponent,
        FrontpageconfigUpdateComponent,
        FrontpageconfigDeleteDialogComponent,
        FrontpageconfigDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressFrontpageconfigModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
