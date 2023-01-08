import {Link} from "react-router-dom";
import "../../style/layout.css"
import {SecurityHelper} from "../../helpers/SecurityHelper";

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
                <li className="nav-item pis-nav-item">
                    <Link className="nav-link pis-navbar-link" to="/create">DODAJ FILM</Link>
                </li>
            </ul>
            <ul className="navbar-nav pis-navbar-nav ms-auto">
                {SecurityHelper.amILogged() && <li className="nav-item pis-nav-item">
                    <Link onClick={() => {
                        SecurityHelper.deleteContext();
                        window.location.reload();
                    }} className="nav-link pis-navbar-link" to="/">
                        <i className="bi bi-box-arrow-right"/> Wyloguj się
                    </Link>
                </li>}

                {!SecurityHelper.amILogged() && <li className="nav-item pis-nav-item">
                    <Link className="nav-link pis-navbar-link" to="/login">
                        <i className="bi bi-box-arrow-in-right"/> Zaloguj się
                    </Link>
                </li>}

                {!SecurityHelper.amILogged() && <li className="nav-item pis-nav-item">
                    <Link className="nav-link pis-navbar-link" to="/register">
                    <i className="bi bi-person-add"/> Zarejestruj się
                    </Link>
                </li>}

            </ul>
        </nav>
    </div>
}
export default NavBar;
