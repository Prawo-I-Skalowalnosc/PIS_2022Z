import {useEffect, useState} from "react";
import Grid2 from "@mui/material/Unstable_Grid2";
import Stack from "@mui/material/Stack";
import {PersonCard} from "./PersonCard";
import {Requests} from "../../requests/Requests";
import {SearchField} from "../movie-grid/SearchField";
import {Helmet} from "react-helmet";
import Layout from "../layout/Layout";
import {useNavigate} from "react-router-dom";
import {PeopleResponse, PersonResponse} from "../../types/Person";

export function PeopleGrid() {
    const [people, setPeople] = useState([] as PeopleResponse);
    const [inputText, setInputText] = useState("");
    const navigate = useNavigate()

    useEffect( () => {
        Requests.allPeople().then(res => {
            if (res.err) {
                setPeople([] as PeopleResponse);
            } else if (res.res) {
                setPeople(res.res);
            }
        })
    }, [])

    const handleCallback = (childData: any) => {
        setInputText(childData);
    }

    const filterPeople = people.filter((person: PersonResponse) => {
        if (inputText === '') {
            return person;
        } else {
            return `${person.name} ${person.last_name}`.toLowerCase().includes(inputText);
        }
    })

    return (
        <div>
            <Helmet>
                <title>Cinex ∙ Ludzie kina</title>
            </Helmet>
            <Layout>
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
                        {filterPeople.map((person: PersonResponse) => {
                            return <Grid2 xs={"auto"} key={person.id}
                                          onClick={() => navigate(`/people/${person.id}`)}>
                                          <PersonCard person_info={person}/></Grid2>;
                        })}
                    </Grid2>
                </Stack>
            </Layout>
        </div>
    )
}
