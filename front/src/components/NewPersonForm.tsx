import {PersonResponse} from "../types/Person";
import {ErrorResponse} from "../types/ErrorResponse";
import {useNavigate} from "react-router-dom";
import {FormEvent, useState} from "react";
import {Button, FormControl, Grid, Paper, Stack, TextField} from "@mui/material";
import SendIcon from "@mui/icons-material/Send";
import {Requests} from "../requests/Requests";
import "../style/register.css";

interface NewPersonProps {
    onSuccess: (response: PersonResponse) => void,
    onError: (err: ErrorResponse) => void
}

export function NewPersonForm(props: NewPersonProps) {
    const [personData, setPersonData] = useState({} as PersonResponse);
    const navigate = useNavigate();

    const handleInput = (e: FormEvent) => {
        e.preventDefault();
        if (personData.name && personData.last_name) {
            Requests.addPerson(personData).then( res => {
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

    const capitalise = (word: string): string => {
        return word.charAt(0).toUpperCase() + word.slice(1);
    }

    return (<><main className={"pis-register-page"}>
        <Paper className={"pis-newperson-card"}>
            <Stack>
                Dodaj nową osobę
                <FormControl className={"pis-newmovie-form"}>
                    <TextField
                        required
                        label={"Imię"}
                        name={"name"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setPersonData({...personData, name: capitalise(e.target.value)})}>
                    </TextField>
                    <TextField
                        required
                        label={"Nazwisko"}
                        name={"last_name"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setPersonData({...personData, last_name: capitalise(e.target.value)})}>
                    </TextField>
                    <TextField
                        label={"Adres URL zdjęcia osoby"}
                        name={"photo_url"}
                        sx={{mt: "1rem"}}
                        onChange={(e) =>
                            setPersonData({...personData, photo_url: e.target.value})}>
                    </TextField>
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
                            disabled={!personData.name || !personData.last_name}>
                            Dodaj osobę
                        </Button>
                    </Grid>
                </FormControl>
            </Stack>
        </Paper>
    </main></>)
}
