import React from 'react';
import ReactDOM from 'react-dom/client';

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min";
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'animate.css';

import './style/index.css';
import App from './pages/App';


const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);
document.title = "Cinex";
root.render(
    <App />
);
