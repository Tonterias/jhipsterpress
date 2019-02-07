import { IUprofile } from 'app/shared/model/uprofile.model';

export interface IInterest {
    id?: number;
    interestName?: string;
    uprofiles?: IUprofile[];
}

export class Interest implements IInterest {
    constructor(public id?: number, public interestName?: string, public uprofiles?: IUprofile[]) {}
}
