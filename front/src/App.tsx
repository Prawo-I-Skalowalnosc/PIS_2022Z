import React from 'react';
import logo from './logo.svg'
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" data-testid="App-logo" />
        <p>
          Templatka aplikacji napisana z użyciem <code>TypeScript</code> z biblioteką <code>React</code>.
        </p>
        <p>
          Używane narzędzia do zarządzania projektem: Github, Jira.
        </p>
        <a
            className="App-link"
            href="https://www.filmweb.pl/"
            target="_blank"
            rel="noopener noreferrer"
        >
          Kliknij tutaj
        </a>
      </header>
    </div>
  );
}

export default App;
