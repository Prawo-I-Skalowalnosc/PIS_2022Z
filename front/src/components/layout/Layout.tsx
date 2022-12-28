import NavBar from "./NavBar";
import Footer from "./Footer";
import "../../style/layout.css"

// TODO: usunąć ts-ignore
// @ts-ignore
const Layout = ({ children }) => {
    return (
        <>
            <NavBar/>
            <main>{children}</main>
            <Footer/>
        </>
    )
}
export default Layout