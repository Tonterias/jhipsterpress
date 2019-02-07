import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICceleb } from 'app/shared/model/cceleb.model';

type EntityResponseType = HttpResponse<ICceleb>;
type EntityArrayResponseType = HttpResponse<ICceleb[]>;

@Injectable({ providedIn: 'root' })
export class CcelebService {
    public resourceUrl = SERVER_API_URL + 'api/ccelebs';

    constructor(protected http: HttpClient) {}

    create(cceleb: ICceleb): Observable<EntityResponseType> {
        return this.http.post<ICceleb>(this.resourceUrl, cceleb, { observe: 'response' });
    }

    update(cceleb: ICceleb): Observable<EntityResponseType> {
        return this.http.put<ICceleb>(this.resourceUrl, cceleb, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICceleb>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICceleb[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
