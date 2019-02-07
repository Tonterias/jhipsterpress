/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { FollowUpdateComponent } from 'app/entities/follow/follow-update.component';
import { FollowService } from 'app/entities/follow/follow.service';
import { Follow } from 'app/shared/model/follow.model';

describe('Component Tests', () => {
    describe('Follow Management Update Component', () => {
        let comp: FollowUpdateComponent;
        let fixture: ComponentFixture<FollowUpdateComponent>;
        let service: FollowService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [FollowUpdateComponent]
            })
                .overrideTemplate(FollowUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FollowUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FollowService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Follow(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.follow = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Follow();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.follow = entity;
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
