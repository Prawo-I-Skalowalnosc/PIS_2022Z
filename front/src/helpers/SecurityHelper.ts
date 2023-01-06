import Cookies from "universal-cookie";
import {Global} from "../Config";
import cookieMaxAge = Global.cookieMaxAge;

export class SecurityHelper {
    static token: string = 'TOKEN';
    static username: string = 'USERNAME';
    static admin: string = 'ADMIN';

    static cookies = new Cookies();
    static setContext(context: UserContext) {
        SecurityHelper.cookies.set(SecurityHelper.token, context.token, {path: '/', maxAge: cookieMaxAge});
        SecurityHelper.cookies.set(SecurityHelper.username, context.username, {path: '/', maxAge: cookieMaxAge});
        SecurityHelper.cookies.set(SecurityHelper.admin, context.isAdmin, {path: '/', maxAge: cookieMaxAge});
        // jednostką maxAge są sekundy
    }

    static deleteContext() {
        SecurityHelper.cookies.remove(SecurityHelper.token);
        SecurityHelper.cookies.remove(SecurityHelper.username);
        SecurityHelper.cookies.remove(SecurityHelper.admin);
    }

    static refreshContext() {
        const context = SecurityHelper.getContext();
        if (!!context.token && !!context.username && context.isAdmin){
            SecurityHelper.deleteContext();
            SecurityHelper.setContext(context)
        }
    }

    static amILogged() : boolean {
        return !!SecurityHelper.getToken();
    }

    static amIAdmin() : boolean {
        return !!SecurityHelper.cookies.get(SecurityHelper.admin);
    }

    static getToken() : string {
        return SecurityHelper.cookies.get(SecurityHelper.token);
    }

    static getContext() : UserContext {
        const token = SecurityHelper.cookies.get(SecurityHelper.token);
        const username = SecurityHelper.cookies.get(SecurityHelper.username);
        const admin = SecurityHelper.cookies.get(SecurityHelper.admin);
        return {token: token, isAdmin: admin, username: username};
    }
}

export type UserContext = {
    token : string
    username : string
    isAdmin : boolean
}