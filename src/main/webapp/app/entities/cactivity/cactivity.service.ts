import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICactivity } from 'app/shared/model/cactivity.model';

type EntityResponseType = HttpResponse<ICactivity>;
type EntityArrayResponseType = HttpResponse<ICactivity[]>;

@Injectable({ providedIn: 'root' })
export class CactivityService {
    public resourceUrl = SERVER_API_URL + 'api/cactivities';

    constructor(protected http: HttpClient) {}

    create(cactivity: ICactivity): Observable<EntityResponseType> {
        return this.http.post<ICactivity>(this.resourceUrl, cactivity, { observe: 'response' });
    }

    update(cactivity: ICactivity): Observable<EntityResponseType> {
        return this.http.put<ICactivity>(this.resourceUrl, cactivity, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICactivity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICactivity[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
