/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterpressTestModule } from '../../../test.module';
import { ConfigVariablesDeleteDialogComponent } from 'app/entities/config-variables/config-variables-delete-dialog.component';
import { ConfigVariablesService } from 'app/entities/config-variables/config-variables.service';

describe('Component Tests', () => {
    describe('ConfigVariables Management Delete Component', () => {
        let comp: ConfigVariablesDeleteDialogComponent;
        let fixture: ComponentFixture<ConfigVariablesDeleteDialogComponent>;
        let service: ConfigVariablesService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [ConfigVariablesDeleteDialogComponent]
            })
                .overrideTemplate(ConfigVariablesDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfigVariablesDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigVariablesService);
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
