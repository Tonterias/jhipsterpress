/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { InterestDetailComponent } from 'app/entities/interest/interest-detail.component';
import { Interest } from 'app/shared/model/interest.model';

describe('Component Tests', () => {
    describe('Interest Management Detail Component', () => {
        let comp: InterestDetailComponent;
        let fixture: ComponentFixture<InterestDetailComponent>;
        const route = ({ data: of({ interest: new Interest(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [InterestDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InterestDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InterestDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.interest).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
