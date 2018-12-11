import { ICasse } from 'app/shared/model//casse.model';
import { IReception } from 'app/shared/model//reception.model';
import { ISortie } from 'app/shared/model//sortie.model';
import { IRetour } from 'app/shared/model//retour.model';
import { IRecuperation } from 'app/shared/model//recuperation.model';
import { IUtilisateurProfil } from 'app/shared/model//utilisateur-profil.model';
import { IUtilisateurDepot } from 'app/shared/model//utilisateur-depot.model';
import { ITransfert } from 'app/shared/model//transfert.model';
import { IStockReception } from 'app/shared/model//stock-reception.model';

export interface IUtilisateur {
    id?: number;
    idUtilisateur?: number;
    loginUtilisateur?: string;
    matriculeUtilisateur?: string;
    nomUtilsateur?: string;
    passwordUtilisateur?: string;
    prenomUtilsateur?: string;
    casses?: ICasse[];
    receptions?: IReception[];
    sorties?: ISortie[];
    retours?: IRetour[];
    recuperations?: IRecuperation[];
    utilisateurProfils?: IUtilisateurProfil[];
    utilisateurDepots?: IUtilisateurDepot[];
    transferts?: ITransfert[];
    stockReceptions?: IStockReception[];
}

export class Utilisateur implements IUtilisateur {
    constructor(
        public id?: number,
        public idUtilisateur?: number,
        public loginUtilisateur?: string,
        public matriculeUtilisateur?: string,
        public nomUtilsateur?: string,
        public passwordUtilisateur?: string,
        public prenomUtilsateur?: string,
        public casses?: ICasse[],
        public receptions?: IReception[],
        public sorties?: ISortie[],
        public retours?: IRetour[],
        public recuperations?: IRecuperation[],
        public utilisateurProfils?: IUtilisateurProfil[],
        public utilisateurDepots?: IUtilisateurDepot[],
        public transferts?: ITransfert[],
        public stockReceptions?: IStockReception[]
    ) {}
}
