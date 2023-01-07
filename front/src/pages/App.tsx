import React from 'react';
import '../style/App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import MainMenuPage from "./MainMenuPage";
import MoviePage from "./MoviePage";
import {MovieGrid} from "../components/movie-grid/MovieGrid";
import {NewMoviePage} from "./NewMoviePage";


export default function App() {
    return (
    <div className="App">
        <header className="App-header">
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<MainMenuPage />} />
              <Route path="/movies" element={<MovieGrid />}/>
              <Route path="/create" element={<NewMoviePage />} />
              <Route path="/login" element={<LoginPage/>} />
              <Route path="/register" element={<RegisterPage />} />
              <Route path="/movies/:id" element={<MoviePage />} />
            </Routes>
          </BrowserRouter>
        </header>
    </div>);
}
