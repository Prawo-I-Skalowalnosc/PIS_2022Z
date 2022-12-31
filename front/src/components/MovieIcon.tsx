import {Card, CardMedia} from "@mui/material";

interface movieIconProps {
    poster_url: string
}

export function MovieIcon(props: movieIconProps) {
// export function MovieIcon() {
    return (
        <Card
            sx={{ height: 300, width: 200 }}
        >
            <CardMedia
                sx={{ height: 1}}
                image={ props.poster_url }
                title={ "star wars movie poster" }
            />
        </Card>
    );
}
