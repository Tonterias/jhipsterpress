export interface IUrllink {
    id?: number;
    linkText?: string;
    linkURL?: string;
}

export class Urllink implements IUrllink {
    constructor(public id?: number, public linkText?: string, public linkURL?: string) {}
}
