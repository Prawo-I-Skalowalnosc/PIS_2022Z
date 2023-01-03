import {Card, CardMedia} from "@mui/material";
import {MovieResponse} from "../../types/Movies";

import "../../style/mainMenu.css";

interface movieIconProps {
    movie_info: MovieResponse
}

export function MovieCard(props: movieIconProps) {
    return (
        <Card
            className={"pis-moviegrid-card"}
            sx={{ height: 300, width: 200 }}
        >
            <CardMedia
                sx={{ height: 1}}
                image={ props.movie_info.poster_url }
                title={ props.movie_info.title }
            />
        </Card>
    );
}
