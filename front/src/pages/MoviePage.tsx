import {useEffect, useState} from 'react';
import '../style/App.css';
import '../style/movie.css';
import Layout from "../components/layout/Layout";
import {Requests} from "../requests/Requests";
import {MovieResponse} from "../types/Movies";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import {Helmet} from "react-helmet";
import {useParams} from "react-router-dom";


export default function MoviePage() {
    let { id } = useParams();
    const [error, setError] = useState("");
    const [movieData, setMovieData] = useState<MovieResponse>({} as MovieResponse);
    useEffect(() => {
        Requests.getMovieById(id ?? '').then(res => {
            if (res.err) {
                setMovieData({} as MovieResponse)
                setError("Brak filmu w bazie");
            } else if (res.res) {
                setMovieData(res.res);
            }
        });
    },[id])
    return <>
        <Helmet>
            <title>Cinex ∙ Opis filmu</title>
        </Helmet>
        <Layout>
            <div className="conatiner-fluid-pis-movie-page">
                <ErrorAndInfo infoMsg={""} errorMsg={error}/>
                {!error && movieData.length > 0 &&
                    <>
                    <div className="pis-movie-page-cont">
                        <h1 className={'pis-movie-page-data-content'} id='movie-title'>{movieData.title}</h1>
                        <div className='pis-movie-page-main-info'>
                            <div className='pis-movie-page-picture'>
                                <img src={movieData.poster_url} alt={movieData.title}/>
                            </div>
                            <div className='pis-movie-page-static-content'>

                                <div className='pis-movie-page-data-section'>
                                    <p className='pis-movie-page-data-headers'>Gatunek</p>
                                    <p className={'pis-movie-page-data-content'}>{movieData.genre}</p>
                                </div>

                                <div className='pis-movie-page-data-section'>
                                    <p className='pis-movie-page-data-headers'>Kraj wydania</p>
                                    <p className={'pis-movie-page-data-content'}>{movieData.country_of_origin}</p>
                                </div>

                                <div className='pis-movie-page-data-section'>
                                    <p className='pis-movie-page-data-headers'>Język</p>
                                    <p className={'pis-movie-page-data-content'}>{movieData.language}</p>
                                </div>

                                <div className='pis-movie-page-data-section'>
                                    <p className='pis-movie-page-data-headers'>Premiera</p>
                                    <p className={'pis-movie-page-data-content'}>{movieData.releaseDate?.substring(0, 10)}</p>
                                </div>

                                <div className='pis-movie-page-data-section'>
                                    <p className='pis-movie-page-data-headers'>Czas trwania</p>
                                    <p className={'pis-movie-page-data-content'}>{movieData.length} min</p>
                                </div>

                                <div className='pis-movie-page-data-section'>
                                    <p className='pis-movie-page-data-headers'>Budżet</p>
                                    <p className={'pis-movie-page-data-content'}>{movieData.budget}$</p>
                                </div>

                            </div>
                        </div>

                        <div className='pis-movie-page-ratings'>

                        </div>
                        <div className='pis-movie-page-comments'>
                        </div>
                    </div>
                </>}

            </div>
        </Layout>
    </>;
}