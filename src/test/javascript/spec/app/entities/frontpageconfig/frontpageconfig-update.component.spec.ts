/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { FrontpageconfigUpdateComponent } from 'app/entities/frontpageconfig/frontpageconfig-update.component';
import { FrontpageconfigService } from 'app/entities/frontpageconfig/frontpageconfig.service';
import { Frontpageconfig } from 'app/shared/model/frontpageconfig.model';

describe('Component Tests', () => {
    describe('Frontpageconfig Management Update Component', () => {
        let comp: FrontpageconfigUpdateComponent;
        let fixture: ComponentFixture<FrontpageconfigUpdateComponent>;
        let service: FrontpageconfigService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [FrontpageconfigUpdateComponent]
            })
                .overrideTemplate(FrontpageconfigUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FrontpageconfigUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FrontpageconfigService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Frontpageconfig(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.frontpageconfig = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Frontpageconfig();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.frontpageconfig = entity;
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
