import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    CinterestComponent,
    CinterestDetailComponent,
    CinterestUpdateComponent,
    CinterestDeletePopupComponent,
    CinterestDeleteDialogComponent,
    cinterestRoute,
    cinterestPopupRoute
} from './';

const ENTITY_STATES = [...cinterestRoute, ...cinterestPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CinterestComponent,
        CinterestDetailComponent,
        CinterestUpdateComponent,
        CinterestDeleteDialogComponent,
        CinterestDeletePopupComponent
    ],
    entryComponents: [CinterestComponent, CinterestUpdateComponent, CinterestDeleteDialogComponent, CinterestDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressCinterestModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
