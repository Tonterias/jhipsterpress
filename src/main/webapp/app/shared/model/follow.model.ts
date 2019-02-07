import { Moment } from 'moment';

export interface IFollow {
    id?: number;
    creationDate?: Moment;
    followedId?: number;
    followingId?: number;
    cfollowedId?: number;
    cfollowingId?: number;
}

export class Follow implements IFollow {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public followedId?: number,
        public followingId?: number,
        public cfollowedId?: number,
        public cfollowingId?: number
    ) {}
}
