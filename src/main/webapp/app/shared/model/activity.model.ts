import { IUprofile } from 'app/shared/model/uprofile.model';

export interface IActivity {
    id?: number;
    activityName?: string;
    uprofiles?: IUprofile[];
}

export class Activity implements IActivity {
    constructor(public id?: number, public activityName?: string, public uprofiles?: IUprofile[]) {}
}
