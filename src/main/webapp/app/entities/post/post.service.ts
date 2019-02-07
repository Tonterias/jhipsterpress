import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPost } from 'app/shared/model/post.model';

type EntityResponseType = HttpResponse<IPost>;
type EntityArrayResponseType = HttpResponse<IPost[]>;

@Injectable({ providedIn: 'root' })
export class PostService {
    public resourceUrl = SERVER_API_URL + 'api/posts';

    constructor(protected http: HttpClient) {}

    create(post: IPost): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(post);
        return this.http
            .post<IPost>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(post: IPost): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(post);
        return this.http
            .put<IPost>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IPost>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IPost[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(post: IPost): IPost {
        const copy: IPost = Object.assign({}, post, {
            creationDate: post.creationDate != null && post.creationDate.isValid() ? post.creationDate.toJSON() : null,
            publicationDate: post.publicationDate != null && post.publicationDate.isValid() ? post.publicationDate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.creationDate = res.body.creationDate != null ? moment(res.body.creationDate) : null;
            res.body.publicationDate = res.body.publicationDate != null ? moment(res.body.publicationDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((post: IPost) => {
                post.creationDate = post.creationDate != null ? moment(post.creationDate) : null;
                post.publicationDate = post.publicationDate != null ? moment(post.publicationDate) : null;
            });
        }
        return res;
    }
}
