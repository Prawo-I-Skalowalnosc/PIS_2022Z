import {Global} from "../Config";
import {MovieResponse} from "../types/Movies"
import {ErrorResponse} from "../types/ErrorResponse";
import {Credentials, LoginResponse, RegisterCredentials} from "../types/Credentials";

function fetchPost(body: any, url: string){
    return fetch(Global.backendUrl + url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(body)
    })
}

class GenericResponse <T>{
    res?: T = undefined
    err?: ErrorResponse = undefined
}

function setResponseOrError(response: any) {
    if (response.status && response.status !== 200)
        return {err: response};
    return {res: response};
}

export class Requests {
    static async firstMovie(): Promise<GenericResponse<MovieResponse>> {
        const response = await fetch(Global.backendUrl + "/movies/first")
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async allMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetch(Global.backendUrl + "/movies/all")
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async getMovieById(id : string): Promise<GenericResponse<MovieResponse>> {
        const response = await fetch(Global.backendUrl + `/movies/byID=${id}`)
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async getMovieByTitle(title : string): Promise<GenericResponse<MovieResponse>> {
        const response = await fetch(Global.backendUrl + `/movies/byTitle=${title}`)
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async upcomingMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetch(Global.backendUrl + "/movies/upcoming")
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async bestMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetch(Global.backendUrl + "/movies/best")
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async newestMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetch(Global.backendUrl + "/movies/newest")
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async login(cred: Credentials): Promise<GenericResponse<LoginResponse>> {
        const response = await fetchPost(cred, "/account/login")
            .then(res => res.json())
        return setResponseOrError(response);
    }

    static async register(cred: RegisterCredentials): Promise<GenericResponse<LoginResponse>> {
        const response = await fetchPost(cred, "/account/register")
            .then(res => res.json())
        return setResponseOrError(response);
    }
}
