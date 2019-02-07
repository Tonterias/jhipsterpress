import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICeleb } from 'app/shared/model/celeb.model';

type EntityResponseType = HttpResponse<ICeleb>;
type EntityArrayResponseType = HttpResponse<ICeleb[]>;

@Injectable({ providedIn: 'root' })
export class CelebService {
    public resourceUrl = SERVER_API_URL + 'api/celebs';

    constructor(protected http: HttpClient) {}

    create(celeb: ICeleb): Observable<EntityResponseType> {
        return this.http.post<ICeleb>(this.resourceUrl, celeb, { observe: 'response' });
    }

    update(celeb: ICeleb): Observable<EntityResponseType> {
        return this.http.put<ICeleb>(this.resourceUrl, celeb, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICeleb>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICeleb[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
