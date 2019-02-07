/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CommunityUpdateComponent } from 'app/entities/community/community-update.component';
import { CommunityService } from 'app/entities/community/community.service';
import { Community } from 'app/shared/model/community.model';

describe('Component Tests', () => {
    describe('Community Management Update Component', () => {
        let comp: CommunityUpdateComponent;
        let fixture: ComponentFixture<CommunityUpdateComponent>;
        let service: CommunityService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CommunityUpdateComponent]
            })
                .overrideTemplate(CommunityUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CommunityUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CommunityService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Community(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.community = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Community();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.community = entity;
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
