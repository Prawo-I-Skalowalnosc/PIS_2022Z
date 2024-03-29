import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {ErrorResponse} from "../types/ErrorResponse";
import {Helmet} from "react-helmet";
import Layout from "../components/layout/Layout";
import Stack from "@mui/material/Stack";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import NewMovieForm from "../components/NewMovieForm";

export function NewMoviePage() {
    const [error, setError] = useState("");
    let navigate = useNavigate();

    const handleError = (res: ErrorResponse) => {
        setError(res.infoMessage);
        setTimeout(() => {
            setError("")
        }, 5000);
    }

    return (
        <>
            <Helmet>
                <title>Cinex ∙ Dodaj film</title>
            </Helmet>
            <Layout>
                <Stack>
                    <NewMovieForm onSuccess={() => navigate("/movies")}
                                  onError={(res) => handleError(res)}/>
                    <div className="pis-register-error">
                        <ErrorAndInfo errorMsg={error} infoMsg={""}/>
                    </div>
                </Stack>
            </Layout>
        </>
    )
}
