import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVquestion } from 'app/shared/model/vquestion.model';
import { VquestionService } from './vquestion.service';

@Component({
    selector: 'jhi-vquestion-delete-dialog',
    templateUrl: './vquestion-delete-dialog.component.html'
})
export class VquestionDeleteDialogComponent {
    vquestion: IVquestion;

    constructor(
        protected vquestionService: VquestionService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.vquestionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'vquestionListModification',
                content: 'Deleted an vquestion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-vquestion-delete-popup',
    template: ''
})
export class VquestionDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ vquestion }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VquestionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.vquestion = vquestion;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/vquestion', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/vquestion', { outlets: { popup: null } }]);
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
