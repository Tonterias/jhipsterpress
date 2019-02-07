/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { FrontpageconfigDetailComponent } from 'app/entities/frontpageconfig/frontpageconfig-detail.component';
import { Frontpageconfig } from 'app/shared/model/frontpageconfig.model';

describe('Component Tests', () => {
    describe('Frontpageconfig Management Detail Component', () => {
        let comp: FrontpageconfigDetailComponent;
        let fixture: ComponentFixture<FrontpageconfigDetailComponent>;
        const route = ({ data: of({ frontpageconfig: new Frontpageconfig(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [FrontpageconfigDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FrontpageconfigDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FrontpageconfigDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.frontpageconfig).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
