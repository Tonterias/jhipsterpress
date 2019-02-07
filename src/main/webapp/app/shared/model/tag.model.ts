import { IPost } from 'app/shared/model/post.model';

export interface ITag {
    id?: number;
    tagName?: string;
    posts?: IPost[];
}

export class Tag implements ITag {
    constructor(public id?: number, public tagName?: string, public posts?: IPost[]) {}
}
