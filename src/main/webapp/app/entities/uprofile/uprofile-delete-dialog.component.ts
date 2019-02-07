import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUprofile } from 'app/shared/model/uprofile.model';
import { UprofileService } from './uprofile.service';

@Component({
    selector: 'jhi-uprofile-delete-dialog',
    templateUrl: './uprofile-delete-dialog.component.html'
})
export class UprofileDeleteDialogComponent {
    uprofile: IUprofile;

    constructor(protected uprofileService: UprofileService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.uprofileService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'uprofileListModification',
                content: 'Deleted an uprofile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-uprofile-delete-popup',
    template: ''
})
export class UprofileDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ uprofile }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UprofileDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.uprofile = uprofile;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/uprofile', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/uprofile', { outlets: { popup: null } }]);
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
