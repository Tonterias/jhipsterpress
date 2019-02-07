/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { NewsletterUpdateComponent } from 'app/entities/newsletter/newsletter-update.component';
import { NewsletterService } from 'app/entities/newsletter/newsletter.service';
import { Newsletter } from 'app/shared/model/newsletter.model';

describe('Component Tests', () => {
    describe('Newsletter Management Update Component', () => {
        let comp: NewsletterUpdateComponent;
        let fixture: ComponentFixture<NewsletterUpdateComponent>;
        let service: NewsletterService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [NewsletterUpdateComponent]
            })
                .overrideTemplate(NewsletterUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NewsletterUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NewsletterService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Newsletter(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.newsletter = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Newsletter();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.newsletter = entity;
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
