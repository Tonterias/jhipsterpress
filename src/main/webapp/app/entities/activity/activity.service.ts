import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IActivity } from 'app/shared/model/activity.model';

type EntityResponseType = HttpResponse<IActivity>;
type EntityArrayResponseType = HttpResponse<IActivity[]>;

@Injectable({ providedIn: 'root' })
export class ActivityService {
    public resourceUrl = SERVER_API_URL + 'api/activities';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/activities';

    constructor(protected http: HttpClient) {}

    create(activity: IActivity): Observable<EntityResponseType> {
        return this.http.post<IActivity>(this.resourceUrl, activity, { observe: 'response' });
    }

    update(activity: IActivity): Observable<EntityResponseType> {
        return this.http.put<IActivity>(this.resourceUrl, activity, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IActivity>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        //        console.log('CONSOLOG: M:ActivityService: & O: this.req : ', req);
        const options = createRequestOption(req);
        //        console.log('CONSOLOG: M:ActivityService: & O: this.options : ', options);
        return this.http.get<IActivity[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
