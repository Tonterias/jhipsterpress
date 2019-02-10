import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { JhipsterpressSharedModule } from 'app/shared';
import {
    BlockuserComponent,
    BlockinguserComponent,
    BlockeduserComponent,
    BlockuserDetailComponent,
    BlockuserUpdateComponent,
    BlockuserDeletePopupComponent,
    BlockuserDeleteDialogComponent,
    blockuserRoute,
    blockuserPopupRoute
} from './';

const ENTITY_STATES = [...blockuserRoute, ...blockuserPopupRoute];

@NgModule({
    imports: [JhipsterpressSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BlockuserComponent,
        BlockinguserComponent,
        BlockeduserComponent,
        BlockuserDetailComponent,
        BlockuserUpdateComponent,
        BlockuserDeleteDialogComponent,
        BlockuserDeletePopupComponent
    ],
    entryComponents: [BlockuserComponent, BlockuserUpdateComponent, BlockuserDeleteDialogComponent, BlockuserDeletePopupComponent],
    providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterpressBlockuserModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
