import React from 'react';
import '../style/App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import MainMenuPage from "./MainMenuPage";
import MoviePage from "./MoviePage";
import {MovieGrid} from "../components/grid-movies/MovieGrid";
import {NewMoviePage} from "./NewMoviePage";
import {NewPersonPage} from "./NewPersonPage";
import {ModeratorRoute} from "../helpers/RouteHelper";
import {PeopleGrid} from "../components/grid-people/PeopleGrid";
import PersonPage from "./PersonPage";

export default function App() {
    return (
    <div className="App">
        <header className="App-header">
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<MainMenuPage />} />
              <Route path="/movies" element={<MovieGrid />}/>
              <Route path="/people" element={<PeopleGrid />}/>
              <Route path="/create" element={<ModeratorRoute />}>
                <Route path="/create" element={<NewMoviePage />} />
              </Route>
              <Route path="/newperson" element={<ModeratorRoute />}>
                <Route path="/newperson" element={<NewPersonPage />} />
              </Route>
              <Route path="/login" element={<LoginPage/>} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/movies/:id" element={<MoviePage />} />
              <Route path="/people/:id" element={<PersonPage />} />
            </Routes>
          </BrowserRouter>
        </header>
    </div>);
}
