/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { PhotoUpdateComponent } from 'app/entities/photo/photo-update.component';
import { PhotoService } from 'app/entities/photo/photo.service';
import { Photo } from 'app/shared/model/photo.model';

describe('Component Tests', () => {
    describe('Photo Management Update Component', () => {
        let comp: PhotoUpdateComponent;
        let fixture: ComponentFixture<PhotoUpdateComponent>;
        let service: PhotoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [PhotoUpdateComponent]
            })
                .overrideTemplate(PhotoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PhotoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PhotoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Photo(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.photo = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Photo();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.photo = entity;
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
