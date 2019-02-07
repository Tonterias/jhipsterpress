import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUrllink } from 'app/shared/model/urllink.model';
import { UrllinkService } from './urllink.service';

@Component({
    selector: 'jhi-urllink-delete-dialog',
    templateUrl: './urllink-delete-dialog.component.html'
})
export class UrllinkDeleteDialogComponent {
    urllink: IUrllink;

    constructor(protected urllinkService: UrllinkService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.urllinkService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'urllinkListModification',
                content: 'Deleted an urllink'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-urllink-delete-popup',
    template: ''
})
export class UrllinkDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ urllink }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UrllinkDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.urllink = urllink;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/urllink', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/urllink', { outlets: { popup: null } }]);
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
