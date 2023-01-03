import {useEffect, useState} from "react";
import Grid2 from "@mui/material/Unstable_Grid2";
import {Stack} from "@mui/material";
import {MovieCard} from "./MovieCard";
import {MovieResponse, MoviesResponse} from "../../types/Movies";
import {Requests} from "../../requests/Requests";
import {SearchField} from "./SearchField";
import {Helmet} from "react-helmet";
import Layout from "../layout/Layout";
import {useNavigate} from "react-router-dom";

export function MovieGrid() {
    const [movies, setMovies] = useState([] as MoviesResponse)
    const [inputText, setInputText] = useState("");
    const navigate = useNavigate()

    useEffect( () => {
    Requests.allMovies().then(res => {
        if (res.err) {
            setMovies([] as MoviesResponse);
        } else if (res.res) {
            setMovies(res.res);
        }})
    })

    const handleCallback = (childData: any) => {
        setInputText(childData);
    }

    const filterMovies = movies.filter((movie: MovieResponse) => {
        if (inputText === '') {
            return movie;
        } else {
            return movie.title.toLowerCase().includes(inputText);
        }
    })

    return (
        <div>
            <Helmet>
                <title>Cinex ∙ Wszystkie filmy</title>
            </Helmet>
            <Layout>
                <Stack
                    margin={0}
                    sx={{height: 1, marginTop: 0}}
                    >
                <SearchField inputHandler={handleCallback}/>
                    <Grid2 container
                           justifyContent="center"
                           display="flex"
                           spacing={2}
                           direction="row"
                           margin={0}>
                        {filterMovies.map((movie: MovieResponse) => {
                            return <Grid2 xs={"auto"} key={movie.id}
                                onClick={() => navigate(`/movies/${movie.id}`)}><MovieCard movie_info={movie}/></Grid2>;
                        })}
                    </Grid2>
                </Stack>
            </Layout>
        </div>
    )
}
