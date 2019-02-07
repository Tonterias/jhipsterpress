import { Moment } from 'moment';

export const enum NotificationReason {
    FOLLOWING = 'FOLLOWING',
    UNFOLLOWING = 'UNFOLLOWING',
    PROPOSAL_RELEASED = 'PROPOSAL_RELEASED',
    FOLLOWER_STATUS = 'FOLLOWER_STATUS',
    AUTHORIZE_COMMUNITY_FOLLOWER = 'AUTHORIZE_COMMUNITY_FOLLOWER',
    UNAUTHORIZE_COMMUNITY_FOLLOWER = 'UNAUTHORIZE_COMMUNITY_FOLLOWER'
}

export interface INotification {
    id?: number;
    creationDate?: Moment;
    notificationDate?: Moment;
    notificationReason?: NotificationReason;
    notificationText?: string;
    isDelivered?: boolean;
    userId?: number;
}

export class Notification implements INotification {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public notificationDate?: Moment,
        public notificationReason?: NotificationReason,
        public notificationText?: string,
        public isDelivered?: boolean,
        public userId?: number
    ) {
        this.isDelivered = this.isDelivered || false;
    }
}
