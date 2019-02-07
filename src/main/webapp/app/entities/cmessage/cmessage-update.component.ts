import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { ICmessage } from 'app/shared/model/cmessage.model';
import { CmessageService } from './cmessage.service';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';

@Component({
    selector: 'jhi-cmessage-update',
    templateUrl: './cmessage-update.component.html'
})
export class CmessageUpdateComponent implements OnInit {
    cmessage: ICmessage;
    isSaving: boolean;

    communities: ICommunity[];
    creationDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cmessageService: CmessageService,
        protected communityService: CommunityService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cmessage }) => {
            this.cmessage = cmessage;
            this.creationDate = this.cmessage.creationDate != null ? this.cmessage.creationDate.format(DATE_TIME_FORMAT) : null;
        });
        this.communityService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICommunity[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICommunity[]>) => response.body)
            )
            .subscribe((res: ICommunity[]) => (this.communities = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.cmessage.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.cmessage.id !== undefined) {
            this.subscribeToSaveResponse(this.cmessageService.update(this.cmessage));
        } else {
            this.subscribeToSaveResponse(this.cmessageService.create(this.cmessage));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICmessage>>) {
        result.subscribe((res: HttpResponse<ICmessage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCommunityById(index: number, item: ICommunity) {
        return item.id;
    }
}
