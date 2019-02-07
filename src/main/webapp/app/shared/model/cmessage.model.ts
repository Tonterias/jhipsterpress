import { Moment } from 'moment';

export interface ICmessage {
    id?: number;
    creationDate?: Moment;
    messageText?: string;
    isDelivered?: boolean;
    csenderId?: number;
    creceiverId?: number;
}

export class Cmessage implements ICmessage {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public messageText?: string,
        public isDelivered?: boolean,
        public csenderId?: number,
        public creceiverId?: number
    ) {
        this.isDelivered = this.isDelivered || false;
    }
}
