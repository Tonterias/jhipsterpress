import { IPost } from 'app/shared/model/post.model';

export interface ITopic {
    id?: number;
    topicName?: string;
    posts?: IPost[];
}

export class Topic implements ITopic {
    constructor(public id?: number, public topicName?: string, public posts?: IPost[]) {}
}
