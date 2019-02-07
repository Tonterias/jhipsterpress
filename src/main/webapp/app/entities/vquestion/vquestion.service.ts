import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVquestion } from 'app/shared/model/vquestion.model';

type EntityResponseType = HttpResponse<IVquestion>;
type EntityArrayResponseType = HttpResponse<IVquestion[]>;

@Injectable({ providedIn: 'root' })
export class VquestionService {
    public resourceUrl = SERVER_API_URL + 'api/vquestions';

    constructor(protected http: HttpClient) {}

    create(vquestion: IVquestion): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vquestion);
        return this.http
            .post<IVquestion>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(vquestion: IVquestion): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vquestion);
        return this.http
            .put<IVquestion>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVquestion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVquestion[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(vquestion: IVquestion): IVquestion {
        const copy: IVquestion = Object.assign({}, vquestion, {
            creationDate: vquestion.creationDate != null && vquestion.creationDate.isValid() ? vquestion.creationDate.toJSON() : null
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
            res.body.forEach((vquestion: IVquestion) => {
                vquestion.creationDate = vquestion.creationDate != null ? moment(vquestion.creationDate) : null;
            });
        }
        return res;
    }
}
