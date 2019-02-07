import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    VthumbComponent,
    VthumbDetailComponent,
    VthumbUpdateComponent,
    VthumbDeletePopupComponent,
    VthumbDeleteDialogComponent,
    vthumbRoute,
    vthumbPopupRoute
} from './';

const ENTITY_STATES = [...vthumbRoute, ...vthumbPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VthumbComponent, VthumbDetailComponent, VthumbUpdateComponent, VthumbDeleteDialogComponent, VthumbDeletePopupComponent],
    entryComponents: [VthumbComponent, VthumbUpdateComponent, VthumbDeleteDialogComponent, VthumbDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressVthumbModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
