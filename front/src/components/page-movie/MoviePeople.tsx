import '../../style/moviePage.css';
import React from "react";


// interface MoviePeopleProps {
//     people: unknown
// }

export function MoviePeople(/*props: MoviePeopleProps*/) {
    return <><table className="table table-borderless pis-moviepeople-table">
        <tbody>
        <tr>
            <th className="pis-moviepeople-header">Reżyseria</th>
            <td className="pis-moviepeople-content">Nieznany</td>
            {/*<td className="pis-moviepeople-content">{props.people.directors}</td>*/}
        </tr>
        <tr>
            <th className="pis-moviepeople-header">Scenariusz</th>
            <td className="pis-moviepeople-content">Nieznany</td>
            {/*<td className="pis-moviepeople-content">{props.people.screenwriters}</td>*/}
        </tr>
        <tr>
            <th className="pis-moviepeople-header">Obsada</th>
            <td className="pis-moviepeople-content">Nieznany, Nieznana</td>
            {/*<td className="pis-moviepeople-content">{props.people.actors}</td>*/}
        </tr>
        </tbody>
    </table>
    </>}