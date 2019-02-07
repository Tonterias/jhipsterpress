import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAlbum } from 'app/shared/model/album.model';
import { AlbumService } from './album.service';

@Component({
    selector: 'jhi-album-delete-dialog',
    templateUrl: './album-delete-dialog.component.html'
})
export class AlbumDeleteDialogComponent {
    album: IAlbum;

    constructor(protected albumService: AlbumService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.albumService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'albumListModification',
                content: 'Deleted an album'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-album-delete-popup',
    template: ''
})
export class AlbumDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ album }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AlbumDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.album = album;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/album', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/album', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
