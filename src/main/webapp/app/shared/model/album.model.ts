import { Moment } from 'moment';
import { IPhoto } from 'app/shared/model/photo.model';

export interface IAlbum {
    id?: number;
    creationDate?: Moment;
    title?: string;
    photos?: IPhoto[];
    userId?: number;
}

export class Album implements IAlbum {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public title?: string,
        public photos?: IPhoto[],
        public userId?: number
    ) {}
}
