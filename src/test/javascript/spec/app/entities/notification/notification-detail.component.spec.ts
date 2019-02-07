/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { NotificationDetailComponent } from 'app/entities/notification/notification-detail.component';
import { Notification } from 'app/shared/model/notification.model';

describe('Component Tests', () => {
    describe('Notification Management Detail Component', () => {
        let comp: NotificationDetailComponent;
        let fixture: ComponentFixture<NotificationDetailComponent>;
        const route = ({ data: of({ notification: new Notification(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [NotificationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NotificationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NotificationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.notification).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
