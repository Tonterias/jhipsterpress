/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VtopicDetailComponent } from 'app/entities/vtopic/vtopic-detail.component';
import { Vtopic } from 'app/shared/model/vtopic.model';

describe('Component Tests', () => {
    describe('Vtopic Management Detail Component', () => {
        let comp: VtopicDetailComponent;
        let fixture: ComponentFixture<VtopicDetailComponent>;
        const route = ({ data: of({ vtopic: new Vtopic(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VtopicDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VtopicDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VtopicDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vtopic).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
