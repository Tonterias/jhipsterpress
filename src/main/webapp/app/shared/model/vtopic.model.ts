import { Moment } from 'moment';
import { IVquestion } from 'app/shared/model/vquestion.model';

export interface IVtopic {
    id?: number;
    creationDate?: Moment;
    vtopicTitle?: string;
    vtopicDescription?: string;
    vquestions?: IVquestion[];
    userId?: number;
}

export class Vtopic implements IVtopic {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public vtopicTitle?: string,
        public vtopicDescription?: string,
        public vquestions?: IVquestion[],
        public userId?: number
    ) {}
}
