/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { TopicUpdateComponent } from 'app/entities/topic/topic-update.component';
import { TopicService } from 'app/entities/topic/topic.service';
import { Topic } from 'app/shared/model/topic.model';

describe('Component Tests', () => {
    describe('Topic Management Update Component', () => {
        let comp: TopicUpdateComponent;
        let fixture: ComponentFixture<TopicUpdateComponent>;
        let service: TopicService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [TopicUpdateComponent]
            })
                .overrideTemplate(TopicUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TopicUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TopicService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Topic(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.topic = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Topic();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.topic = entity;
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
