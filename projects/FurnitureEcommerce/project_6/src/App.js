import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {NavBar} from "./components/navbar";
import{Login} from "./pages/login/login";
import{Register} from "./pages/login/register";
import{Shop} from "./pages/shop/shop";
import{Checkout} from "./pages/checkout/checkout";
import{History} from "./pages/history/history";
import { ShopContextProvider } from './context/shopContext';
import { CartContextProvider } from './context/context';
import { UserContextProvider } from './context/context';
import { InventoryContextProvider } from './context/context';
import { useState, useEffect } from 'react';


function App() {
  

  const [products, setProducts] = useState([]);
  useEffect(() => { // make api call and parse as json
    fetch('http://localhost:3000/furniture')
    .then(response => response.json())
    .then(json => setProducts(json))
    .catch(error => console.error(error));
  }, []);


  const [curUser, setUser] = useState("guest");
  useEffect(() => { // make api call and parse as json
    fetch('http://localhost:3000/users')
    .then(response => response.json())
    .then(json => setUser(json))
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
      <UserContextProvider>
        <InventoryContextProvider>
          <CartContextProvider>
            <ShopContextProvider>
              <Router>
                <NavBar/>
                <Routes>
                  <Route path="/login" element={<Login data={props}/>}></Route>
                  <Route path="/register" element={<Register data={props}/>}></Route>
                  <Route path="/" element={<Shop data={props}/>}></Route>
                  <Route path="/checkout" element={<Checkout data={props}/>}></Route>
                  <Route path="/history" element={<History data={props}/>}></Route>
                </Routes>
              </Router>
            </ShopContextProvider>
          </CartContextProvider>
        </InventoryContextProvider>
      </UserContextProvider>
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
