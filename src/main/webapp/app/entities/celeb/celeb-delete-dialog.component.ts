import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICeleb } from 'app/shared/model/celeb.model';
import { CelebService } from './celeb.service';

@Component({
    selector: 'jhi-celeb-delete-dialog',
    templateUrl: './celeb-delete-dialog.component.html'
})
export class CelebDeleteDialogComponent {
    celeb: ICeleb;

    constructor(protected celebService: CelebService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.celebService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'celebListModification',
                content: 'Deleted an celeb'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-celeb-delete-popup',
    template: ''
})
export class CelebDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ celeb }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CelebDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.celeb = celeb;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/celeb', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/celeb', { outlets: { popup: null } }]);
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
