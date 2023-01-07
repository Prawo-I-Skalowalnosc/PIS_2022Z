import {useEffect, useState} from 'react';
import '../style/App.css';
import {Link, useNavigate} from "react-router-dom";
import { LoginForm } from "../components/LoginForm";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import Layout from "../components/layout/Layout";
import {SecurityHelper} from "../helpers/SecurityHelper";
import {Helmet} from "react-helmet";

export default function LoginPage() {
    const [error, setError] = useState("");
    const navigate = useNavigate()

    useEffect(() => {
        if (SecurityHelper.amILogged()){
            navigate("/", {replace: true});
        }
    })

    return <>
        <Helmet>
            <title>Cinex ∙ Zaloguj się</title>
        </Helmet>
        <Layout>
            <div className="App container-fluid pis-login-page">
                <div className="App pis-login-page-cont">
                    <div className="pis-login-error">
                        <ErrorAndInfo errorMsg={error} infoMsg={""}/>
                    </div>
                    <LoginForm onSuccess={(res) => {
                        SecurityHelper.setContext(res)
                        navigate("/")
                    }} onError={(res) => setError(res.infoMessage)}/>
                    <small className="pis-login-reglink-text">
                        Nie masz konta?
                        <Link className="pis-link pis-login-reglink" to="/register"> Załóż konto</Link>
                    </small>
                </div>
            </div>
        </Layout>
    </>;
}