import {MovieResponse} from "../types/Movies";
import {Link} from "react-router-dom";
import React from "react";

import "../style/mainMenu.css";


interface MoviesListProps {
    movies: MovieResponse[]
}

export function MenuMoviesList(props: MoviesListProps) {
    return <>
        <div className="pis-mainpagelist-card-cont">
        {props.movies && props.movies.map(movie => {
            return  <>
                <div className="card pis-mainpagelist-card" style={{backgroundImage: `url(${movie.poster_url})`}}>
                    <div className="pis-mainpagelist-card-title">{movie.title}</div>
                </div>
            </>
        })}
    </div>
    </>}
