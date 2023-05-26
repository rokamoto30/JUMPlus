import React, {useState, useEffect, useContext} from 'react'
import {ShopContext} from "../../context/shopContext"
import {CheckoutItem} from "./checkoutItem";
import {UserContext, InventoryContext} from "../../context/context"

import "./checkout.css";



export const Checkout = () => {

  const{products} = useContext(InventoryContext);
  const{curUser, setUser} = useContext(UserContext);

  // const [products, setProducts] = useState([]);
  // useEffect(() => { // make api call and parse as json
  //   fetch('http://localhost:3000/furniture')
  //   .then(response => response.json())
  //   .then(json => setProducts(json))
  //   .catch(error => console.error(error));
  // }, []);

  const{addToCart, cartItems} = useContext(ShopContext);

  const total = () => {
    let totalCost = 0;
    for (let product of products) {
      let count = cartItems[product.id];
      if (count > 0) {
        totalCost += product.price * count;
      }
    }
    return totalCost;
  }



  return (
    <div className = "checkout">
      <div>
        <h1>Checkout</h1>
      </div>
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
         }
      </div>
      <div className = "paymentInfo">
         {total()}
      </div>
    </div>
  )
}
