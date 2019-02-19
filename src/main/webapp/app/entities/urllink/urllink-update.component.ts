import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IUrllink } from 'app/shared/model/urllink.model';
import { UrllinkService } from './urllink.service';

@Component({
    selector: 'jhi-urllink-update',
    templateUrl: './urllink-update.component.html'
})
export class UrllinkUpdateComponent implements OnInit {
    urllink: IUrllink;
    isSaving: boolean;

    constructor(protected urllinkService: UrllinkService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ urllink }) => {
            this.urllink = urllink;
            //            console.log('CONSOLOG: M:ngOnInit & O: this.urllink:', this.urllink);
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.urllink.id !== undefined) {
            this.subscribeToSaveResponse(this.urllinkService.update(this.urllink));
        } else {
            this.subscribeToSaveResponse(this.urllinkService.create(this.urllink));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IUrllink>>) {
        result.subscribe((res: HttpResponse<IUrllink>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
