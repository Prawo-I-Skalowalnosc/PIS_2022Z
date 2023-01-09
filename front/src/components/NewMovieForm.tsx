import {FormEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import {Button, Stack, Grid, FormControl, MenuItem, TextField, Paper} from "@mui/material";
import SendIcon from '@mui/icons-material/Send'
import {MovieResponse} from "../types/Movies";
import {Requests} from "../requests/Requests";
import {COUNTRIES} from "../helpers/CountryList";
import {GENRES} from "../helpers/GenreList";
import {ErrorResponse} from "../types/ErrorResponse";
import "../style/register.css"

interface NewMovieProps {
    onSuccess: (response: MovieResponse) => void,
    onError: (err: ErrorResponse) => void
}

export function NewMovieForm(props: NewMovieProps) {
    const defaultDate = new Date().toISOString().split('T')[0];
    const [movieData, setMovieData] = useState({releaseDate: defaultDate, rating: 0} as MovieResponse);
    let navigate = useNavigate();

    const handleInput = (e: FormEvent) => {
        e.preventDefault();
        if (movieData.title && movieData.genre) {
            Requests.addMovie(movieData).then( res => {
            if (res.err) {
                props.onError(res.err);
            }
            else if (res.res) {
                props.onSuccess(res.res);
                }})
        }
    }

    const goToMainPage = () => {
        const path="/";
        navigate(path);
    }

    return (<><main className={"pis-register-page"}>
        <Paper className={"pis-newmovie-card"}>
            <Stack>
                Dodaj nowy film
                <FormControl className={"pis-newmovie-form"}>
                    <TextField
                        required
                        label={"Tytuł filmu"}
                        name={"title"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                                setMovieData({...movieData, title: e.target.value})}/>
                    <TextField
                        required
                        select
                        label={"Gatunek"}
                        name={"genre"}
                        sx={{mt: "1rem"}}
                        defaultValue={''}
                        onChange={(e) =>
                            setMovieData({...movieData, genre: e.target.value})}>
                        {GENRES.map((genre: string) => {
                            return <MenuItem key={genre} value={genre}>{genre}</MenuItem>
                        })}
                    </TextField>
                    <TextField
                        select
                        label={"Kraj powstania"}
                        name={"country_of_origin"}
                        sx={{mt: "1rem"}}
                        defaultValue={''}
                        onChange={(e) =>
                            setMovieData({...movieData, country_of_origin: e.target.value})}>
                        {COUNTRIES.map((country: string) => {
                            return <MenuItem key={country} value={country}>{country}</MenuItem>
                        })}
                    </TextField>
                    <TextField
                        name={"budget"}
                        label={"Budżet ($)"}
                        type={"number"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setMovieData({...movieData, budget: parseInt(e.target.value, 10)})}/>
                    <TextField
                        label={"Język filmu"}
                        name={"language"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setMovieData({...movieData, language: e.target.value})}/>
                    <TextField
                        type={"date"}
                        label={"Data premiery"}
                        name={"release_date"}
                        defaultValue={defaultDate}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setMovieData({...movieData, releaseDate: e.target.value})}/>
                    <TextField
                        type={"number"}
                        label={"Długość trwania filmu (w minutach)"}
                        name={"length"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setMovieData({...movieData, length: parseInt(e.target.value, 10)})}/>
                    <TextField
                        label={"Adres URL plakatu filmu"}
                        name={"poster_url"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setMovieData({...movieData, poster_url: e.target.value})}/>
                    <Grid sx={{mt: "1rem", textAlign: "center"}}>
                        <Button
                            type="button"
                            variant="contained"
                            onClick={goToMainPage}
                            color="error"
                            sx={{m: "0 10px"}}>
                            Powróć
                        </Button>
                        <Button
                            type="submit"
                            variant="contained"
                            onClick={handleInput}
                            endIcon={<SendIcon />}
                            sx={{m: "0 10px"}}
                            disabled={!movieData.title || !movieData.genre}>
                            Dodaj film
                        </Button>
                    </Grid>
                </FormControl>
            </Stack>
        </Paper>
    </main>
    </>)
}

export default NewMovieForm;
