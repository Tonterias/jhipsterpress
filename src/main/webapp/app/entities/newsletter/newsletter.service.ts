import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INewsletter } from 'app/shared/model/newsletter.model';

type EntityResponseType = HttpResponse<INewsletter>;
type EntityArrayResponseType = HttpResponse<INewsletter[]>;

@Injectable({ providedIn: 'root' })
export class NewsletterService {
    public resourceUrl = SERVER_API_URL + 'api/newsletters';

    constructor(protected http: HttpClient) {}

    create(newsletter: INewsletter): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(newsletter);
        return this.http
            .post<INewsletter>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(newsletter: INewsletter): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(newsletter);
        return this.http
            .put<INewsletter>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<INewsletter>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<INewsletter[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(newsletter: INewsletter): INewsletter {
        const copy: INewsletter = Object.assign({}, newsletter, {
            creationDate: newsletter.creationDate != null && newsletter.creationDate.isValid() ? newsletter.creationDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((newsletter: INewsletter) => {
                newsletter.creationDate = newsletter.creationDate != null ? moment(newsletter.creationDate) : null;
            });
        }
        return res;
    }
}
