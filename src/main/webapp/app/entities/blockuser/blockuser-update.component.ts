import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IBlockuser } from 'app/shared/model/blockuser.model';
import { BlockuserService } from './blockuser.service';
import { IUser, UserService } from 'app/core';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';

@Component({
    selector: 'jhi-blockuser-update',
    templateUrl: './blockuser-update.component.html'
})
export class BlockuserUpdateComponent implements OnInit {
    blockuser: IBlockuser;
    isSaving: boolean;

    users: IUser[];

    communities: ICommunity[];
    creationDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected blockuserService: BlockuserService,
        protected userService: UserService,
        protected communityService: CommunityService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ blockuser }) => {
            this.blockuser = blockuser;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            //            this.creationDate = this.blockuser.creationDate != null ? this.blockuser.creationDate.format(DATE_TIME_FORMAT) : null;
            this.blockuser.creationDate = moment(this.creationDate);
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.blockuser.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.blockuser.id !== undefined) {
            this.subscribeToSaveResponse(this.blockuserService.update(this.blockuser));
        } else {
            this.subscribeToSaveResponse(this.blockuserService.create(this.blockuser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IBlockuser>>) {
        result.subscribe((res: HttpResponse<IBlockuser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackCommunityById(index: number, item: ICommunity) {
        return item.id;
    }
}
