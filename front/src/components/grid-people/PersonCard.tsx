import {Card, CardContent, CardMedia, Typography} from "@mui/material";
import "../../style/mainMenu.css";
import {PersonResponse} from "../../types/Person";

interface movieIconProps {
    person_info: PersonResponse
}

export function PersonCard(props: movieIconProps) {
    return (
        <Card
            className={"pis-moviegrid-card"}
            sx={{ height: 300, width: 200 }}>
            <CardMedia
                sx={{ height: 250, width: 200 }}
                image={ props.person_info.photo_url }
                title={ `${props.person_info.name} ${props.person_info.last_name}` }
                component={ "img" }
            />
            <CardContent>
                <Typography gutterBottom>
                    {`${props.person_info.name} ${props.person_info.last_name}`}
                </Typography>
            </CardContent>
    </Card>
);
}
