import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IConfigVariables } from 'app/shared/model/config-variables.model';
import { ConfigVariablesService } from './config-variables.service';

@Component({
    selector: 'jhi-config-variables-update',
    templateUrl: './config-variables-update.component.html'
})
export class ConfigVariablesUpdateComponent implements OnInit {
    configVariables: IConfigVariables;
    isSaving: boolean;

    constructor(protected configVariablesService: ConfigVariablesService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ configVariables }) => {
            this.configVariables = configVariables;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.configVariables.id !== undefined) {
            this.subscribeToSaveResponse(this.configVariablesService.update(this.configVariables));
        } else {
            this.subscribeToSaveResponse(this.configVariablesService.create(this.configVariables));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IConfigVariables>>) {
        result.subscribe((res: HttpResponse<IConfigVariables>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
