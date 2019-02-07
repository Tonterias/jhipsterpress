/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { BlockuserUpdateComponent } from 'app/entities/blockuser/blockuser-update.component';
import { BlockuserService } from 'app/entities/blockuser/blockuser.service';
import { Blockuser } from 'app/shared/model/blockuser.model';

describe('Component Tests', () => {
    describe('Blockuser Management Update Component', () => {
        let comp: BlockuserUpdateComponent;
        let fixture: ComponentFixture<BlockuserUpdateComponent>;
        let service: BlockuserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [BlockuserUpdateComponent]
            })
                .overrideTemplate(BlockuserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BlockuserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BlockuserService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Blockuser(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.blockuser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Blockuser();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.blockuser = entity;
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
