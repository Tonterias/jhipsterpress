/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CactivityUpdateComponent } from 'app/entities/cactivity/cactivity-update.component';
import { CactivityService } from 'app/entities/cactivity/cactivity.service';
import { Cactivity } from 'app/shared/model/cactivity.model';

describe('Component Tests', () => {
    describe('Cactivity Management Update Component', () => {
        let comp: CactivityUpdateComponent;
        let fixture: ComponentFixture<CactivityUpdateComponent>;
        let service: CactivityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CactivityUpdateComponent]
            })
                .overrideTemplate(CactivityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CactivityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CactivityService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cactivity(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cactivity = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cactivity();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cactivity = entity;
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
