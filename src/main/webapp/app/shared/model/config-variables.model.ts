export interface IConfigVariables {
    id?: number;
    configVarLong1?: number;
    configVarLong2?: number;
    configVarLong3?: number;
    configVarLong4?: number;
    configVarLong5?: number;
    configVarLong6?: number;
    configVarLong7?: number;
    configVarLong8?: number;
    configVarLong9?: number;
    configVarLong10?: number;
    configVarLong11?: number;
    configVarLong12?: number;
    configVarLong13?: number;
    configVarLong14?: number;
    configVarLong15?: number;
    configVarBoolean16?: boolean;
    configVarBoolean17?: boolean;
    configVarBoolean18?: boolean;
    configVarString19?: string;
    configVarString20?: string;
}

export class ConfigVariables implements IConfigVariables {
    constructor(
        public id?: number,
        public configVarLong1?: number,
        public configVarLong2?: number,
        public configVarLong3?: number,
        public configVarLong4?: number,
        public configVarLong5?: number,
        public configVarLong6?: number,
        public configVarLong7?: number,
        public configVarLong8?: number,
        public configVarLong9?: number,
        public configVarLong10?: number,
        public configVarLong11?: number,
        public configVarLong12?: number,
        public configVarLong13?: number,
        public configVarLong14?: number,
        public configVarLong15?: number,
        public configVarBoolean16?: boolean,
        public configVarBoolean17?: boolean,
        public configVarBoolean18?: boolean,
        public configVarString19?: string,
        public configVarString20?: string
    ) {
        this.configVarBoolean16 = this.configVarBoolean16 || false;
        this.configVarBoolean17 = this.configVarBoolean17 || false;
        this.configVarBoolean18 = this.configVarBoolean18 || false;
    }
}
