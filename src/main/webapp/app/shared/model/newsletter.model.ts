import { Moment } from 'moment';

export interface INewsletter {
    id?: number;
    creationDate?: Moment;
    email?: string;
}

export class Newsletter implements INewsletter {
    constructor(public id?: number, public creationDate?: Moment, public email?: string) {}
}
