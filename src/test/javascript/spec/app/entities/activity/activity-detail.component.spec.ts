/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { ActivityDetailComponent } from 'app/entities/activity/activity-detail.component';
import { Activity } from 'app/shared/model/activity.model';

describe('Component Tests', () => {
    describe('Activity Management Detail Component', () => {
        let comp: ActivityDetailComponent;
        let fixture: ComponentFixture<ActivityDetailComponent>;
        const route = ({ data: of({ activity: new Activity(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [ActivityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ActivityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ActivityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.activity).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
