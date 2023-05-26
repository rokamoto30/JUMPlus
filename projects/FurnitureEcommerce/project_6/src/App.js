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
// import {UserContext} from "../../context/context"



function App() {
  

  // const [products, setProducts] = useState([]);
  // useEffect(() => { // make api call and parse as json
  //   fetch('http://localhost:3000/furniture')
  //   .then(response => response.json())
  //   .then(json => setProducts(json))
  //   .catch(error => console.error(error));
  // }, []);


  // const [curUser, setUser] = useState("guest");
  // useEffect(() => { // make api call and parse as json
  //   fetch('http://localhost:3000/users')
  //   .then(response => response.json())
  //   .then(json => setUser(json))
  //   .catch(error => console.error(error));
  // }, []);


  // let props = {
  //   products: products,
  //   setProducts: setProducts,
  //   curUser: curUser,
  //   setUser: setUser
  // }
  // const{curUser, setUser} = useContext(UserContext);

  const [appCurUser, setAppCurUser] = useState("");
  const [loginState, setLoginState] = useState(<Login data={setAppCurUser}></Login>)
  const [registerState, setRegisterState] = useState(<Register data={setAppCurUser}/>)

  const getLoginPage = () => {
    console.log("switching login")
    if (appCurUser === "") {
      return <Login data={setAppCurUser}/>
    } else {
      return <History/>
    }
  }
  const getRegisterPage = () => {
    console.log("switching register")
    if (appCurUser === "") {
      return <Register data={setAppCurUser}/>
    } else {
      return <History/>
    }
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
                  <Route path="/login" element={
                  getLoginPage()
                  }></Route>
                  <Route path="/register" element={getRegisterPage()}></Route>
                  <Route path="/" element={<Shop/>}></Route>
                  <Route path="/checkout" element={<Checkout/>}></Route>
                  <Route path="/history" element={<History/>}></Route>
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
