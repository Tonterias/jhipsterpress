/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CmessageUpdateComponent } from 'app/entities/cmessage/cmessage-update.component';
import { CmessageService } from 'app/entities/cmessage/cmessage.service';
import { Cmessage } from 'app/shared/model/cmessage.model';

describe('Component Tests', () => {
    describe('Cmessage Management Update Component', () => {
        let comp: CmessageUpdateComponent;
        let fixture: ComponentFixture<CmessageUpdateComponent>;
        let service: CmessageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CmessageUpdateComponent]
            })
                .overrideTemplate(CmessageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CmessageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CmessageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cmessage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cmessage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cmessage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cmessage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
