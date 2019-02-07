import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    CmessageComponent,
    CmessageDetailComponent,
    CmessageUpdateComponent,
    CmessageDeletePopupComponent,
    CmessageDeleteDialogComponent,
    cmessageRoute,
    cmessagePopupRoute
} from './';

const ENTITY_STATES = [...cmessageRoute, ...cmessagePopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CmessageComponent,
        CmessageDetailComponent,
        CmessageUpdateComponent,
        CmessageDeleteDialogComponent,
        CmessageDeletePopupComponent
    ],
    entryComponents: [CmessageComponent, CmessageUpdateComponent, CmessageDeleteDialogComponent, CmessageDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressCmessageModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
