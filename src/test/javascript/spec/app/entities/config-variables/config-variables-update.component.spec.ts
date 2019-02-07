/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { ConfigVariablesUpdateComponent } from 'app/entities/config-variables/config-variables-update.component';
import { ConfigVariablesService } from 'app/entities/config-variables/config-variables.service';
import { ConfigVariables } from 'app/shared/model/config-variables.model';

describe('Component Tests', () => {
    describe('ConfigVariables Management Update Component', () => {
        let comp: ConfigVariablesUpdateComponent;
        let fixture: ComponentFixture<ConfigVariablesUpdateComponent>;
        let service: ConfigVariablesService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [ConfigVariablesUpdateComponent]
            })
                .overrideTemplate(ConfigVariablesUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConfigVariablesUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfigVariablesService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ConfigVariables(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.configVariables = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ConfigVariables();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.configVariables = entity;
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
