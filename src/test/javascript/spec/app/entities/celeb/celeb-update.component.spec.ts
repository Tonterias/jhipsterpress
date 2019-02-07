/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CelebUpdateComponent } from 'app/entities/celeb/celeb-update.component';
import { CelebService } from 'app/entities/celeb/celeb.service';
import { Celeb } from 'app/shared/model/celeb.model';

describe('Component Tests', () => {
    describe('Celeb Management Update Component', () => {
        let comp: CelebUpdateComponent;
        let fixture: ComponentFixture<CelebUpdateComponent>;
        let service: CelebService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CelebUpdateComponent]
            })
                .overrideTemplate(CelebUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CelebUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CelebService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Celeb(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.celeb = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Celeb();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.celeb = entity;
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
