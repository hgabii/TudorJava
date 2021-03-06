import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from "react-router-dom";
import { CookiesProvider } from 'react-cookie';
import App from './App';
import './index.css';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(
  <Router>
    <CookiesProvider>
      <App />
    </CookiesProvider>
  </Router>
  , document.getElementById('root')
);
registerServiceWorker();
