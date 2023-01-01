import {useEffect, useState} from "react";
import Grid2 from "@mui/material/Unstable_Grid2";
import {Box, Stack} from "@mui/material";
import {MovieIcon} from "./MovieIcon";
import {MoviesResponse} from "../types/Movies";
import {Requests} from "../requests/Requests";
import {ErrorResponse} from "../types/ErrorResponse";
import {SearchField} from "./SearchField";

interface MovieProps {
    onSuccess: (response: MoviesResponse) => void,
    onError: (err: ErrorResponse) => void
}

export function MovieGrid(props: MovieProps) {
    const [movies, setMovies] = useState([] as MoviesResponse)
    const [amount, setAmount] = useState(0);
    const [inputText, setInputText] = useState("");

    useEffect( () => {
    Requests.allMovies().then(res => {
        if (res.err) {
            setMovies({} as MoviesResponse);
            props.onError({
                message: "",
                infoMessage: "Brak filmów w bazie", status: 0, timestamp: new Date()
            });
        } else if (res.res) {
            setMovies(res.res);
            props.onSuccess(res.res);
            setAmount(Object.keys(res.res).length);
        }
    })
    }, [props])

    const handleCallback = (childData: any) => {
        setInputText(childData);
    }

    // const filterData = movies.filter()

    return (
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

                {Array.from({length: amount}, (_, idx) => {
                    return <Grid2 xs={"auto"} key={idx}><MovieIcon movie_info={movies[idx]}/></Grid2>;
                })
                }
            </Grid2>
        </Stack>
    )
}
