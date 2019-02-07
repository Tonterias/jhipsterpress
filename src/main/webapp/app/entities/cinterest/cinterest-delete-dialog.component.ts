import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICinterest } from 'app/shared/model/cinterest.model';
import { CinterestService } from './cinterest.service';

@Component({
    selector: 'jhi-cinterest-delete-dialog',
    templateUrl: './cinterest-delete-dialog.component.html'
})
export class CinterestDeleteDialogComponent {
    cinterest: ICinterest;

    constructor(
        protected cinterestService: CinterestService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cinterestService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cinterestListModification',
                content: 'Deleted an cinterest'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cinterest-delete-popup',
    template: ''
})
export class CinterestDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cinterest }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CinterestDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cinterest = cinterest;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/cinterest', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/cinterest', { outlets: { popup: null } }]);
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
