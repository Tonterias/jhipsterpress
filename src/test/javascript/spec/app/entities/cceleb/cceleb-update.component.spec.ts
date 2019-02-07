/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CcelebUpdateComponent } from 'app/entities/cceleb/cceleb-update.component';
import { CcelebService } from 'app/entities/cceleb/cceleb.service';
import { Cceleb } from 'app/shared/model/cceleb.model';

describe('Component Tests', () => {
    describe('Cceleb Management Update Component', () => {
        let comp: CcelebUpdateComponent;
        let fixture: ComponentFixture<CcelebUpdateComponent>;
        let service: CcelebService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CcelebUpdateComponent]
            })
                .overrideTemplate(CcelebUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CcelebUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CcelebService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cceleb(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cceleb = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Cceleb();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cceleb = entity;
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
