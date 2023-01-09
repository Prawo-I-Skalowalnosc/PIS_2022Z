import '../../style/moviePage.css';
import React from "react";
import {PeopleResponse} from "../../types/Movies";


interface MoviePeopleProps {
    people: PeopleResponse
}

export function MoviePeople(props: MoviePeopleProps) {
    return <><table className="table table-borderless pis-moviepeople-table">
        <tbody>
        {!!props.people.directors?.length && <tr>
            <th className="pis-moviepeople-header">Reżyseria</th>
            {props.people.directors.map(director => {
                return <td className="pis-moviepeople-content">{director}</td>
            })}
        </tr>}
        {!!props.people.screenwriters?.length && <tr>
            <th className="pis-moviepeople-header">Scenariusz</th>
            {props.people.screenwriters.map(screenwriter => {
                return <td className="pis-moviepeople-content">{screenwriter}</td>
            })}
        </tr>}
        {!!props.people.actors?.length && <tr>
            <th className="pis-moviepeople-header">Obsada</th>
            {props.people.actors.map(actor => {
                return <td className="pis-moviepeople-content">{actor}</td>
            })}
        </tr>}
        </tbody>
    </table>
    </>}