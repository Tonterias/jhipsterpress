import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInterest } from 'app/shared/model/interest.model';

type EntityResponseType = HttpResponse<IInterest>;
type EntityArrayResponseType = HttpResponse<IInterest[]>;

@Injectable({ providedIn: 'root' })
export class InterestService {
    public resourceUrl = SERVER_API_URL + 'api/interests';

    constructor(protected http: HttpClient) {}

    create(interest: IInterest): Observable<EntityResponseType> {
        return this.http.post<IInterest>(this.resourceUrl, interest, { observe: 'response' });
    }

    update(interest: IInterest): Observable<EntityResponseType> {
        return this.http.put<IInterest>(this.resourceUrl, interest, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IInterest>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IInterest[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
