import {useState} from 'react';
import '../style/App.css';
import {ErrorAndInfo} from "../components/ErrorAndInfo";
import {FirstMovie} from "../components/FirstMovie";
import Layout from "../components/layout/Layout";

export default function MainMenuPage() {
    const [error, setError] = useState("");

    return <>
        <Layout>
        <ErrorAndInfo infoMsg={""} errorMsg={error}/>
        <FirstMovie onSuccess={() => {}} onError={(res) => setError(res.message)}/>
        </Layout>
    </>;
}