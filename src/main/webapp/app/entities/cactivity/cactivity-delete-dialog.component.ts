import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICactivity } from 'app/shared/model/cactivity.model';
import { CactivityService } from './cactivity.service';

@Component({
    selector: 'jhi-cactivity-delete-dialog',
    templateUrl: './cactivity-delete-dialog.component.html'
})
export class CactivityDeleteDialogComponent {
    cactivity: ICactivity;

    constructor(
        protected cactivityService: CactivityService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cactivityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cactivityListModification',
                content: 'Deleted an cactivity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cactivity-delete-popup',
    template: ''
})
export class CactivityDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cactivity }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CactivityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cactivity = cactivity;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/cactivity', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/cactivity', { outlets: { popup: null } }]);
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
