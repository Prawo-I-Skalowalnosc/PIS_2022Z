import {useEffect, useState} from 'react';
import '../style/App.css';
import {Link, useNavigate} from "react-router-dom";
import { LoginForm } from "../components/LoginForm";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import Layout from "../components/layout/Layout";
import {TokenHelper} from "../helpers/TokenHelper";

export default function LoginPage() {
    const [error, setError] = useState("");
    const navigate = useNavigate()

    useEffect(() => {
        if (TokenHelper.amILogged()){
            navigate("/", {replace: true});
        }
    },[])

    return <>
        <Layout>
            <div className="App container-fluid pis-login-page">
                <div className="App pis-login-page-cont">
                    <div className="pis-login-error">
                        <ErrorAndInfo errorMsg={error} infoMsg={""}/>
                    </div>
                    <LoginForm onSuccess={(res) => {
                        TokenHelper.setToken(res.token)
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