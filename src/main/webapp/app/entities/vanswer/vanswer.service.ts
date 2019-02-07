import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVanswer } from 'app/shared/model/vanswer.model';

type EntityResponseType = HttpResponse<IVanswer>;
type EntityArrayResponseType = HttpResponse<IVanswer[]>;

@Injectable({ providedIn: 'root' })
export class VanswerService {
    public resourceUrl = SERVER_API_URL + 'api/vanswers';

    constructor(protected http: HttpClient) {}

    create(vanswer: IVanswer): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vanswer);
        return this.http
            .post<IVanswer>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(vanswer: IVanswer): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vanswer);
        return this.http
            .put<IVanswer>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVanswer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVanswer[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(vanswer: IVanswer): IVanswer {
        const copy: IVanswer = Object.assign({}, vanswer, {
            creationDate: vanswer.creationDate != null && vanswer.creationDate.isValid() ? vanswer.creationDate.toJSON() : null
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
            res.body.forEach((vanswer: IVanswer) => {
                vanswer.creationDate = vanswer.creationDate != null ? moment(vanswer.creationDate) : null;
            });
        }
        return res;
    }
}
