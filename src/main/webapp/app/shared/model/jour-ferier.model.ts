import { Moment } from 'moment';

export interface IJourFerier {
    id?: number;
    annee?: number;
    debut?: Moment;
    fin?: Moment;
}

export class JourFerier implements IJourFerier {
    constructor(public id?: number, public annee?: number, public debut?: Moment, public fin?: Moment) {}
}
