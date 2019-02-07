import { IUprofile } from 'app/shared/model/uprofile.model';

export interface ICeleb {
    id?: number;
    celebName?: string;
    uprofiles?: IUprofile[];
}

export class Celeb implements ICeleb {
    constructor(public id?: number, public celebName?: string, public uprofiles?: IUprofile[]) {}
}
