import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUprofile } from 'app/shared/model/uprofile.model';

type EntityResponseType = HttpResponse<IUprofile>;
type EntityArrayResponseType = HttpResponse<IUprofile[]>;

@Injectable({ providedIn: 'root' })
export class UprofileService {
    public resourceUrl = SERVER_API_URL + 'api/uprofiles';

    constructor(protected http: HttpClient) {}

    create(uprofile: IUprofile): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(uprofile);
        return this.http
            .post<IUprofile>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(uprofile: IUprofile): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(uprofile);
        return this.http
            .put<IUprofile>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IUprofile>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUprofile[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(uprofile: IUprofile): IUprofile {
        const copy: IUprofile = Object.assign({}, uprofile, {
            creationDate: uprofile.creationDate != null && uprofile.creationDate.isValid() ? uprofile.creationDate.toJSON() : null,
            birthdate: uprofile.birthdate != null && uprofile.birthdate.isValid() ? uprofile.birthdate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
            res.body.birthdate = res.body.birthdate != null ? moment(res.body.birthdate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((uprofile: IUprofile) => {
                uprofile.creationDate = uprofile.creationDate != null ? moment(uprofile.creationDate) : null;
                uprofile.birthdate = uprofile.birthdate != null ? moment(uprofile.birthdate) : null;
            });
        }
        return res;
    }
}
