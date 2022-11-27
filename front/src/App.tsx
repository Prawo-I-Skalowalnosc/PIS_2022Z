import React from 'react';
import logo from './logo.svg'
import './App.css';
import {ExampleComponent} from "./ExampleComponent";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" data-testid="App-logo" />
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
        <ExampleComponent></ExampleComponent> {/*do usunięcia potem, jest tutaj tylko testowo, żeby sprawdzić czy info z db dostaje się na front*/}
      </header>
    </div>
  );
}

export default App;
