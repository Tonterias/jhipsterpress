/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VtopicUpdateComponent } from 'app/entities/vtopic/vtopic-update.component';
import { VtopicService } from 'app/entities/vtopic/vtopic.service';
import { Vtopic } from 'app/shared/model/vtopic.model';

describe('Component Tests', () => {
    describe('Vtopic Management Update Component', () => {
        let comp: VtopicUpdateComponent;
        let fixture: ComponentFixture<VtopicUpdateComponent>;
        let service: VtopicService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VtopicUpdateComponent]
            })
                .overrideTemplate(VtopicUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VtopicUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VtopicService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vtopic(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vtopic = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vtopic();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vtopic = entity;
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
