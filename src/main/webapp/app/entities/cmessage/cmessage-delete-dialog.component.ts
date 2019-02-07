import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICmessage } from 'app/shared/model/cmessage.model';
import { CmessageService } from './cmessage.service';

@Component({
    selector: 'jhi-cmessage-delete-dialog',
    templateUrl: './cmessage-delete-dialog.component.html'
})
export class CmessageDeleteDialogComponent {
    cmessage: ICmessage;

    constructor(protected cmessageService: CmessageService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cmessageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cmessageListModification',
                content: 'Deleted an cmessage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cmessage-delete-popup',
    template: ''
})
export class CmessageDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cmessage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CmessageDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cmessage = cmessage;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/cmessage', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/cmessage', { outlets: { popup: null } }]);
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
