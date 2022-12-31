import React, {FormEvent, useState} from 'react';
import {RegisterCredentials, RegisterResponse} from '../types/Credentials';
import {Requests} from "../requests/Requests";
import {ErrorResponse} from "../types/ErrorResponse";
import '../style/register.css';


interface RegisterFormProps {
    onSuccess: (response: RegisterResponse) => void,
    onError: (err: ErrorResponse) => void
}

export function RegisterForm(props: RegisterFormProps) {
    const [credentials, setCredentials] = useState({} as RegisterCredentials);
    const [isConfirmedPassword, setConfirmedPassword] = useState(true);

    const handleSubmit = (e: FormEvent) => {
        e.preventDefault();
        if (!credentials)
            return;

        if (credentials.confirmPassword !== credentials.password)
            setConfirmedPassword(false);
        else {
            setConfirmedPassword(true);
            Requests.register(credentials).then(res => {
                if (res.err) {
                    props.onError(res.err);
                }
                else if (res.res){
                    props.onSuccess(res.res)

                }
            })
        }
    }

    return <>
        <div className="container-fluid pis-register-cont">
            <div className="row pis-register-row">
                <div className="col-lg-3 col-md-6 col-sm-12">
                    <form onSubmit={handleSubmit}>
                        <div className="card text-black bg-light pis-register-card">
                             <label className="pis-register-user">
                                <small>Nazwa użytkownika</small>
                                <input className="form-control" type="text" name="username" maxLength={20} required onChange={(e) => {
                                    setCredentials({...credentials, username: e.target.value})
                                }} />
                            </label>
                            <label className="pis-register-email">
                                <small>Email</small>
                                <input className="form-control" type="text" name="email" style={{marginBottom: "4%"}} maxLength={50} required onChange={(e) => {
                                    setCredentials({...credentials, email: e.target.value})
                                }} />
                            </label>
                            <label className="pis-register-pwd">
                                <small>Hasło</small>
                                <input className="form-control" type="password" name="password" style={{marginBottom: "4%"}} maxLength={100} required onChange={(e) => {
                                    setCredentials({...credentials, password: e.target.value})
                                }} />
                            </label>
                            <label className="pis-register-pwdconf">
                            <small>Powtórz hasło</small>
                                <input className="form-control" type="password" name="password" required onChange={(e) => {
                                    setCredentials({...credentials, confirmPassword: e.target.value})
                                }} />
                                {!isConfirmedPassword &&
                                    <div className="badge bg-warning pis-register-badge">Hasła nie są identyczne!</div>
                                }
                            </label>
                            <div className="pis-register-submit">
                            <button type="submit" onClick={handleSubmit} className="btn btn-outline-success">Zarejestruj się</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        </>
}
