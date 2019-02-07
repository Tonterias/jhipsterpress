/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VanswerUpdateComponent } from 'app/entities/vanswer/vanswer-update.component';
import { VanswerService } from 'app/entities/vanswer/vanswer.service';
import { Vanswer } from 'app/shared/model/vanswer.model';

describe('Component Tests', () => {
    describe('Vanswer Management Update Component', () => {
        let comp: VanswerUpdateComponent;
        let fixture: ComponentFixture<VanswerUpdateComponent>;
        let service: VanswerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VanswerUpdateComponent]
            })
                .overrideTemplate(VanswerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VanswerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VanswerService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vanswer(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vanswer = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vanswer();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vanswer = entity;
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
