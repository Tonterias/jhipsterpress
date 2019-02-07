import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    UrllinkComponent,
    UrllinkDetailComponent,
    UrllinkUpdateComponent,
    UrllinkDeletePopupComponent,
    UrllinkDeleteDialogComponent,
    urllinkRoute,
    urllinkPopupRoute
} from './';

const ENTITY_STATES = [...urllinkRoute, ...urllinkPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UrllinkComponent,
        UrllinkDetailComponent,
        UrllinkUpdateComponent,
        UrllinkDeleteDialogComponent,
        UrllinkDeletePopupComponent
    ],
    entryComponents: [UrllinkComponent, UrllinkUpdateComponent, UrllinkDeleteDialogComponent, UrllinkDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressUrllinkModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
