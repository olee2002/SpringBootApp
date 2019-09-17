import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";

import Parent from './Containers/Parent'
import './App.css';


function App() {
  return (
    <Router>
      <Route exact path='/' component={Parent}/>
    </Router>
  );
}

export default App;
