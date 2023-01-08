import React from "react";
import {SecurityHelper} from "./SecurityHelper";
import {Navigate, Outlet} from "react-router-dom";

export function PrivateRoute () {
    // {/*<Route path="/" element={<RouteHelper/>}>
    //      <Route path="/" element={<MainMenuPage/>}/>
    //    </Route>*/}
    //  przykład oznaczania strony wymagającej zalogowania, inaczej przekierowuje na /login

    if (!SecurityHelper.amILogged()) {
        return <Navigate to="/login"/>;
    }
    return <Outlet/>;
}

export function AdminRoute () {
    // {/*<Route path="/" element={<RouteHelper/>}>
    //      <Route path="/" element={<MainMenuPage/>}/>
    //    </Route>*/}
    //  przykład oznaczania strony wymagającej zalogowania, inaczej przekierowuje na /login

    if (!SecurityHelper.amIAdmin()) {
        return <Navigate to="/login"/>;
    }
    return <Outlet/>;
}



