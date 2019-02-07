/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VthumbUpdateComponent } from 'app/entities/vthumb/vthumb-update.component';
import { VthumbService } from 'app/entities/vthumb/vthumb.service';
import { Vthumb } from 'app/shared/model/vthumb.model';

describe('Component Tests', () => {
    describe('Vthumb Management Update Component', () => {
        let comp: VthumbUpdateComponent;
        let fixture: ComponentFixture<VthumbUpdateComponent>;
        let service: VthumbService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VthumbUpdateComponent]
            })
                .overrideTemplate(VthumbUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VthumbUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VthumbService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vthumb(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vthumb = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Vthumb();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.vthumb = entity;
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
