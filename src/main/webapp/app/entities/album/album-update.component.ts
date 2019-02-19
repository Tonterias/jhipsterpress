import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IAlbum } from 'app/shared/model/album.model';
import { AlbumService } from './album.service';
import { IUser, UserService } from 'app/core';
import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-album-update',
    templateUrl: './album-update.component.html'
})
export class AlbumUpdateComponent implements OnInit {
    private _album: IAlbum;
    isSaving: boolean;

    users: IUser[];
    user: IUser;
    creationDate: string;

    currentAccount: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected albumService: AlbumService,
        protected userService: UserService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ album }) => {
            this.album = album;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            //            this.creationDate = this.album.creationDate != null ? this.album.creationDate.format(DATE_TIME_FORMAT) : null;
            this.album.creationDate = moment(this.creationDate);
        });
        //        console.log('CONSOLOG: M:ngOnInit & O: this.isSaving : ', this.isSaving);
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myUser();
        });
        this.userService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IUser[]>) => response.body)
            )
            .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.album.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.album.id !== undefined) {
            //            console.log('CONSOLOG: M:save & O: this.album : ', this.album);
            this.subscribeToSaveResponse(this.albumService.update(this.album));
        } else {
            //            console.log('CONSOLOG: M:save & O: this.album : ', this.album);
            this.subscribeToSaveResponse(this.albumService.create(this.album));
        }
    }

    protected myUser() {
        this.userService.findById(this.currentAccount.id).subscribe(
            (res: HttpResponse<IUser>) => {
                this.album.userId = res.body.id;
                //                console.log('CONSOLOG: M:myUser & O: res.body : ', res.body);
                //                console.log('CONSOLOG: M:myUser & O: this.album.userId : ', this.album.userId);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAlbum>>) {
        result.subscribe((res: HttpResponse<IAlbum>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    get album() {
        return this._album;
    }

    set album(album: IAlbum) {
        this._album = album;
        this.creationDate = moment(album.creationDate).format(DATE_TIME_FORMAT);
    }
}
