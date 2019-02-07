/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { UprofileDetailComponent } from 'app/entities/uprofile/uprofile-detail.component';
import { Uprofile } from 'app/shared/model/uprofile.model';

describe('Component Tests', () => {
    describe('Uprofile Management Detail Component', () => {
        let comp: UprofileDetailComponent;
        let fixture: ComponentFixture<UprofileDetailComponent>;
        const route = ({ data: of({ uprofile: new Uprofile(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [UprofileDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UprofileDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UprofileDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.uprofile).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
