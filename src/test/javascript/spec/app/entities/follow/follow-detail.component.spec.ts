/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { FollowDetailComponent } from 'app/entities/follow/follow-detail.component';
import { Follow } from 'app/shared/model/follow.model';

describe('Component Tests', () => {
    describe('Follow Management Detail Component', () => {
        let comp: FollowDetailComponent;
        let fixture: ComponentFixture<FollowDetailComponent>;
        const route = ({ data: of({ follow: new Follow(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [FollowDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FollowDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FollowDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.follow).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
