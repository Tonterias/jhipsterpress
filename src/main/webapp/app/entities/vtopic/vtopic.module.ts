import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    VtopicComponent,
    VtopicDetailComponent,
    VtopicUpdateComponent,
    VtopicDeletePopupComponent,
    VtopicDeleteDialogComponent,
    vtopicRoute,
    vtopicPopupRoute
} from './';

const ENTITY_STATES = [...vtopicRoute, ...vtopicPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VtopicComponent, VtopicDetailComponent, VtopicUpdateComponent, VtopicDeleteDialogComponent, VtopicDeletePopupComponent],
    entryComponents: [VtopicComponent, VtopicUpdateComponent, VtopicDeleteDialogComponent, VtopicDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressVtopicModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
