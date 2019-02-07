import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    CactivityComponent,
    CactivityDetailComponent,
    CactivityUpdateComponent,
    CactivityDeletePopupComponent,
    CactivityDeleteDialogComponent,
    cactivityRoute,
    cactivityPopupRoute
} from './';

const ENTITY_STATES = [...cactivityRoute, ...cactivityPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CactivityComponent,
        CactivityDetailComponent,
        CactivityUpdateComponent,
        CactivityDeleteDialogComponent,
        CactivityDeletePopupComponent
    ],
    entryComponents: [CactivityComponent, CactivityUpdateComponent, CactivityDeleteDialogComponent, CactivityDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressCactivityModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
