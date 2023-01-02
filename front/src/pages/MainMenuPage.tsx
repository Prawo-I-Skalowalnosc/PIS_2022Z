import {useEffect, useState} from 'react';
import '../style/App.css';
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import {MenuMoviesList} from "../components/MenuMoviesList";
import Layout from "../components/layout/Layout";
import {MovieResponse} from "../types/Movies";
import {Requests} from "../requests/Requests";

export default function MainMenuPage() {
    const [error, setError] = useState("");
    const [recentMovies, setRecentMovies] = useState<MovieResponse[]>([]);
    const [upcomingMovies, setUpcomingMovies] = useState<MovieResponse[]>([]);
    const [bestMovies, setBestMovies] = useState<MovieResponse[]>([]);

    useEffect(() => {
        Requests.newestMovies().then(res => {
            if (res.err) {
                setRecentMovies([] as MovieResponse[]);
                setError("Brak filmów w bazie");
            } else if (res.res) {
                setRecentMovies(res.res);
            }
        });

        Requests.upcomingMovies().then(res => {
            if (res.err) {
                setUpcomingMovies([] as MovieResponse[]);
            } else if (res.res) {
                setUpcomingMovies(res.res);
            }
        });
        
        Requests.bestMovies().then(res => {
            if (res.err) {
                setBestMovies([] as MovieResponse[]);
                setError("Brak filmów w bazie");
            } else if (res.res) {
                setBestMovies(res.res);
            }
        });

    }, [])

    return <>
        <Layout>
            <div className="App container-fluid pis-mainpage-cont">
                <div className="pis-mainpage-error">
                    <ErrorAndInfo errorMsg={error} infoMsg={""}/>
                </div>
                {recentMovies.length !== 0 &&
                <>
                    <h4 className="pis-mainpage-newesttext">NAJNOWSZE FILMY</h4>
                    <MenuMoviesList movies={recentMovies}/>
                </>}

                {upcomingMovies.length !== 0 &&
                <>
                    <h4 className="pis-mainpage-upcomingtext">NAJBLIŻSZE PREMIERY</h4>
                    <MenuMoviesList movies={upcomingMovies}/>
                </>}

                {bestMovies.length !== 0 &&
                <>
                    <h4 className="pis-mainpage-besttext">NAJLEPSZE FILMY</h4>
                    <MenuMoviesList movies={bestMovies}/>
                </>}
            </div>
        </Layout>
    </>;
}