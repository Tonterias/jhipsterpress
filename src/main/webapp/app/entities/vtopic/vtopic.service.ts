import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVtopic } from 'app/shared/model/vtopic.model';

type EntityResponseType = HttpResponse<IVtopic>;
type EntityArrayResponseType = HttpResponse<IVtopic[]>;

@Injectable({ providedIn: 'root' })
export class VtopicService {
    public resourceUrl = SERVER_API_URL + 'api/vtopics';

    constructor(protected http: HttpClient) {}

    create(vtopic: IVtopic): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vtopic);
        return this.http
            .post<IVtopic>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(vtopic: IVtopic): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vtopic);
        return this.http
            .put<IVtopic>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVtopic>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVtopic[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(vtopic: IVtopic): IVtopic {
        const copy: IVtopic = Object.assign({}, vtopic, {
            creationDate: vtopic.creationDate != null && vtopic.creationDate.isValid() ? vtopic.creationDate.toJSON() : null
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
            res.body.forEach((vtopic: IVtopic) => {
                vtopic.creationDate = vtopic.creationDate != null ? moment(vtopic.creationDate) : null;
            });
        }
        return res;
    }
}
