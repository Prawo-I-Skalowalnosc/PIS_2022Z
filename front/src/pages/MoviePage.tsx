import {useEffect, useState} from 'react';
import '../style/App.css';
import '../style/movie.css';
import Layout from "../components/layout/Layout";
import {Requests} from "../requests/Requests";
import {MovieResponse} from "../types/Movies";
import {ErrorAndInfo} from "../components/ErrorAndInfo";

export default function MoviePage() {
    const [error, setError] = useState("");
    const [movieData, setMovieData] = useState<MovieResponse>({} as MovieResponse);
    useEffect(() => {
        Requests.getMovieById(window.location.href.split('/')[4]).then(res => {
            if (res.err) {
                setMovieData({} as MovieResponse)
                setError("Brak filmu w bazie");
            } else if (res.res) {
                setMovieData(res.res);
            }
        });
    },[])
    return <>
        <Layout>
            <div className="conatiner-fluid-pis-movie-page">
                <ErrorAndInfo infoMsg={""} errorMsg={error}/>
                {!error && movieData.length > 0 &&
                    <>
                    <div className="pis-movie-page-cont">
                        <div className='pis-movie-page-picture'>
                            <img src={movieData.poster_url} alt={movieData.title}/>
                        </div>
                        <div className='pis-movie-page-static-content'>
                            <h1 className={'pis-movie-page-data-content'}>{movieData.title}</h1>

                            <h2 className='pis-movie-page-data-headers'>Autor</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.author}</h4>

                            <h2 className='pis-movie-page-data-headers'>Gatunek</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.genre}</h4>

                            <h2 className='pis-movie-page-data-headers'>Kraj wydania</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.country_of_origin}</h4>

                            <h2 className='pis-movie-page-data-headers'>Język</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.language}</h4>

                            <h2 className='pis-movie-page-data-headers'>Premiera</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.releaseDate?.substring(0, 10)}</h4>

                            <h2 className='pis-movie-page-data-headers'>Czas trwania</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.length} min</h4>

                            <h2 className='pis-movie-page-data-headers'>Budżet</h2>
                            <h4 className={'pis-movie-page-data-content'}>{movieData.budget}$</h4>
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