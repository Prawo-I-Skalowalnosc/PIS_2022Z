import {useState} from 'react';
import '../style/App.css';
import {Link, useNavigate} from "react-router-dom";
import { RegisterForm } from "../components/RegisterForm";
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import Layout from "../components/layout/Layout";

export default function RegisterPage() {
    const [error, setError] = useState("");
    const navigate = useNavigate()

    return <>
        <Layout>
            <div className="container-fluid pis-register-page">
                <div className="App pis-register-page-cont">
                    <div className="pis-register-error">
                        <ErrorAndInfo errorMsg={error} infoMsg={""}/>
                    </div>
                    <RegisterForm onSuccess={() => navigate("/login")} onError={(res) => setError(res.infoMessage)}/>
                    <small className="pis-register-loglink-text">Masz już konto?
                        <Link className="pis-link pis-register-loglink" to="/login"> Zaloguj się</Link>
                    </small>
                </div>
            </div>
        </Layout>
    </>;
}