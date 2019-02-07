import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    VanswerComponent,
    VanswerDetailComponent,
    VanswerUpdateComponent,
    VanswerDeletePopupComponent,
    VanswerDeleteDialogComponent,
    vanswerRoute,
    vanswerPopupRoute
} from './';

const ENTITY_STATES = [...vanswerRoute, ...vanswerPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VanswerComponent,
        VanswerDetailComponent,
        VanswerUpdateComponent,
        VanswerDeleteDialogComponent,
        VanswerDeletePopupComponent
    ],
    entryComponents: [VanswerComponent, VanswerUpdateComponent, VanswerDeleteDialogComponent, VanswerDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressVanswerModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
