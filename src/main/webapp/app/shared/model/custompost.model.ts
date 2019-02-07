import { Moment } from 'moment';
import { IComment } from 'app/shared/model//comment.model';
import { ITag } from 'app/shared/model//tag.model';
import { ITopic } from 'app/shared/model//topic.model';

export interface ICustomPost {
    id?: number;
    creationDate?: Moment;
    publicationDate?: Moment;
    headline?: string;
    leadtext?: string;
    bodytext?: string;
    quote?: string;
    conclusion?: string;
    imageContentType?: string;
    image?: any;
    comments?: IComment[];
    urllink?: any;
    blogTitle?: string;
    blog?: any;
    profile?: any;
    tags?: ITag[];
    topics?: ITopic[];
}

export class CustomPost implements ICustomPost {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public publicationDate?: Moment,
        public headline?: string,
        public leadtext?: string,
        public bodytext?: string,
        public quote?: string,
        public conclusion?: string,
        public imageContentType?: string,
        public image?: any,
        public comments?: IComment[],
        public urllink?: any,
        public blogTitle?: string,
        public blog?: any,
        public profile?: any,
        public tags?: ITag[],
        public topics?: ITopic[]
    ) {}
}
