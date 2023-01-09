import {PersonResponse} from "../../types/Person";
import '../../style/moviePage.css';
import React from "react";


interface PersonInfoProps {
    person: PersonResponse
}

export function PersonInfo(props: PersonInfoProps) {
    return <>
        <table className="table table-borderless pis-movieinfo-table">
            <tbody>
            <tr>
                <th className="pis-movieinfo-header">Imię</th>
                <td className="pis-movieinfo-content">{props.person.name}</td>
            </tr>
            <tr>
                <th className="pis-movieinfo-header">Nazwisko</th>
                <td className="pis-movieinfo-content">{props.person.last_name}</td>
            </tr>
            </tbody>
        </table>
    </>}
