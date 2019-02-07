import { ICommunity } from 'app/shared/model/community.model';

export interface ICceleb {
    id?: number;
    celebName?: string;
    communities?: ICommunity[];
}

export class Cceleb implements ICceleb {
    constructor(public id?: number, public celebName?: string, public communities?: ICommunity[]) {}
}
