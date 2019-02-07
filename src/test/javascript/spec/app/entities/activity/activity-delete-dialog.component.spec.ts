/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterpressTestModule } from '../../../test.module';
import { ActivityDeleteDialogComponent } from 'app/entities/activity/activity-delete-dialog.component';
import { ActivityService } from 'app/entities/activity/activity.service';

describe('Component Tests', () => {
    describe('Activity Management Delete Component', () => {
        let comp: ActivityDeleteDialogComponent;
        let fixture: ComponentFixture<ActivityDeleteDialogComponent>;
        let service: ActivityService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [ActivityDeleteDialogComponent]
            })
                .overrideTemplate(ActivityDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ActivityDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ActivityService);
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
