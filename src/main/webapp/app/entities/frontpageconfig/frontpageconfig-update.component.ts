import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IFrontpageconfig } from 'app/shared/model/frontpageconfig.model';
import { FrontpageconfigService } from './frontpageconfig.service';

@Component({
    selector: 'jhi-frontpageconfig-update',
    templateUrl: './frontpageconfig-update.component.html'
})
export class FrontpageconfigUpdateComponent implements OnInit {
    frontpageconfig: IFrontpageconfig;
    isSaving: boolean;
    creationDate: string;

    constructor(protected frontpageconfigService: FrontpageconfigService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ frontpageconfig }) => {
            this.frontpageconfig = frontpageconfig;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.frontpageconfig.creationDate = moment(this.creationDate);
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.frontpageconfig.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.frontpageconfig.id !== undefined) {
            this.subscribeToSaveResponse(this.frontpageconfigService.update(this.frontpageconfig));
        } else {
            this.subscribeToSaveResponse(this.frontpageconfigService.create(this.frontpageconfig));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFrontpageconfig>>) {
        result.subscribe((res: HttpResponse<IFrontpageconfig>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
