import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { INotification } from 'app/shared/model/notification.model';
import { NotificationService } from './notification.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-notification-update',
    templateUrl: './notification-update.component.html'
})
export class NotificationUpdateComponent implements OnInit {
    notification: INotification;
    isSaving: boolean;

    users: IUser[];
    creationDate: string;
    notificationDate: string;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected notificationService: NotificationService,
        protected userService: UserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ notification }) => {
            this.notification = notification;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.notification.creationDate = moment(this.creationDate);
            this.notificationDate =
                this.notification.notificationDate != null ? this.notification.notificationDate.format(DATE_TIME_FORMAT) : null;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        //        this.userService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IUser[]>) => response.body)
        //            )
        //            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.notification.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        this.notification.notificationDate = this.notificationDate != null ? moment(this.notificationDate, DATE_TIME_FORMAT) : null;
        if (this.notification.id !== undefined) {
            //            console.log('CONSOLOG: M:save.update & O: this.notification : ', this.notification);
            this.subscribeToSaveResponse(this.notificationService.update(this.notification));
        } else {
            this.subscribeToSaveResponse(this.notificationService.create(this.notification));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INotification>>) {
        result.subscribe((res: HttpResponse<INotification>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
