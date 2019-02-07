import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVthumb } from 'app/shared/model/vthumb.model';

type EntityResponseType = HttpResponse<IVthumb>;
type EntityArrayResponseType = HttpResponse<IVthumb[]>;

@Injectable({ providedIn: 'root' })
export class VthumbService {
    public resourceUrl = SERVER_API_URL + 'api/vthumbs';

    constructor(protected http: HttpClient) {}

    create(vthumb: IVthumb): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vthumb);
        return this.http
            .post<IVthumb>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(vthumb: IVthumb): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(vthumb);
        return this.http
            .put<IVthumb>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IVthumb>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IVthumb[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(vthumb: IVthumb): IVthumb {
        const copy: IVthumb = Object.assign({}, vthumb, {
            creationDate: vthumb.creationDate != null && vthumb.creationDate.isValid() ? vthumb.creationDate.toJSON() : null
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
            res.body.forEach((vthumb: IVthumb) => {
                vthumb.creationDate = vthumb.creationDate != null ? moment(vthumb.creationDate) : null;
            });
        }
        return res;
    }
}
