import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    ConfigVariablesComponent,
    ConfigVariablesDetailComponent,
    ConfigVariablesUpdateComponent,
    ConfigVariablesDeletePopupComponent,
    ConfigVariablesDeleteDialogComponent,
    configVariablesRoute,
    configVariablesPopupRoute
} from './';

const ENTITY_STATES = [...configVariablesRoute, ...configVariablesPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConfigVariablesComponent,
        ConfigVariablesDetailComponent,
        ConfigVariablesUpdateComponent,
        ConfigVariablesDeleteDialogComponent,
        ConfigVariablesDeletePopupComponent
    ],
    entryComponents: [
        ConfigVariablesComponent,
        ConfigVariablesUpdateComponent,
        ConfigVariablesDeleteDialogComponent,
        ConfigVariablesDeletePopupComponent
    ],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressConfigVariablesModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
