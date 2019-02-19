import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IPhoto } from 'app/shared/model/photo.model';
import { PhotoService } from './photo.service';
import { IAlbum } from 'app/shared/model/album.model';
import { AlbumService } from 'app/entities/album';
import { ICalbum } from 'app/shared/model/calbum.model';
import { CalbumService } from 'app/entities/calbum';
import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from 'app/entities/community';

import { AccountService } from 'app/core';

@Component({
    selector: 'jhi-photo-update',
    templateUrl: './photo-update.component.html'
})
export class PhotoUpdateComponent implements OnInit {
    photo: IPhoto;
    isSaving: boolean;

    albums: IAlbum[];
    communities: ICommunity[];
    calbums: ICalbum[];
    creationDate: string;
    currentAccount: any;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected photoService: PhotoService,
        protected albumService: AlbumService,
        protected calbumService: CalbumService,
        protected communityService: CommunityService,
        protected accountService: AccountService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ photo }) => {
            this.photo = photo;
            this.creationDate = moment().format(DATE_TIME_FORMAT);
            this.photo.creationDate = moment(this.creationDate);
        });
        this.accountService.identity().then(account => {
            this.currentAccount = account;
            this.myCommunities(this.currentAccount);
            this.myAlbums(this.currentAccount);
        });
        //        this.albumService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<IAlbum[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<IAlbum[]>) => response.body)
        //            )
        //            .subscribe((res: IAlbum[]) => (this.albums = res), (res: HttpErrorResponse) => this.onError(res.message));
        //        this.calbumService
        //            .query()
        //            .pipe(
        //                filter((mayBeOk: HttpResponse<ICalbum[]>) => mayBeOk.ok),
        //                map((response: HttpResponse<ICalbum[]>) => response.body)
        //            )
        //            .subscribe((res: ICalbum[]) => (this.calbums = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.photo, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.photo.creationDate = this.creationDate != null ? moment(this.creationDate, DATE_TIME_FORMAT) : null;
        if (this.photo.id !== undefined) {
            this.subscribeToSaveResponse(this.photoService.update(this.photo));
        } else {
            this.subscribeToSaveResponse(this.photoService.create(this.photo));
        }
    }

    protected myCommunities(currentAccount) {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.communityService.query(query).subscribe(
            (res: HttpResponse<ICommunity[]>) => {
                this.communities = res.body;
                //                console.log('CONSOLOG: M:myCommunities & O: res.body : ', res.body);
                this.communitiesCalbums(this.communities);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        //        console.log('CONSOLOG: M:myCommunities & O: this.currentAccount.id : ', this.currentAccount.id);
    }

    protected communitiesCalbums(communities) {
        const query = {};
        if (this.communities != null) {
            const arrayCommmunities = [];
            this.communities.forEach(community => {
                arrayCommmunities.push(community.id);
            });
            query['communityId.in'] = arrayCommmunities;
        }
        this.calbumService.query(query).subscribe(
            (res: HttpResponse<ICalbum[]>) => {
                this.calbums = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    protected myAlbums(currentAccount) {
        const query = {};
        if (this.currentAccount.id != null) {
            query['userId.equals'] = this.currentAccount.id;
        }
        this.albumService.query(query).subscribe(
            (res: HttpResponse<IAlbum[]>) => {
                this.albums = res.body;
                //                console.log('CONSOLOG: M:myAlbums & O: res.body : ', res.body);
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        //        console.log('CONSOLOG: M:myAlbums & O: this.currentAccount.id : ', this.currentAccount.id);
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPhoto>>) {
        result.subscribe((res: HttpResponse<IPhoto>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAlbumById(index: number, item: IAlbum) {
        return item.id;
    }

    trackCalbumById(index: number, item: ICalbum) {
        return item.id;
    }
}
