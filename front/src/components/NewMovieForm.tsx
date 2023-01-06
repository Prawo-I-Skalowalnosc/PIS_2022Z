import {useState} from "react";
import {Helmet} from "react-helmet";
import {Button, Card, Grid, MenuItem, TextField} from "@mui/material";
import Stack from "@mui/material/Stack";
import FormControl from '@mui/material/FormControl';
import Layout from "./layout/Layout";
import {MovieResponse} from "../types/Movies";
import {COUNTRIES} from "../helpers/CountryList";
import {GENRES} from "../helpers/GenreList";
import SendIcon from '@mui/icons-material/Send'
import "../style/register.css"

export function NewMovieForm() {
    const [movieData, setMovieData] = useState({} as MovieResponse);

    return (
        <>
            <Helmet>
                <title>Cinex ∙ Dodaj film</title>
            </Helmet>
            <Layout>
                <main className={"pis-register-page"}>
                    <Card className={"pis-newmovie-card"}>
                        <Stack>
                            Dodaj nowy film
                        <FormControl className={"pis-newmovie-form"}>
                            <TextField
                                label={"Tytuł filmu"}
                                required
                                sx={{mt: "1rem"}}/>
                            <TextField
                                required
                                select
                                label={"Gatunek"}
                                sx={{mt: "1rem"}}>
                                {GENRES.map((genre: string) => {
                                    return <MenuItem value={genre}>{genre}</MenuItem>
                                })}
                            </TextField>
                            <TextField
                                select
                                label={"Kraj powstania"}
                                sx={{mt: "1rem"}}>
                                {COUNTRIES.map((country: string) => {
                                    return <MenuItem value={country}>{country}</MenuItem>
                                })}
                            </TextField>
                            <TextField
                                type={"number"}
                                label={"Budżet (w mln $)"}
                                sx={{mt: "1rem"}}/>
                            <TextField
                                label={"Język filmu"}
                                sx={{mt: "1rem"}}/>
                            <TextField
                                type={"date"}
                                label={"Data premiery"}
                                defaultValue={new Date().toISOString().split('T')[0]}
                                sx={{mt: "1rem"}}/>
                            <TextField
                                type={"number"}
                                label={"Długość trwania filmu (w minutach)"}
                                sx={{mt: "1rem"}}/>
                        </FormControl>
                            <Grid sx={{mt: "1rem"}}>
                                <Button variant="contained" color="error" sx={{m: "0 10px"}}>
                                    Cancel
                                </Button>
                                <Button variant="contained" endIcon={<SendIcon />} sx={{m: "0 10px"}}>
                                    Add movie
                                </Button>
                            </Grid>
                        </Stack>
                </Card>
                </main>
            </Layout>
        </>
    )

}

export default NewMovieForm;
