/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterpressTestModule } from '../../../test.module';
import { CmessageDeleteDialogComponent } from 'app/entities/cmessage/cmessage-delete-dialog.component';
import { CmessageService } from 'app/entities/cmessage/cmessage.service';

describe('Component Tests', () => {
    describe('Cmessage Management Delete Component', () => {
        let comp: CmessageDeleteDialogComponent;
        let fixture: ComponentFixture<CmessageDeleteDialogComponent>;
        let service: CmessageService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CmessageDeleteDialogComponent]
            })
                .overrideTemplate(CmessageDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CmessageDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CmessageService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
