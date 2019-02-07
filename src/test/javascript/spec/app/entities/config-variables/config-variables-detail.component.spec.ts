/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { ConfigVariablesDetailComponent } from 'app/entities/config-variables/config-variables-detail.component';
import { ConfigVariables } from 'app/shared/model/config-variables.model';

describe('Component Tests', () => {
    describe('ConfigVariables Management Detail Component', () => {
        let comp: ConfigVariablesDetailComponent;
        let fixture: ComponentFixture<ConfigVariablesDetailComponent>;
        const route = ({ data: of({ configVariables: new ConfigVariables(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [ConfigVariablesDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConfigVariablesDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConfigVariablesDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.configVariables).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
