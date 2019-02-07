/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CalbumUpdateComponent } from 'app/entities/calbum/calbum-update.component';
import { CalbumService } from 'app/entities/calbum/calbum.service';
import { Calbum } from 'app/shared/model/calbum.model';

describe('Component Tests', () => {
    describe('Calbum Management Update Component', () => {
        let comp: CalbumUpdateComponent;
        let fixture: ComponentFixture<CalbumUpdateComponent>;
        let service: CalbumService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CalbumUpdateComponent]
            })
                .overrideTemplate(CalbumUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CalbumUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CalbumService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Calbum(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.calbum = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Calbum();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.calbum = entity;
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
