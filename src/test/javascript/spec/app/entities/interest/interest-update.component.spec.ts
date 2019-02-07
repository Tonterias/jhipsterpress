/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { InterestUpdateComponent } from 'app/entities/interest/interest-update.component';
import { InterestService } from 'app/entities/interest/interest.service';
import { Interest } from 'app/shared/model/interest.model';

describe('Component Tests', () => {
    describe('Interest Management Update Component', () => {
        let comp: InterestUpdateComponent;
        let fixture: ComponentFixture<InterestUpdateComponent>;
        let service: InterestService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [InterestUpdateComponent]
            })
                .overrideTemplate(InterestUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InterestUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InterestService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Interest(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.interest = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Interest();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.interest = entity;
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
