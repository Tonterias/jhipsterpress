/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FrontpageconfigService } from 'app/entities/frontpageconfig/frontpageconfig.service';
import { IFrontpageconfig, Frontpageconfig } from 'app/shared/model/frontpageconfig.model';

describe('Service Tests', () => {
    describe('Frontpageconfig Service', () => {
        let injector: TestBed;
        let service: FrontpageconfigService;
        let httpMock: HttpTestingController;
        let elemDefault: IFrontpageconfig;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(FrontpageconfigService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Frontpageconfig(
                0,
                currentDate,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a Frontpageconfig', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        creationDate: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creationDate: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Frontpageconfig(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Frontpageconfig', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_TIME_FORMAT),
                        topNews1: 1,
                        topNews2: 1,
                        topNews3: 1,
                        topNews4: 1,
                        topNews5: 1,
                        latestNews1: 1,
                        latestNews2: 1,
                        latestNews3: 1,
                        latestNews4: 1,
                        latestNews5: 1,
                        breakingNews1: 1,
                        recentPosts1: 1,
                        recentPosts2: 1,
                        recentPosts3: 1,
                        recentPosts4: 1,
                        featuredArticles1: 1,
                        featuredArticles2: 1,
                        featuredArticles3: 1,
                        featuredArticles4: 1,
                        featuredArticles5: 1,
                        featuredArticles6: 1,
                        featuredArticles7: 1,
                        featuredArticles8: 1,
                        featuredArticles9: 1,
                        featuredArticles10: 1,
                        popularNews1: 1,
                        popularNews2: 1,
                        popularNews3: 1,
                        popularNews4: 1,
                        popularNews5: 1,
                        popularNews6: 1,
                        popularNews7: 1,
                        popularNews8: 1,
                        weeklyNews1: 1,
                        weeklyNews2: 1,
                        weeklyNews3: 1,
                        weeklyNews4: 1,
                        newsFeeds1: 1,
                        newsFeeds2: 1,
                        newsFeeds3: 1,
                        newsFeeds4: 1,
                        newsFeeds5: 1,
                        newsFeeds6: 1,
                        usefulLinks1: 1,
                        usefulLinks2: 1,
                        usefulLinks3: 1,
                        usefulLinks4: 1,
                        usefulLinks5: 1,
                        usefulLinks6: 1,
                        recentVideos1: 1,
                        recentVideos2: 1,
                        recentVideos3: 1,
                        recentVideos4: 1,
                        recentVideos5: 1,
                        recentVideos6: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        creationDate: currentDate
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

            it('should return a list of Frontpageconfig', async () => {
                const returnedFromService = Object.assign(
                    {
                        creationDate: currentDate.format(DATE_TIME_FORMAT),
                        topNews1: 1,
                        topNews2: 1,
                        topNews3: 1,
                        topNews4: 1,
                        topNews5: 1,
                        latestNews1: 1,
                        latestNews2: 1,
                        latestNews3: 1,
                        latestNews4: 1,
                        latestNews5: 1,
                        breakingNews1: 1,
                        recentPosts1: 1,
                        recentPosts2: 1,
                        recentPosts3: 1,
                        recentPosts4: 1,
                        featuredArticles1: 1,
                        featuredArticles2: 1,
                        featuredArticles3: 1,
                        featuredArticles4: 1,
                        featuredArticles5: 1,
                        featuredArticles6: 1,
                        featuredArticles7: 1,
                        featuredArticles8: 1,
                        featuredArticles9: 1,
                        featuredArticles10: 1,
                        popularNews1: 1,
                        popularNews2: 1,
                        popularNews3: 1,
                        popularNews4: 1,
                        popularNews5: 1,
                        popularNews6: 1,
                        popularNews7: 1,
                        popularNews8: 1,
                        weeklyNews1: 1,
                        weeklyNews2: 1,
                        weeklyNews3: 1,
                        weeklyNews4: 1,
                        newsFeeds1: 1,
                        newsFeeds2: 1,
                        newsFeeds3: 1,
                        newsFeeds4: 1,
                        newsFeeds5: 1,
                        newsFeeds6: 1,
                        usefulLinks1: 1,
                        usefulLinks2: 1,
                        usefulLinks3: 1,
                        usefulLinks4: 1,
                        usefulLinks5: 1,
                        usefulLinks6: 1,
                        recentVideos1: 1,
                        recentVideos2: 1,
                        recentVideos3: 1,
                        recentVideos4: 1,
                        recentVideos5: 1,
                        recentVideos6: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        creationDate: currentDate
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

            it('should delete a Frontpageconfig', async () => {
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
