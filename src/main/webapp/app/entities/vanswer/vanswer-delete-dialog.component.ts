import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVanswer } from 'app/shared/model/vanswer.model';
import { VanswerService } from './vanswer.service';

@Component({
    selector: 'jhi-vanswer-delete-dialog',
    templateUrl: './vanswer-delete-dialog.component.html'
})
export class VanswerDeleteDialogComponent {
    vanswer: IVanswer;

    constructor(protected vanswerService: VanswerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vanswerService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vanswerListModification',
                content: 'Deleted an vanswer'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vanswer-delete-popup',
    template: ''
})
export class VanswerDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vanswer }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VanswerDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vanswer = vanswer;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/vanswer', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/vanswer', { outlets: { popup: null } }]);
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
