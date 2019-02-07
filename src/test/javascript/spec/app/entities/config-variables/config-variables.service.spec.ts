/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ConfigVariablesService } from 'app/entities/config-variables/config-variables.service';
import { IConfigVariables, ConfigVariables } from 'app/shared/model/config-variables.model';

describe('Service Tests', () => {
    describe('ConfigVariables Service', () => {
        let injector: TestBed;
        let service: ConfigVariablesService;
        let httpMock: HttpTestingController;
        let elemDefault: IConfigVariables;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ConfigVariablesService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new ConfigVariables(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, 'AAAAAAA', 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ConfigVariables', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new ConfigVariables(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ConfigVariables', async () => {
                const returnedFromService = Object.assign(
                    {
                        configVarLong1: 1,
                        configVarLong2: 1,
                        configVarLong3: 1,
                        configVarLong4: 1,
                        configVarLong5: 1,
                        configVarLong6: 1,
                        configVarLong7: 1,
                        configVarLong8: 1,
                        configVarLong9: 1,
                        configVarLong10: 1,
                        configVarLong11: 1,
                        configVarLong12: 1,
                        configVarLong13: 1,
                        configVarLong14: 1,
                        configVarLong15: 1,
                        configVarBoolean16: true,
                        configVarBoolean17: true,
                        configVarBoolean18: true,
                        configVarString19: 'BBBBBB',
                        configVarString20: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ConfigVariables', async () => {
                const returnedFromService = Object.assign(
                    {
                        configVarLong1: 1,
                        configVarLong2: 1,
                        configVarLong3: 1,
                        configVarLong4: 1,
                        configVarLong5: 1,
                        configVarLong6: 1,
                        configVarLong7: 1,
                        configVarLong8: 1,
                        configVarLong9: 1,
                        configVarLong10: 1,
                        configVarLong11: 1,
                        configVarLong12: 1,
                        configVarLong13: 1,
                        configVarLong14: 1,
                        configVarLong15: 1,
                        configVarBoolean16: true,
                        configVarBoolean17: true,
                        configVarBoolean18: true,
                        configVarString19: 'BBBBBB',
                        configVarString20: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a ConfigVariables', async () => {
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
