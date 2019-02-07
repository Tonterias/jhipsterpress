/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterpressTestModule } from '../../../test.module';
import { AlbumUpdateComponent } from 'app/entities/album/album-update.component';
import { AlbumService } from 'app/entities/album/album.service';
import { Album } from 'app/shared/model/album.model';

describe('Component Tests', () => {
    describe('Album Management Update Component', () => {
        let comp: AlbumUpdateComponent;
        let fixture: ComponentFixture<AlbumUpdateComponent>;
        let service: AlbumService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterpressTestModule],
                declarations: [AlbumUpdateComponent]
            })
                .overrideTemplate(AlbumUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AlbumUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AlbumService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Album(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.album = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Album();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.album = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
