import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVtopic } from 'app/shared/model/vtopic.model';
import { VtopicService } from './vtopic.service';

@Component({
    selector: 'jhi-vtopic-delete-dialog',
    templateUrl: './vtopic-delete-dialog.component.html'
})
export class VtopicDeleteDialogComponent {
    vtopic: IVtopic;

    constructor(protected vtopicService: VtopicService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vtopicService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vtopicListModification',
                content: 'Deleted an vtopic'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vtopic-delete-popup',
    template: ''
})
export class VtopicDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vtopic }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VtopicDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vtopic = vtopic;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/vtopic', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/vtopic', { outlets: { popup: null } }]);
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
