import {MovieResponse} from "../types/Movies";
import {Link} from "react-router-dom";
import React from "react";

import "../style/mainMenu.css";


interface MoviesListProps {
    movies: MovieResponse[]
}

export function MenuMoviesList(props: MoviesListProps) {
    return <>
        <div className="pis-card-cont">
        {props.movies && props.movies.map(movie => {
            return  <>
                <div className="card pis-card has-bg-img" style={{backgroundImage: `url(${movie.poster_url})`}}>
                    <div className="pis-card-title">{movie.title}</div>
                </div>
            </>
        })}
    </div>
    </>}
