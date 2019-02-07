/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VquestionDetailComponent } from 'app/entities/vquestion/vquestion-detail.component';
import { Vquestion } from 'app/shared/model/vquestion.model';

describe('Component Tests', () => {
    describe('Vquestion Management Detail Component', () => {
        let comp: VquestionDetailComponent;
        let fixture: ComponentFixture<VquestionDetailComponent>;
        const route = ({ data: of({ vquestion: new Vquestion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VquestionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VquestionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VquestionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vquestion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
