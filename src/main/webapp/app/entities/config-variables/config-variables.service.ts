import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConfigVariables } from 'app/shared/model/config-variables.model';

type EntityResponseType = HttpResponse<IConfigVariables>;
type EntityArrayResponseType = HttpResponse<IConfigVariables[]>;

@Injectable({ providedIn: 'root' })
export class ConfigVariablesService {
    public resourceUrl = SERVER_API_URL + 'api/config-variables';

    constructor(protected http: HttpClient) {}

    create(configVariables: IConfigVariables): Observable<EntityResponseType> {
        return this.http.post<IConfigVariables>(this.resourceUrl, configVariables, { observe: 'response' });
    }

    update(configVariables: IConfigVariables): Observable<EntityResponseType> {
        return this.http.put<IConfigVariables>(this.resourceUrl, configVariables, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IConfigVariables>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConfigVariables[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
