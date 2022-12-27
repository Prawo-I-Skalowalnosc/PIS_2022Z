import {Global} from "../Config";
import {MovieResponse} from "../types/Movies"
import {ErrorResponse} from "../types/ErrorResponse";

// function fetchPost(body: any, url: string){
//     return fetch(Global.backendUrl + url, {
//         method: 'POST',
//         headers: {'Content-Type': 'application/json'},
//         body: JSON.stringify(body)
//     })
// }

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
}
