import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {NavBar} from "./components/navbar";
import{Login} from "./pages/login/login";
import{Shop} from "./pages/shop/shop";
import{Checkout} from "./pages/checkout/checkout";
import{History} from "./pages/history/history";
import { ShopContextProvider } from './context/shopContext';
import { useState, useEffect } from 'react';


function App() {
  const [curUser, setUser] = useState();

  const [products, setProducts] = useState([]);
  useEffect(() => { // make api call and parse as json
    fetch('http://localhost:3000/furniture')
    .then(response => response.json())
    .then(json => setProducts(json))
    .catch(error => console.error(error));
  }, []);

  let props = {
    products: products,
    setProducts: setProducts,
    curUser: curUser,
    setUser: setUser
  }

  return (
    <div  className = "App">
      <ShopContextProvider>
        <Router>
          <NavBar/>
          <Routes>
            <Route path="/login" element={<Login/>}></Route>
            <Route path="/shop" element={<Shop/>}></Route>
            <Route path="/checkout" element={<Checkout/>}></Route>
            <Route path="/history" element={<History/>}></Route>
          </Routes>
        </Router>
      </ShopContextProvider>
    </div>
  );


  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
