import React, {useContext, useState, useEffect} from 'react'
import {UserContext} from "../../context/context"
import {Order} from "./order";
import "./account.css";


export const Account = (props) => {

  const{curUser, setUser} = useContext(UserContext);


  const [orders, setOrders] = useState([]);
    useEffect(() => { // make api call and parse as json
        fetch(`http://localhost:3000/orders?curUser=${curUser}`)
        .then(response => response.json())
        .then(json => {
          console.log(json)
          setOrders(json)
        })
        .catch(error => console.error(error));
    }, []);

    const signOut = () => {
      setUser("")
      props.data("");
      setOrders()
    }

  return (
    <div className = "accountPage">
      <button className = "signOut" onClick={signOut}>Sign out of <b>{curUser}</b> </button>
      <h1>Orders</h1>
      <div className = "orderItems">
         {
          orders.map( (order) => {
            return <Order data={order}></Order>
          })
         }
      </div>
    </div>
  )
}
