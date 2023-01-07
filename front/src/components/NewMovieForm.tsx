import {FormEvent, useState} from "react";
import {useNavigate} from "react-router-dom";
import {Button, Stack, Grid, FormControl, MenuItem, TextField, Paper} from "@mui/material";
import SendIcon from '@mui/icons-material/Send'
import {MovieResponse} from "../types/Movies";
import {Requests} from "../requests/Requests";
import {COUNTRIES} from "../helpers/CountryList";
import {GENRES} from "../helpers/GenreList";
import "../style/register.css"

export function NewMovieForm() {
    const defaultDate = new Date().toISOString().split('T')[0];
    const [movieData, setMovieData] = useState({release_date: defaultDate, rating: 0} as MovieResponse);
    let navigate = useNavigate();

    const handleInput = (e: FormEvent) => {
        e.preventDefault();
        if (movieData.title && movieData.genre) {
            Requests.addMovie(movieData).then( res => {
                // TODO: error handling
            if (res.err) {}
            else {
                navigate("/movies");
                }})
        }
    }

    const goToMainPage = () => {
        const path="/";
        navigate(path);
    }

    return (
        <>
            <main className={"pis-register-page"}>
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
                                name={"genre"}
                                label={"Gatunek"}
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
                                name={"country_of_origin"}
                                label={"Kraj powstania"}
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
                                type={"number"}
                                label={"Budżet ($)"}
                                sx={{mt: "1rem"}}
                                onChange={(e) =>
                                    setMovieData({...movieData, budget: parseInt(e.target.value, 10)})}/>
                            <TextField
                                name={"language"}
                                label={"Język filmu"}
                                sx={{mt: "1rem"}}
                                onChange={(e) =>
                                    setMovieData({...movieData, language: e.target.value})}/>
                            <TextField
                                name={"release_date"}
                                type={"date"}
                                label={"Data premiery"}
                                defaultValue={defaultDate}
                                sx={{mt: "1rem"}}
                                onChange={(e) =>
                                    setMovieData({...movieData, release_date: e.target.value})}/>
                            <TextField
                                name={"length"}
                                type={"number"}
                                label={"Długość trwania filmu (w minutach)"}
                                sx={{mt: "1rem"}}
                                onChange={(e) =>
                                    setMovieData({...movieData, length: parseInt(e.target.value, 10)})}/>
                            <TextField
                                name={"poster_url"}
                                label={"Adres URL plakatu filmu"}
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
        </>
    )

}

export default NewMovieForm;
