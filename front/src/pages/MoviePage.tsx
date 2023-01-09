import React, {useEffect, useState} from 'react';
import '../style/App.css';
import '../style/moviePage.css';
import Layout from "../components/layout/Layout";
import {Requests} from "../requests/Requests";
import {MovieResponse} from "../types/Movies";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import { StarRating, StarShow } from '../components/Stars';
import {Helmet} from "react-helmet";
import {useParams} from "react-router-dom";
import {MovieInfo} from "../components/page-movie/MovieInfo";
import {MoviePeople} from "../components/page-movie/MoviePeople";


export default function MoviePage() {
    let { id } = useParams();
    const [error, setError] = useState("");
    const [info, setInfo] = useState("");
    const [movie, setMovie] = useState<MovieResponse>({} as MovieResponse);
    const [people, setPeople] = useState({});
    const [userRating, setUserRating] = useState(1);

    useEffect(() => {
        Requests.getMovieById(id ?? '').then(res => {
            if (res.err) {
                setMovie({} as MovieResponse)
                setError(res.err.infoMessage);
            } else if (res.res) {
                setMovie(res.res);
                setUserRating(res.res.userRating);
            }
        });
        // TODO pobieranie z bazy ludzi
        setPeople({});
    },[id])

    const getUserRating = () => {
        Requests.getUserRating(movie.id).then(r => {
            if (r.res)
                setUserRating(r.res)
        })
    }

    return <>
        <Helmet>
            <title>Cinex ∙ Opis filmu</title>
        </Helmet>
        <Layout>
            <div className="App container-fluid pis-moviepage-cont">
                <div className="pis-moviepage-error">
                    <ErrorAndInfo errorMsg={error} infoMsg={info}/>
                </div>
                {movie.length && <div className="container-fluid pis-moviepage-info-cont">
                    <div className="row align-items-center">
                        <div className="col-lg-2 col-md-2 col-sm-12 pis-moviepage-poster-col">
                            <div className="card pis-moviepage-card" style={{backgroundImage: `url(${movie.poster_url})`}}/>
                        </div>
                        <div className="col-lg-4 col-md-6 col-sm-12">
                            <div className="container pis-moviepage-data-cont">
                                <MovieInfo movie={movie}/>
                            </div>
                        </div>
                    </div>
                </div>}
                {movie.length && people && <div className="container-fluid pis-moviepage-people-cont">
                    <MoviePeople /*people={{}}*//>
                </div>}
                {movie.length && people && <div className="container-fluid pis-moviepage-rating-cont">
                    <div className='pis-movie-page-ratings'>
                        <div className='pis-stars-text'>Twoja ocena</div>
                            <StarRating
                                movie_id = {movie.id} size={20} maxRating={5}
                                onSuccess={(response) => {
                                    getUserRating();
                                    response.isNew ? setInfo("Dodano nową ocenę") : setInfo("Zaktualizowano ocenę")
                                }}
                                onError={(response) => setError(response.infoMessage)}
                                resetInfo={() => {setInfo(""); setError("")}}
                            />
                            <div className='pis-stars-text'>Ocena krytyków</div><StarShow rating={movie.rating * 5} size={20} maxRating={5} />
                            <div className='pis-stars-text'>Ocena użytkowników</div><StarShow rating= {userRating} size={20} maxRating={5} />
                        </div>
                </div>}
            </div>
        </Layout>
    </>;
}
