import {useState} from 'react';
import '../style/App.css';
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import Layout from "../components/layout/Layout";

export default function MainMenuPage() {
    const [error, setError] = useState("");

    return <>
        <Layout>
        <ErrorAndInfo infoMsg={""} errorMsg={error}/>
        </Layout>
    </>;
}
