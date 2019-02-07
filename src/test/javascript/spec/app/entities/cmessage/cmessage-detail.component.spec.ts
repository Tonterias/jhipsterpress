/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CmessageDetailComponent } from 'app/entities/cmessage/cmessage-detail.component';
import { Cmessage } from 'app/shared/model/cmessage.model';

describe('Component Tests', () => {
    describe('Cmessage Management Detail Component', () => {
        let comp: CmessageDetailComponent;
        let fixture: ComponentFixture<CmessageDetailComponent>;
        const route = ({ data: of({ cmessage: new Cmessage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CmessageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CmessageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CmessageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cmessage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
