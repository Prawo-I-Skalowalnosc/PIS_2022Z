import {Card} from "@mui/material";
import Layout from "./layout/Layout";
import {Helmet} from "react-helmet";

export function NewMovieForm() {
    return (
        <>
            <Helmet>
                <title>Cinex ∙ Dodaj film</title>
            </Helmet>
            <Layout>
                <Card>
                    <p>Formularz</p>
                </Card>
            </Layout>
        </>
    )

}

export default NewMovieForm;
