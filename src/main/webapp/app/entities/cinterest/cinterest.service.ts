import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICinterest } from 'app/shared/model/cinterest.model';

type EntityResponseType = HttpResponse<ICinterest>;
type EntityArrayResponseType = HttpResponse<ICinterest[]>;

@Injectable({ providedIn: 'root' })
export class CinterestService {
    public resourceUrl = SERVER_API_URL + 'api/cinterests';

    constructor(protected http: HttpClient) {}

    create(cinterest: ICinterest): Observable<EntityResponseType> {
        return this.http.post<ICinterest>(this.resourceUrl, cinterest, { observe: 'response' });
    }

    update(cinterest: ICinterest): Observable<EntityResponseType> {
        return this.http.put<ICinterest>(this.resourceUrl, cinterest, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICinterest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICinterest[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
