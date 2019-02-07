/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VquestionUpdateComponent } from 'app/entities/vquestion/vquestion-update.component';
import { VquestionService } from 'app/entities/vquestion/vquestion.service';
import { Vquestion } from 'app/shared/model/vquestion.model';

describe('Component Tests', () => {
    describe('Vquestion Management Update Component', () => {
        let comp: VquestionUpdateComponent;
        let fixture: ComponentFixture<VquestionUpdateComponent>;
        let service: VquestionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VquestionUpdateComponent]
            })
                .overrideTemplate(VquestionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VquestionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VquestionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vquestion(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vquestion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vquestion();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vquestion = entity;
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
