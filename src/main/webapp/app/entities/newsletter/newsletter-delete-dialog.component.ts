import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INewsletter } from 'app/shared/model/newsletter.model';
import { NewsletterService } from './newsletter.service';

@Component({
    selector: 'jhi-newsletter-delete-dialog',
    templateUrl: './newsletter-delete-dialog.component.html'
})
export class NewsletterDeleteDialogComponent {
    newsletter: INewsletter;

    constructor(
        protected newsletterService: NewsletterService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.newsletterService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'newsletterListModification',
                content: 'Deleted an newsletter'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-newsletter-delete-popup',
    template: ''
})
export class NewsletterDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ newsletter }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NewsletterDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.newsletter = newsletter;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/newsletter', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/newsletter', { outlets: { popup: null } }]);
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
