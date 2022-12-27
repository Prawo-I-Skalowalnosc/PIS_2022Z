import React from 'react';
import '../style/App.css';
import {FirstMovie} from "../components/FirstMovie";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <p>
          Templatka aplikacji napisana z użyciem <code>TypeScript</code> z biblioteką <code>React</code>.
        </p>
        <p>
          Używane narzędzia do zarządzania projektem: Github, Jira, Jenkins.
        </p>
        <a
            className="App-link"
            href="https://www.filmweb.pl/"
            target="_blank"
            rel="noopener noreferrer"
        >
          Kliknij tutaj
        </a>
        {/*do usunięcia potem, jest tutaj tylko testowo, żeby sprawdzić czy info z db dostaje się na front*/}
        <FirstMovie onSuccess={() => {}} onError={() => {}}/>
      </header>
    </div>
  );
}

export default App;
