import { Moment } from 'moment';

export interface IPhoto {
    id?: number;
    creationDate?: Moment;
    imageContentType?: string;
    image?: any;
    albumTitle?: string;
    albumId?: number;
    calbumTitle?: string;
    calbumId?: number;
}

export class Photo implements IPhoto {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public imageContentType?: string,
        public image?: any,
        public albumTitle?: string,
        public albumId?: number,
        public calbumTitle?: string,
        public calbumId?: number
    ) {}
}
