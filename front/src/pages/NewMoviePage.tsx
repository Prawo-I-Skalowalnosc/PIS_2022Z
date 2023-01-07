import {Helmet} from "react-helmet";
import Layout from "../components/layout/Layout";
import NewMovieForm from "../components/NewMovieForm";

export function NewMoviePage() {
    return (
        <>
            <Helmet>
                <title>Cinex ∙ Dodaj film</title>
            </Helmet>
            <Layout>
                <NewMovieForm />
            </Layout>
        </>
    )
}
