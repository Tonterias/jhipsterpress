import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConfigVariables } from 'app/shared/model/config-variables.model';
import { ConfigVariablesService } from './config-variables.service';

@Component({
    selector: 'jhi-config-variables-delete-dialog',
    templateUrl: './config-variables-delete-dialog.component.html'
})
export class ConfigVariablesDeleteDialogComponent {
    configVariables: IConfigVariables;

    constructor(
        protected configVariablesService: ConfigVariablesService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.configVariablesService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'configVariablesListModification',
                content: 'Deleted an configVariables'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-config-variables-delete-popup',
    template: ''
})
export class ConfigVariablesDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ configVariables }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConfigVariablesDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.configVariables = configVariables;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/config-variables', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/config-variables', { outlets: { popup: null } }]);
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
