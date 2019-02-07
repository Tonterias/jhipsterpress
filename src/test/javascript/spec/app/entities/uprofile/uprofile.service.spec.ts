/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { UprofileService } from 'app/entities/uprofile/uprofile.service';
import {
    IUprofile,
    Uprofile,
    Gender,
    CivilStatus,
    Purpose,
    Physical,
    Religion,
    EthnicGroup,
    Studies,
    Eyes,
    Smoker,
    Children,
    FutureChildren
} from 'app/shared/model/uprofile.model';

describe('Service Tests', () => {
    describe('Uprofile Service', () => {
        let injector: TestBed;
        let service: UprofileService;
        let httpMock: HttpTestingController;
        let elemDefault: IUprofile;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(UprofileService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Uprofile(
                0,
                currentDate,
                'image/png',
                'AAAAAAA',
                Gender.MALE,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                CivilStatus.NA,
                Gender.MALE,
                Purpose.NOT_INTERESTED,
                Physical.NA,
                Religion.NA,
                EthnicGroup.NA,
                Studies.NA,
                0,
                Eyes.NA,
                Smoker.NA,
                Children.NA,
                FutureChildren.NA,
                false
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_TIME_FORMAT),
                        birthdate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Uprofile', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        creationDate: currentDate.format(DATE_TIME_FORMAT),
                        birthdate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creationDate: currentDate,
                        birthdate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Uprofile(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Uprofile', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_TIME_FORMAT),
                        image: 'BBBBBB',
                        gender: 'BBBBBB',
                        phone: 'BBBBBB',
                        bio: 'BBBBBB',
                        facebook: 'BBBBBB',
                        twitter: 'BBBBBB',
                        linkedin: 'BBBBBB',
                        instagram: 'BBBBBB',
                        googlePlus: 'BBBBBB',
                        birthdate: currentDate.format(DATE_TIME_FORMAT),
                        civilStatus: 'BBBBBB',
                        lookingFor: 'BBBBBB',
                        purpose: 'BBBBBB',
                        physical: 'BBBBBB',
                        religion: 'BBBBBB',
                        ethnicGroup: 'BBBBBB',
                        studies: 'BBBBBB',
                        sibblings: 1,
                        eyes: 'BBBBBB',
                        smoker: 'BBBBBB',
                        children: 'BBBBBB',
                        futureChildren: 'BBBBBB',
                        pet: true
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        creationDate: currentDate,
                        birthdate: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Uprofile', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_TIME_FORMAT),
                        image: 'BBBBBB',
                        gender: 'BBBBBB',
                        phone: 'BBBBBB',
                        bio: 'BBBBBB',
                        facebook: 'BBBBBB',
                        twitter: 'BBBBBB',
                        linkedin: 'BBBBBB',
                        instagram: 'BBBBBB',
                        googlePlus: 'BBBBBB',
                        birthdate: currentDate.format(DATE_TIME_FORMAT),
                        civilStatus: 'BBBBBB',
                        lookingFor: 'BBBBBB',
                        purpose: 'BBBBBB',
                        physical: 'BBBBBB',
                        religion: 'BBBBBB',
                        ethnicGroup: 'BBBBBB',
                        studies: 'BBBBBB',
                        sibblings: 1,
                        eyes: 'BBBBBB',
                        smoker: 'BBBBBB',
                        children: 'BBBBBB',
                        futureChildren: 'BBBBBB',
                        pet: true
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creationDate: currentDate,
                        birthdate: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Uprofile', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
