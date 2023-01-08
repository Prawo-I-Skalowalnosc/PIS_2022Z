import {Helmet} from "react-helmet";
import {useNavigate} from "react-router-dom";
import Layout from "../components/layout/Layout";
import NewMovieForm from "../components/NewMovieForm";
import React, {useState} from "react";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import Stack from "@mui/material/Stack";
import {ErrorResponse} from "../types/ErrorResponse";

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
