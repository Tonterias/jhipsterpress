import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    UprofileComponent,
    UprofileDetailComponent,
    UprofileUpdateComponent,
    UprofileDeletePopupComponent,
    UprofileDeleteDialogComponent,
    uprofileRoute,
    uprofilePopupRoute
} from './';

const ENTITY_STATES = [...uprofileRoute, ...uprofilePopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UprofileComponent,
        UprofileDetailComponent,
        UprofileUpdateComponent,
        UprofileDeleteDialogComponent,
        UprofileDeletePopupComponent
    ],
    entryComponents: [UprofileComponent, UprofileUpdateComponent, UprofileDeleteDialogComponent, UprofileDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressUprofileModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
