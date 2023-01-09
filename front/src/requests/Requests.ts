import {Global} from "../Config";
import {MovieResponse} from "../types/Movies"
import {ErrorResponse} from "../types/ErrorResponse";
import {UserRate, UserRateResponse} from "../types/UserRate";
import {Credentials, LoginResponse, RegisterCredentials} from "../types/Credentials";
import {SecurityHelper} from "../helpers/SecurityHelper";
import {PersonResponse} from "../types/Person";

function fetchPost(body: any, url: string){
    return fetch(Global.backendUrl + url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${SecurityHelper.getToken()}`
        },
        body: JSON.stringify(body)
    })
}

function fetchGet(url: string) {
    return fetch(Global.backendUrl + url, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${SecurityHelper.getToken()}`
        }
    })
}

function fetchPut(body: any, url: string){
    return fetch(Global.backendUrl + url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `${SecurityHelper.getToken()}`
        },
        body: JSON.stringify(body)
    })
}

class GenericResponse <T>{
    res?: T = undefined
    err?: ErrorResponse = undefined
}

async function handleResponse(response: Response) {
    console.log(response);
    SecurityHelper.refreshContext()

    if (response.status === 401)
        return {err: {status: response.status, infoMessage: "Brak uprawnień", timestamp: new Date(), message: ""}};

    const json = await response.json();

    if (response.status === 200)
        return {res: json};
    return {err: json};
}

export class Requests {
    static async firstMovie(): Promise<GenericResponse<MovieResponse>> {
        const response = await fetchGet("/movies/first")
        return handleResponse(response);
    }

    static async allMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetchGet("/movies/all")
        return handleResponse(response);
    }

    static async allPeople(): Promise<GenericResponse<PersonResponse[]>> {
        const response = await fetchGet("/people/all")
        return handleResponse(response);
    }

    static async getPersonById(id : string): Promise<GenericResponse<PersonResponse>> {
        const response = await fetchGet(`/people/byID?id=${id}`)
        return handleResponse(response);
    }

    static async getMovieById(id : string): Promise<GenericResponse<MovieResponse>> {
        const response = await fetchGet(`/movies/byID?id=${id}`)
        return handleResponse(response);
    }

    static async getMovieByTitle(title : string): Promise<GenericResponse<MovieResponse>> {
        const response = await fetchGet(`/movies/byTitle=${title}`)
        return handleResponse(response);
    }

    static async upcomingMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetchGet("/movies/upcoming")
        return handleResponse(response);
    }

    static async bestMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetchGet("/movies/best")
        return handleResponse(response);
    }

    static async newestMovies(): Promise<GenericResponse<MovieResponse[]>> {
        const response = await fetchGet("/movies/newest")
        return handleResponse(response);
    }

    static async sendRate(rate : UserRate): Promise<GenericResponse<UserRateResponse>> {
        const response = await fetchPut(rate, "/movieRatings/addRating")
        return handleResponse(response);
    }

    static async login(cred: Credentials): Promise<GenericResponse<LoginResponse>> {
        const response = await fetchPost(cred, "/account/login")
        if (response.status !== 200)
            SecurityHelper.deleteContext()
        return handleResponse(response);
    }

    static async register(cred: RegisterCredentials): Promise<GenericResponse<LoginResponse>> {
        const response = await fetchPost(cred, "/account/register")
        return handleResponse(response);
    }

    static async getUsersRating(id : string): Promise<GenericResponse<number>> {
        const response = await fetchGet(`/movies/userRating?id=${id}`)
        return handleResponse(response);
    }

    static async getUserRating(id : string): Promise<GenericResponse<number>> {
        const response = await fetchGet(`/movieRatings/user/?id=${id}`)
        return handleResponse(response);
    }

    static async addMovie(request: MovieResponse): Promise<GenericResponse<MovieResponse>> {
        const response = await fetchPost(request, "/movies/create")
        return handleResponse(response);
    }

    static async addPerson(request: PersonResponse): Promise<GenericResponse<PersonResponse>> {
        const response = await fetchPost(request, "/people/create")
        return handleResponse(response);
    }
}
