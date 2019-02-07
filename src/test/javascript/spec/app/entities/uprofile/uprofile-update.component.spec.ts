/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { UprofileUpdateComponent } from 'app/entities/uprofile/uprofile-update.component';
import { UprofileService } from 'app/entities/uprofile/uprofile.service';
import { Uprofile } from 'app/shared/model/uprofile.model';

describe('Component Tests', () => {
    describe('Uprofile Management Update Component', () => {
        let comp: UprofileUpdateComponent;
        let fixture: ComponentFixture<UprofileUpdateComponent>;
        let service: UprofileService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [UprofileUpdateComponent]
            })
                .overrideTemplate(UprofileUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UprofileUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UprofileService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Uprofile(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.uprofile = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Uprofile();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.uprofile = entity;
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
