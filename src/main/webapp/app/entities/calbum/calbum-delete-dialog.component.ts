import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICalbum } from 'app/shared/model/calbum.model';
import { CalbumService } from './calbum.service';

@Component({
    selector: 'jhi-calbum-delete-dialog',
    templateUrl: './calbum-delete-dialog.component.html'
})
export class CalbumDeleteDialogComponent {
    calbum: ICalbum;

    constructor(protected calbumService: CalbumService, protected activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.calbumService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'calbumListModification',
                content: 'Deleted an calbum'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-calbum-delete-popup',
    template: ''
})
export class CalbumDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ calbum }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CalbumDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.calbum = calbum;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/calbum', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/calbum', { outlets: { popup: null } }]);
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
