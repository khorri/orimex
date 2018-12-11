import { Moment } from 'moment';

export interface IAchatPrevisionArrivage {
    id?: number;
    idPrevisionArrivage?: number;
    produit?: string;
    desigantionCopagnieMaritme?: string;
    pol?: string;
    numeroBl?: string;
    nombreTc?: number;
    etd?: Moment;
    eta?: Moment;
    documents?: string;
    douane?: string;
    active?: number;
    achatDossierId?: number;
}

export class AchatPrevisionArrivage implements IAchatPrevisionArrivage {
    constructor(
        public id?: number,
        public idPrevisionArrivage?: number,
        public produit?: string,
        public desigantionCopagnieMaritme?: string,
        public pol?: string,
        public numeroBl?: string,
        public nombreTc?: number,
        public etd?: Moment,
        public eta?: Moment,
        public documents?: string,
        public douane?: string,
        public active?: number,
        public achatDossierId?: number
    ) {}
}
