import Cookies from "universal-cookie";

export class TokenHelper {
    static cookies = new Cookies();
    static setToken(token: string) {
        TokenHelper.cookies.set('TOKEN', token, {path: '/', maxAge: 3600});
    }

    static deleteToken() {
        TokenHelper.cookies.remove('TOKEN');
    }

    static refreshToken() {
        const token = TokenHelper.cookies.get('TOKEN');
        TokenHelper.setToken(token);
    }

    static amILogged() : boolean {
        return !!TokenHelper.cookies.get('TOKEN');
    }

    static getToken() : string {
        return TokenHelper.cookies.get('TOKEN');
    }
}