import { IAction } from 'app/shared/model//action.model';
import { IMenu } from 'app/shared/model//menu.model';

export interface IMenu {
    id?: number;
    codeMenu?: string;
    cmampsMenu?: string;
    libelleMenu?: string;
    ordre?: number;
    domaine?: string;
    actions?: IAction[];
    menuId?: number;
    menus?: IMenu[];
}

export class Menu implements IMenu {
    constructor(
        public id?: number,
        public codeMenu?: string,
        public cmampsMenu?: string,
        public libelleMenu?: string,
        public ordre?: number,
        public domaine?: string,
        public actions?: IAction[],
        public menuId?: number,
        public menus?: IMenu[]
    ) {}
}
