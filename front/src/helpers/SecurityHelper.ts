import Cookies from "universal-cookie";
import {Global} from "../Config";
import cookieMaxAge = Global.cookieMaxAge;

export class SecurityHelper {
    static context: string = 'CONTEXT';
    static admin: string = 'admin';
    static moderator: string = 'moderator';
    static user: string = 'user';
    static cookies = new Cookies();
    static setContext(context: UserContext) {
        SecurityHelper.cookies.set(SecurityHelper.context, JSON.stringify(context),
            {path: '/', maxAge: cookieMaxAge});
        // jednostką maxAge są sekundy
        // po włączeniu https należy dodać secure: true
    }

    static deleteContext() {
        SecurityHelper.cookies.remove(SecurityHelper.context);
    }

    static refreshContext() {
        const context = SecurityHelper.getContext();
        SecurityHelper.deleteContext();
        if (!!context.token && !!context.username && !!context.roles){
            SecurityHelper.setContext(context)
        }
    }

    static amILogged() : boolean {
        return !!SecurityHelper.getToken();
    }

    static amIInRole(role: string) : boolean {
        return SecurityHelper.getContext().roles.some(x => x === role);
    }

    static getToken() : string {
        return SecurityHelper.getContext().token;
    }

    static getContext() : UserContext {
        const data = SecurityHelper.cookies.get(SecurityHelper.context);
        if (!data)
            return {roles:[], token:"", username:""}
        return data;
    }
}

export type UserContext = {
    token : string
    username : string
    roles : string[]
}