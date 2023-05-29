import React, {useState, useEffect, useContext} from 'react'
import {CheckoutItem} from "./checkoutItem";
import {UserContext, InventoryContext, CartContext, PaymentContext} from "../../context/context"

import "./checkout.css";



export const Checkout = () => {

  const{products} = useContext(InventoryContext);
  const{curUser} = useContext(UserContext);
  const{cartItems, clearCart} = useContext(CartContext);
  const{Address, setAddress, CardHolder, setCardHolder, CardNumber, setCardNumber, CardType, setCardType, Exp, setExp, Ccv, setCcv, Total, setTotal, Time, setTime, send} = useContext(PaymentContext);



  const subTotal = () => {
    let totalCost = 0;
    for (let product of products) {
      let count = cartItems[product.id];
      if (count > 0) {
        totalCost += product.price * count;
      }
    }
    return totalCost;
  }

  const discount = () => {
    if (subTotal() > 2000) {
      let props = {
        label: "Discount",
        count: "20%"
      }
      return <CheckoutItem data={props}></CheckoutItem>;
    }
    return "";
  }

  const getTotal = () => {
    let curTotal = subTotal()
    if (curTotal > 2000) {
      curTotal -= curTotal*.2
    }
    let rounded = Math.ceil(curTotal * 100) / 100
    return rounded;
  }

  const totalComponent = () => {
    if (getTotal() > 0) {
      let props = {
        label: "Total",
        count: getTotal()
      }
      return <CheckoutItem data={props}></CheckoutItem>
    }
    
  }

  const setTotalTime = () => {
    let currentDate = new Date()
    let date = `${currentDate.getMonth() + 1}-${currentDate.getDate()}-${currentDate.getFullYear()}`
    let time = `${currentDate.getHours()}:${currentDate.getMinutes()}`
    let curTime = {
      date: date,
      time: time
    }
    setTime(curTime)
    let curTotal = subTotal()
    if (curTotal > 2000) {
      curTotal -= curTotal*.2
    }
    let rounded = Math.ceil(curTotal * 100) / 100
    setTotal(rounded)
  }


  

  const purchase = (e) => {
    e.preventDefault();
    if(curUser != "" && Time!=undefined && Total!=0 && Total!= undefined) {
      console.log("SENT")
      // setTotalTime();
      // send(curUser, cartItems);

      // useEffect(() => {
      //   setTotalTime();
      // }, []);
      // useEffect(() => {
        let body = JSON.stringify({curUser, Time, Address, CardHolder, CardNumber, CardType, Exp, Ccv, Total, cartItems})
        fetch('http://localhost:3000/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: body
        })
        .then(response => response.json())
        .then(json => console.log(json))
      // }, []);
    }
    
    clearCart();
  }



  return (
    <div className = "checkout">
      {/* <div>
        <h1>Checkout</h1>
      </div> */}
      <div className = "checkoutItems">
         {
          products.map( (product) => {
            const itemCount = cartItems[product.id];
            if (itemCount > 0) {
              let props = {
                product: product,
                count: itemCount
              }
              return <CheckoutItem data={props}></CheckoutItem>
            }
          })
         } <br />{discount()} <br /> {totalComponent()}
      </div>


      <div className = "paymentInfo">
      <form onSubmit={purchase}>
            <h2>Payment and Shipping Information</h2>
            {/* <label for="address">Address</label> */}
            <input placeholder= "address" type="text" id="address" value ={Address} onChange={(e) => setAddress(e.target.value)} required/>

            {/* <label for="cardHolderName">Cardholder Name</label> */}
            <input placeholder = "Cardholder Name" type="text" id="cardHolderName" value ={CardHolder} onChange={(e) => setCardHolder(e.target.value)} required/>

            {/* <label for="cardNumber">Card Number</label> */}
            <input placeholder = "Card Number" type="number" id="cardNumber" value ={CardNumber} onChange={(e) => setCardNumber(e.target.value)} required />

            {/* <label for="cardType">Card Type</label> */}
            <select id="cardType" value ={CardType} onChange={(e) => setCardType(e.target.value)} required>
              <option value="">--Select a Card Type--</option>
              <option value="Visa">Visa</option>
              <option value="MasterCard">MasterCard</option>
              <option value="Discover">Discover</option>
            </select>

            <div className="expcvv">
              {/* <label for="expDate" id="expText">Expiry</label> */}
              <input type="date" id="expDate" value ={Exp} onChange={(e) => setExp(e.target.value)} required />

              {/* <label for="cvv" id="cvvText">CVV</label> */}
              <input placeholder = "CVV" type="password" id="cvv" value ={Ccv} onChange={(e) => setCcv(e.target.value)} required />
            </div>

            <button onClick={setTotalTime} id="checkoutButton" type="submit">Check Out</button>
          </form>
      </div>
    </div>
  )
}
