import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICommunity } from 'app/shared/model/community.model';
import { CommunityService } from './community.service';

@Component({
    selector: 'jhi-community-delete-dialog',
    templateUrl: './community-delete-dialog.component.html'
})
export class CommunityDeleteDialogComponent {
    community: ICommunity;

    constructor(
        protected communityService: CommunityService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.communityService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'communityListModification',
                content: 'Deleted an community'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-community-delete-popup',
    template: ''
})
export class CommunityDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ community }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CommunityDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.community = community;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/community', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/community', { outlets: { popup: null } }]);
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
