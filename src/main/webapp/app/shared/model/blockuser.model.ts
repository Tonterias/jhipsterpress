import { Moment } from 'moment';

export interface IBlockuser {
    id?: number;
    creationDate?: Moment;
    blockeduserId?: number;
    blockinguserId?: number;
    cblockeduserId?: number;
    cblockinguserId?: number;
}

export class Blockuser implements IBlockuser {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public blockeduserId?: number,
        public blockinguserId?: number,
        public cblockeduserId?: number,
        public cblockinguserId?: number
    ) {}
}
