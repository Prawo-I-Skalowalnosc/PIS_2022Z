import {useEffect, useState} from "react";
import Grid2 from "@mui/material/Unstable_Grid2";
import {Box} from "@mui/material";
import {MovieIcon} from "./MovieIcon";
import {MovieResponse} from "../types/Movies";
import {Requests} from "../requests/Requests";
import {ErrorResponse} from "../types/ErrorResponse";

interface MovieProps {
    onSuccess: (response: MovieResponse) => void,
    onError: (err: ErrorResponse) => void
}

export function MovieGrid(props: MovieProps) {
    const [movies, setMovies] = useState({} as MovieResponse)
    const [amount, setAmount] = useState(0);

    useEffect( () => {
    Requests.movies().then(res => {
        if (res.err) {
            setMovies({} as MovieResponse);
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

    return (
        <Box>
            <Grid2 container
                   alignItems="center"
                   justifyContent="center"
                   display="flex"
                   spacing={2}
                   direction="row"
                   margin={0}>

                {Array.from({length: amount}, (_, idx) => {
                    //@ts-ignore
                    return <Grid2 xs={"auto"} key={idx}><MovieIcon poster_url={movies[idx].poster_url}/></Grid2>;
                })
                }
            </Grid2>
        </Box>
    )
}
