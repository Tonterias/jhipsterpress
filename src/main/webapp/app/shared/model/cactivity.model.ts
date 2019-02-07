import { ICommunity } from 'app/shared/model/community.model';

export interface ICactivity {
    id?: number;
    activityName?: string;
    communities?: ICommunity[];
}

export class Cactivity implements ICactivity {
    constructor(public id?: number, public activityName?: string, public communities?: ICommunity[]) {}
}
