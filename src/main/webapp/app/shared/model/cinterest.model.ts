import { ICommunity } from 'app/shared/model/community.model';

export interface ICinterest {
    id?: number;
    interestName?: string;
    communities?: ICommunity[];
}

export class Cinterest implements ICinterest {
    constructor(public id?: number, public interestName?: string, public communities?: ICommunity[]) {}
}
