import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import Parent from './Containers/Parent'
import './App.css';


function App() {
  return (
    <Router className="App">
      <Route path='/' component={Parent}/>
      <h1>Hello World!</h1>
    </Router>
  );
}

export default App;
