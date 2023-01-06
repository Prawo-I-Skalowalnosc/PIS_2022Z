import {MovieResponse} from "../types/Movies";
import {useNavigate} from "react-router-dom";
import React from "react";

import "../style/mainMenu.css";


interface MoviesListProps {
    movies: MovieResponse[]
}

export function MenuMoviesList(props: MoviesListProps) {
    const navigate = useNavigate()
    return <>
        <div className="pis-mainpagelist-card-cont">
        {props.movies && props.movies.map(movie => {
            return  <>
                <div className="card pis-mainpagelist-card" style={{backgroundImage: `url(${movie.poster_url})`}}
                onClick={() => navigate(`/movies/${movie.id}`)}>
                    <div key={movie.id} className="pis-mainpagelist-card-title">{movie.title}</div>
                </div>
            </>
        })}
    </div>
    </>}
