/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { NewsletterDetailComponent } from 'app/entities/newsletter/newsletter-detail.component';
import { Newsletter } from 'app/shared/model/newsletter.model';

describe('Component Tests', () => {
    describe('Newsletter Management Detail Component', () => {
        let comp: NewsletterDetailComponent;
        let fixture: ComponentFixture<NewsletterDetailComponent>;
        const route = ({ data: of({ newsletter: new Newsletter(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [NewsletterDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NewsletterDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NewsletterDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.newsletter).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
