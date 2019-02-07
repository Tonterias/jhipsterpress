import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICalbum } from 'app/shared/model/calbum.model';

type EntityResponseType = HttpResponse<ICalbum>;
type EntityArrayResponseType = HttpResponse<ICalbum[]>;

@Injectable({ providedIn: 'root' })
export class CalbumService {
    public resourceUrl = SERVER_API_URL + 'api/calbums';

    constructor(protected http: HttpClient) {}

    create(calbum: ICalbum): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(calbum);
        return this.http
            .post<ICalbum>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(calbum: ICalbum): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(calbum);
        return this.http
            .put<ICalbum>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICalbum>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICalbum[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(calbum: ICalbum): ICalbum {
        const copy: ICalbum = Object.assign({}, calbum, {
            creationDate: calbum.creationDate != null && calbum.creationDate.isValid() ? calbum.creationDate.toJSON() : null
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
            res.body.forEach((calbum: ICalbum) => {
                calbum.creationDate = calbum.creationDate != null ? moment(calbum.creationDate) : null;
            });
        }
        return res;
    }
}
