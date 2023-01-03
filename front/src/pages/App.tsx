import React from 'react';
import '../style/App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./LoginPage";
import RegisterPage from "./RegisterPage";
import MainMenuPage from "./MainMenuPage";
import { StarRating, StarShow } from '../components/Stars';

export default function App() {
  return (
      <div className="App">
        <header className="App-header">
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<MainMenuPage />} />
              <Route path="/login" element={<LoginPage/>} />
              <Route path="/register" element={<RegisterPage />} />
            </Routes>
          </BrowserRouter>
        </header>
        <StarRating rater_id={"a45da728-3a40-4c2c-8028-946ca33be960"} movie_id = {"c2d29867-3d0b-d497-9191-18a9d8ee7830"}size={24} maxRating={5} onSuccess={(a : any) => {}} onError={(a : any) => {}}></StarRating>
      </div>
  );
}
