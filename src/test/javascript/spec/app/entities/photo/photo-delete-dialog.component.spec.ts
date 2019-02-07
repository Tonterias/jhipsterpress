/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterpressTestModule } from '../../../test.module';
import { PhotoDeleteDialogComponent } from 'app/entities/photo/photo-delete-dialog.component';
import { PhotoService } from 'app/entities/photo/photo.service';

describe('Component Tests', () => {
    describe('Photo Management Delete Component', () => {
        let comp: PhotoDeleteDialogComponent;
        let fixture: ComponentFixture<PhotoDeleteDialogComponent>;
        let service: PhotoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [PhotoDeleteDialogComponent]
            })
                .overrideTemplate(PhotoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PhotoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhotoService);
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
