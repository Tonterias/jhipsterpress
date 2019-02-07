/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { VthumbDetailComponent } from 'app/entities/vthumb/vthumb-detail.component';
import { Vthumb } from 'app/shared/model/vthumb.model';

describe('Component Tests', () => {
    describe('Vthumb Management Detail Component', () => {
        let comp: VthumbDetailComponent;
        let fixture: ComponentFixture<VthumbDetailComponent>;
        const route = ({ data: of({ vthumb: new Vthumb(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [VthumbDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VthumbDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VthumbDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.vthumb).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
