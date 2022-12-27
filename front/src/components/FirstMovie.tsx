import {useEffect, useState} from "react";
import {Requests} from "../requests/Requests";
import {MovieResponse} from "../types/Movies";
import {ErrorResponse} from "../types/ErrorResponse";
import {SimplifyDate} from "../helpers/DateHelpers";


interface MovieProps {
    onSuccess: (response: MovieResponse) => void,
    onError: (err: ErrorResponse) => void
}

export function FirstMovie(props: MovieProps) {
    const [movie, setMovie] = useState({} as MovieResponse)

    useEffect(() => {
        Requests.firstMovie().then(res => {
            if (res.err) {
                setMovie({} as MovieResponse);
                props.onError({
                    message: "",
                    infoMessage: "Brak filmów w bazie", status: 0, timestamp: new Date()
                });
            } else if (res.res) {
                setMovie(res.res);
                props.onSuccess(res.res);
            }
        });
    }, [props])

    return <>
    <small>
        ID: {movie.id} <br/>
        Tytuł: {movie.title} <br/>
        Ocena: {movie.rating} <br/>
        Gatunek: {movie.genre} <br/>
        Wyprodukowano w: {movie.country_of_origin} <br/>
        Budżet: {movie.budget}$ <br/>
        Język: {movie.language} <br/>
        Data premiery: {SimplifyDate(movie.release_date)} <br/>
        Długość: {movie.length} <br/>
        Plakat URL: {movie.poster_url}
    </small>
    </>;
}
