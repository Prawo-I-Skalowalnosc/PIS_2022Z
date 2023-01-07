import {MovieResponse} from "../../types/Movies";
import '../../style/moviePage.css';
import React from "react";


interface MovieInfoProps {
    movie: MovieResponse
}

export function MovieInfo(props: MovieInfoProps) {
    return <>
        <table className="table table-borderless pis-movieinfo-table">
            <tbody>
            <tr>
                <th className="pis-movieinfo-header">Gatunek</th>
                <td className="pis-movieinfo-content">{props.movie.genre}</td>
            </tr>
            <tr>
                <th className="pis-movieinfo-header">Kraj wydania</th>
                <td className="pis-movieinfo-content">{props.movie.country_of_origin}</td>
            </tr>
            <tr>
                <th className="pis-movieinfo-header">Język</th>
                <td className="pis-movieinfo-content">{props.movie.language}</td>
            </tr>
            <tr>
                <th className="pis-movieinfo-header">Data premiery</th>
                <td className="pis-movieinfo-content">{props.movie.releaseDate?.substring(0, 10)}</td>
            </tr>
            <tr>
                <th className="pis-movieinfo-header">Czas trwania</th>
                <td className="pis-movieinfo-content">{props.movie.length} minut</td>
            </tr>
            <tr>
                <th className="pis-movieinfo-header">Budżet</th>
                <td className="pis-movieinfo-content">{props.movie.budget}$</td>
            </tr>
            </tbody>
        </table>
    </>}