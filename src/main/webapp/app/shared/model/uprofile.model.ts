import { Moment } from 'moment';
import { IInterest } from 'app/shared/model/interest.model';
import { IActivity } from 'app/shared/model/activity.model';
import { ICeleb } from 'app/shared/model/celeb.model';

export const enum Gender {
    MALE = 'MALE',
    FEMALE = 'FEMALE',
    OTHER = 'OTHER'
}

export const enum CivilStatus {
    NA = 'NA',
    SINGLE = 'SINGLE',
    MARRIED = 'MARRIED',
    DIVORCED = 'DIVORCED',
    WIDOWER = 'WIDOWER',
    SEPARATED = 'SEPARATED',
    ENGAGED = 'ENGAGED',
    OTHER = 'OTHER'
}

export const enum Purpose {
    NOT_INTERESTED = 'NOT_INTERESTED',
    FRIENDSHIP = 'FRIENDSHIP',
    MEET_PEOPLE = 'MEET_PEOPLE',
    STABLE_RELATIONSHIP = 'STABLE_RELATIONSHIP',
    MARRIAGE = 'MARRIAGE',
    OTHER = 'OTHER'
}

export const enum Physical {
    NA = 'NA',
    THIN = 'THIN',
    ATHLETIC = 'ATHLETIC',
    NORMAL = 'NORMAL',
    CORPULENT = 'CORPULENT',
    BULKY = 'BULKY',
    OTHER = 'OTHER'
}

export const enum Religion {
    NA = 'NA',
    ATHEIST = 'ATHEIST',
    AGNOSTIC = 'AGNOSTIC',
    CATHOLIC = 'CATHOLIC',
    JEWISH = 'JEWISH',
    ISLAMIC = 'ISLAMIC',
    OTHER = 'OTHER'
}

export const enum EthnicGroup {
    NA = 'NA',
    MIXED = 'MIXED',
    WHITE = 'WHITE',
    LATIN = 'LATIN',
    GYPSY = 'GYPSY',
    AFRO = 'AFRO',
    HINDU = 'HINDU',
    ARAB = 'ARAB',
    ASIAN = 'ASIAN',
    INDIAN = 'INDIAN',
    OTHER = 'OTHER'
}

export const enum Studies {
    NA = 'NA',
    PRIMARY = 'PRIMARY',
    HIGH_SCHOOL = 'HIGH_SCHOOL',
    TECHNICAL = 'TECHNICAL',
    COLLEGE = 'COLLEGE',
    MASTER = 'MASTER',
    DOCTORATE = 'DOCTORATE',
    OTHER = 'OTHER'
}

export const enum Eyes {
    NA = 'NA',
    BLUE = 'BLUE',
    GREEN = 'GREEN',
    BROWN = 'BROWN',
    BLACK = 'BLACK',
    CHESTNUT = 'CHESTNUT',
    OTHER = 'OTHER'
}

export const enum Smoker {
    NA = 'NA',
    YES = 'YES',
    NO = 'NO',
    OCCASIONALLY = 'OCCASIONALLY'
}

export const enum Children {
    NA = 'NA',
    YES = 'YES',
    NO = 'NO',
    GREATER_THAN_18 = 'GREATER_THAN_18'
}

export const enum FutureChildren {
    NA = 'NA',
    YES = 'YES',
    NO = 'NO'
}

export interface IUprofile {
    id?: number;
    creationDate?: Moment;
    imageContentType?: string;
    image?: any;
    gender?: Gender;
    phone?: string;
    bio?: string;
    facebook?: string;
    twitter?: string;
    linkedin?: string;
    instagram?: string;
    googlePlus?: string;
    birthdate?: Moment;
    civilStatus?: CivilStatus;
    lookingFor?: Gender;
    purpose?: Purpose;
    physical?: Physical;
    religion?: Religion;
    ethnicGroup?: EthnicGroup;
    studies?: Studies;
    sibblings?: number;
    eyes?: Eyes;
    smoker?: Smoker;
    children?: Children;
    futureChildren?: FutureChildren;
    pet?: boolean;
    userId?: number;
    interests?: IInterest[];
    activities?: IActivity[];
    celebs?: ICeleb[];
}

export class Uprofile implements IUprofile {
    constructor(
        public id?: number,
        public creationDate?: Moment,
        public imageContentType?: string,
        public image?: any,
        public gender?: Gender,
        public phone?: string,
        public bio?: string,
        public facebook?: string,
        public twitter?: string,
        public linkedin?: string,
        public instagram?: string,
        public googlePlus?: string,
        public birthdate?: Moment,
        public civilStatus?: CivilStatus,
        public lookingFor?: Gender,
        public purpose?: Purpose,
        public physical?: Physical,
        public religion?: Religion,
        public ethnicGroup?: EthnicGroup,
        public studies?: Studies,
        public sibblings?: number,
        public eyes?: Eyes,
        public smoker?: Smoker,
        public children?: Children,
        public futureChildren?: FutureChildren,
        public pet?: boolean,
        public userId?: number,
        public interests?: IInterest[],
        public activities?: IActivity[],
        public celebs?: ICeleb[]
    ) {
        this.pet = this.pet || false;
    }
}
