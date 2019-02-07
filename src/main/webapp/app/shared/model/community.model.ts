import { Moment } from 'moment';
import { IBlog } from 'app/shared/model/blog.model';
import { ICmessage } from 'app/shared/model/cmessage.model';
import { IFollow } from 'app/shared/model/follow.model';
import { IBlockuser } from 'app/shared/model/blockuser.model';
import { ICalbum } from 'app/shared/model/calbum.model';
import { ICinterest } from 'app/shared/model/cinterest.model';
import { ICactivity } from 'app/shared/model/cactivity.model';
import { ICceleb } from 'app/shared/model/cceleb.model';

export interface ICommunity {
    id?: number;
    creationDate?: Moment;
    communityName?: string;
    communityDescription?: string;
    imageContentType?: string;
    image?: any;
    isActive?: boolean;
    blogs?: IBlog[];
    csenders?: ICmessage[];
    creceivers?: ICmessage[];
    cfolloweds?: IFollow[];
    cfollowings?: IFollow[];
    cblockedusers?: IBlockuser[];
    cblockingusers?: IBlockuser[];
    userId?: number;
    calbums?: ICalbum[];
    cinterests?: ICinterest[];
    cactivities?: ICactivity[];
    ccelebs?: ICceleb[];
}

export class Community implements ICommunity {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public communityName?: string,
        public communityDescription?: string,
        public imageContentType?: string,
        public image?: any,
        public isActive?: boolean,
        public blogs?: IBlog[],
        public csenders?: ICmessage[],
        public creceivers?: ICmessage[],
        public cfolloweds?: IFollow[],
        public cfollowings?: IFollow[],
        public cblockedusers?: IBlockuser[],
        public cblockingusers?: IBlockuser[],
        public userId?: number,
        public calbums?: ICalbum[],
        public cinterests?: ICinterest[],
        public cactivities?: ICactivity[],
        public ccelebs?: ICceleb[]
    ) {
        this.isActive = this.isActive || false;
    }
}
