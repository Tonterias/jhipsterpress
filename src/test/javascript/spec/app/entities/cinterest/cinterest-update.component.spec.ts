/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CinterestUpdateComponent } from 'app/entities/cinterest/cinterest-update.component';
import { CinterestService } from 'app/entities/cinterest/cinterest.service';
import { Cinterest } from 'app/shared/model/cinterest.model';

describe('Component Tests', () => {
    describe('Cinterest Management Update Component', () => {
        let comp: CinterestUpdateComponent;
        let fixture: ComponentFixture<CinterestUpdateComponent>;
        let service: CinterestService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CinterestUpdateComponent]
            })
                .overrideTemplate(CinterestUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CinterestUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CinterestService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cinterest(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cinterest = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cinterest();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cinterest = entity;
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
