/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { CommunityDetailComponent } from 'app/entities/community/community-detail.component';
import { Community } from 'app/shared/model/community.model';

describe('Component Tests', () => {
    describe('Community Management Detail Component', () => {
        let comp: CommunityDetailComponent;
        let fixture: ComponentFixture<CommunityDetailComponent>;
        const route = ({ data: of({ community: new Community(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [CommunityDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CommunityDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CommunityDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.community).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
