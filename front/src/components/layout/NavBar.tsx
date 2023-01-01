import {Link} from "react-router-dom";
import "../../style/layout.css"
import {TokenHelper} from "../../helpers/TokenHelper";

const NavBar = () => {
    return <div>
        <nav className="navbar navbar-dark pis-navbar">
            <ul className="navbar-nav pis-navbar-nav">
                <li className="nav-item pis-nav-item">
                <Link className="nav-link pis-navbar-link-main" to="/">CINEX</Link>
                </li>
            </ul>
            <ul className="navbar-nav pis-navbar-nav me-auto">
                <li className="nav-item pis-nav-item">
                <Link className="nav-link pis-navbar-link" to="/movies">FILMY</Link>
                </li>
                <li className="nav-item pis-nav-item">
                <Link className="nav-link pis-navbar-link" to="/movies/rankings">RANKINGI</Link>
                </li>
            </ul>
            <ul className="navbar-nav pis-navbar-nav ms-auto">
                {TokenHelper.amILogged() && <li className="nav-item pis-nav-item">
                    <Link onClick={() => {
                        TokenHelper.deleteToken();
                        window.location.reload();
                    }} className="nav-link pis-navbar-link" to="/">Wyloguj się</Link>
                </li>}
                <li className="nav-item pis-nav-item">
                    <Link className="nav-link pis-navbar-link" to="/login">Zaloguj się</Link>
                </li>
                <li className="nav-item pis-nav-item">
                    <Link className="nav-link pis-navbar-link" to="/register">Zarejestruj się</Link>
                </li>
            </ul>
        </nav>
    </div>
}
export default NavBar