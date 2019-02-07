/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VanswerDetailComponent } from 'app/entities/vanswer/vanswer-detail.component';
import { Vanswer } from 'app/shared/model/vanswer.model';

describe('Component Tests', () => {
    describe('Vanswer Management Detail Component', () => {
        let comp: VanswerDetailComponent;
        let fixture: ComponentFixture<VanswerDetailComponent>;
        const route = ({ data: of({ vanswer: new Vanswer(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VanswerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VanswerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VanswerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vanswer).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
