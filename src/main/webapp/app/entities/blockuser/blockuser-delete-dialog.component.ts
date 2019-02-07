import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBlockuser } from 'app/shared/model/blockuser.model';
import { BlockuserService } from './blockuser.service';

@Component({
    selector: 'jhi-blockuser-delete-dialog',
    templateUrl: './blockuser-delete-dialog.component.html'
})
export class BlockuserDeleteDialogComponent {
    blockuser: IBlockuser;

    constructor(
        protected blockuserService: BlockuserService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.blockuserService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'blockuserListModification',
                content: 'Deleted an blockuser'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-blockuser-delete-popup',
    template: ''
})
export class BlockuserDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ blockuser }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(BlockuserDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.blockuser = blockuser;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/blockuser', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/blockuser', { outlets: { popup: null } }]);
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
