/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { UrllinkUpdateComponent } from 'app/entities/urllink/urllink-update.component';
import { UrllinkService } from 'app/entities/urllink/urllink.service';
import { Urllink } from 'app/shared/model/urllink.model';

describe('Component Tests', () => {
    describe('Urllink Management Update Component', () => {
        let comp: UrllinkUpdateComponent;
        let fixture: ComponentFixture<UrllinkUpdateComponent>;
        let service: UrllinkService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [UrllinkUpdateComponent]
            })
                .overrideTemplate(UrllinkUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UrllinkUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UrllinkService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Urllink(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.urllink = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Urllink();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.urllink = entity;
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
