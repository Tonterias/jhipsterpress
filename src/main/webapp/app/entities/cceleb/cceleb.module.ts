import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    CcelebComponent,
    CcelebDetailComponent,
    CcelebUpdateComponent,
    CcelebDeletePopupComponent,
    CcelebDeleteDialogComponent,
    ccelebRoute,
    ccelebPopupRoute
} from './';

const ENTITY_STATES = [...ccelebRoute, ...ccelebPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CcelebComponent, CcelebDetailComponent, CcelebUpdateComponent, CcelebDeleteDialogComponent, CcelebDeletePopupComponent],
    entryComponents: [CcelebComponent, CcelebUpdateComponent, CcelebDeleteDialogComponent, CcelebDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressCcelebModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
