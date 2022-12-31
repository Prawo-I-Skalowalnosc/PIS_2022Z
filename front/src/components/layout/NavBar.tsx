import {Link} from "react-router-dom";
import "../../style/layout.css"

const NavBar = () => {
    return (
        <nav className="navbar navbar-dark pis-navbar">
            <Link className="navbar-brand pis-navbar-link" to="/">PIS</Link>
        </nav>)
}
export default NavBar