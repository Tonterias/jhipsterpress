import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFrontpageconfig } from 'app/shared/model/frontpageconfig.model';
import { ICustomFrontpageconfig } from 'app/shared/model/customfrontpageconfig.model';

type EntityResponseType = HttpResponse<IFrontpageconfig>;
type EntityArrayResponseType = HttpResponse<IFrontpageconfig[]>;
type CustomEntityResponseType = HttpResponse<ICustomFrontpageconfig>;
type CustomEntityArrayResponseType = HttpResponse<ICustomFrontpageconfig[]>;

@Injectable({ providedIn: 'root' })
export class FrontpageconfigService {
    public resourceUrl = SERVER_API_URL + 'api/frontpageconfigs';

    constructor(protected http: HttpClient) {}

    create(frontpageconfig: IFrontpageconfig): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(frontpageconfig);
        return this.http
            .post<IFrontpageconfig>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(frontpageconfig: IFrontpageconfig): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(frontpageconfig);
        return this.http
            .put<IFrontpageconfig>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFrontpageconfig>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    findIncludingPosts(id: number): Observable<CustomEntityResponseType> {
        return this.http
            .get<ICustomFrontpageconfig>(`${this.resourceUrl}/${id}/posts`, { observe: 'response' })
            .pipe(map((res: CustomEntityResponseType) => this.customConvertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFrontpageconfig[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(frontpageconfig: IFrontpageconfig): IFrontpageconfig {
        const copy: IFrontpageconfig = Object.assign({}, frontpageconfig, {
            creationDate:
                frontpageconfig.creationDate != null && frontpageconfig.creationDate.isValid()
                    ? frontpageconfig.creationDate.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
        }
        return res;
    }

    private customConvertDateFromServer(res: CustomEntityResponseType): CustomEntityResponseType {
        res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((frontpageconfig: IFrontpageconfig) => {
                frontpageconfig.creationDate = frontpageconfig.creationDate != null ? moment(frontpageconfig.creationDate) : null;
            });
        }
        return res;
    }
}
