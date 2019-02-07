import { Moment } from 'moment';

export interface IMessage {
    id?: number;
    creationDate?: Moment;
    messageText?: string;
    isDelivered?: boolean;
    senderId?: number;
    receiverId?: number;
}

export class Message implements IMessage {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public messageText?: string,
        public isDelivered?: boolean,
        public senderId?: number,
        public receiverId?: number
    ) {
        this.isDelivered = this.isDelivered || false;
    }
}
