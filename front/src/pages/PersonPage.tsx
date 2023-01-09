import React, {useEffect, useState} from 'react';
import Layout from "../components/layout/Layout";
import {Requests} from "../requests/Requests";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import {Helmet} from "react-helmet";
import {useParams} from "react-router-dom";
import {PersonResponse} from "../types/Person";
import {PersonInfo} from "../components/page-person/PersonInfo";
import '../style/App.css';
import '../style/moviePage.css';

export default function PersonPage() {
    let { id } = useParams();
    const [person, setPerson] = useState({} as PersonResponse);
    const [error, setError] = useState("");
    const [info, setInfo] = useState("");

    useEffect(() => {
        Requests.getPersonById(id ?? '').then(res => {
            if (res.err) {
                setPerson({} as PersonResponse)
                setError(res.err.infoMessage);
            } else if (res.res) {
                setPerson(res.res);
            }
        });
    },[id])

    return <>
        <Helmet>
            <title>Cinex ∙ Opis osoby</title>
        </Helmet>
        <Layout>
            <div className="App container-fluid pis-moviepage-cont">
                <div className="pis-moviepage-error">
                    <ErrorAndInfo errorMsg={error} infoMsg={info}/>
                </div>
                {person.name && <div className="container-fluid pis-moviepage-info-cont">
                    <div className="row align-items-center">
                        <div className="col-lg-2 col-md-2 col-sm-12 pis-moviepage-poster-col">
                            <div className="card pis-moviepage-card" style={{backgroundImage: `url(${person.photo_url})`}}/>
                        </div>
                        <div className="col-lg-4 col-md-6 col-sm-12">
                            <div className="container pis-moviepage-data-cont">
                                <PersonInfo person={person}/>
                            </div>
                        </div>
                    </div>
                </div>}
            </div>
        </Layout>
    </>;
}
