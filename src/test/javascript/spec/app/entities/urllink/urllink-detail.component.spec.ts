/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { UrllinkDetailComponent } from 'app/entities/urllink/urllink-detail.component';
import { Urllink } from 'app/shared/model/urllink.model';

describe('Component Tests', () => {
    describe('Urllink Management Detail Component', () => {
        let comp: UrllinkDetailComponent;
        let fixture: ComponentFixture<UrllinkDetailComponent>;
        const route = ({ data: of({ urllink: new Urllink(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [UrllinkDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UrllinkDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UrllinkDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.urllink).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
