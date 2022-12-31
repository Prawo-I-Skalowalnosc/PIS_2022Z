import {Card, CardMedia} from "@mui/material";
import {MovieResponse} from "../types/Movies";

interface movieIconProps {
    movie_info: MovieResponse
}

export function MovieIcon(props: movieIconProps) {
    return (
        <Card
            sx={{ height: 300, width: 200 }}
        >
            <CardMedia
                sx={{ height: 1}}
                image={ props.movie_info.poster_url }
                title={ "star wars movie poster" }
            />
        </Card>
    );
}
