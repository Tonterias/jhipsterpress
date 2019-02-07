import { Moment } from 'moment';
import { IVanswer } from 'app/shared/model/vanswer.model';
import { IVthumb } from 'app/shared/model/vthumb.model';

export interface IVquestion {
    id?: number;
    creationDate?: Moment;
    vquestion?: string;
    vquestionDescription?: string;
    vanswers?: IVanswer[];
    vthumbs?: IVthumb[];
    userId?: number;
    vtopicId?: number;
}

export class Vquestion implements IVquestion {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public vquestion?: string,
        public vquestionDescription?: string,
        public vanswers?: IVanswer[],
        public vthumbs?: IVthumb[],
        public userId?: number,
        public vtopicId?: number
    ) {}
}
