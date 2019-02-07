import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICommunity } from 'app/shared/model/community.model';

type EntityResponseType = HttpResponse<ICommunity>;
type EntityArrayResponseType = HttpResponse<ICommunity[]>;

@Injectable({ providedIn: 'root' })
export class CommunityService {
    public resourceUrl = SERVER_API_URL + 'api/communities';

    constructor(protected http: HttpClient) {}

    create(community: ICommunity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(community);
        return this.http
            .post<ICommunity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(community: ICommunity): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(community);
        return this.http
            .put<ICommunity>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICommunity>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICommunity[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(community: ICommunity): ICommunity {
        const copy: ICommunity = Object.assign({}, community, {
            creationDate: community.creationDate != null && community.creationDate.isValid() ? community.creationDate.toJSON() : null
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
            res.body.forEach((community: ICommunity) => {
                community.creationDate = community.creationDate != null ? moment(community.creationDate) : null;
            });
        }
        return res;
    }
}
