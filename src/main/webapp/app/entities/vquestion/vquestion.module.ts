import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    VquestionComponent,
    VquestionDetailComponent,
    VquestionUpdateComponent,
    VquestionDeletePopupComponent,
    VquestionDeleteDialogComponent,
    vquestionRoute,
    vquestionPopupRoute
} from './';

const ENTITY_STATES = [...vquestionRoute, ...vquestionPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VquestionComponent,
        VquestionDetailComponent,
        VquestionUpdateComponent,
        VquestionDeleteDialogComponent,
        VquestionDeletePopupComponent
    ],
    entryComponents: [VquestionComponent, VquestionUpdateComponent, VquestionDeleteDialogComponent, VquestionDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressVquestionModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
