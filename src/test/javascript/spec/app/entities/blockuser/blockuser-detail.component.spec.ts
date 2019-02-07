/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { BlockuserDetailComponent } from 'app/entities/blockuser/blockuser-detail.component';
import { Blockuser } from 'app/shared/model/blockuser.model';

describe('Component Tests', () => {
    describe('Blockuser Management Detail Component', () => {
        let comp: BlockuserDetailComponent;
        let fixture: ComponentFixture<BlockuserDetailComponent>;
        const route = ({ data: of({ blockuser: new Blockuser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [BlockuserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BlockuserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BlockuserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.blockuser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
