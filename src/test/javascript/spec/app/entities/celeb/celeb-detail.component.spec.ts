/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CelebDetailComponent } from 'app/entities/celeb/celeb-detail.component';
import { Celeb } from 'app/shared/model/celeb.model';

describe('Component Tests', () => {
    describe('Celeb Management Detail Component', () => {
        let comp: CelebDetailComponent;
        let fixture: ComponentFixture<CelebDetailComponent>;
        const route = ({ data: of({ celeb: new Celeb(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CelebDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CelebDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CelebDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.celeb).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
