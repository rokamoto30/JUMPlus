import React from 'react'
import "./checkout.css";


export const CheckoutItem = (props) => {
    const{product, count, label} = props.data;
    if (product) {
      return (
        <div className = "checkoutItem">
            <text>{product.name}</text>
            <text>${product.price}</text>
            <text>X {count}</text>
        </div>
      )
    } else if(label) {
      return (
        <div className = "checkoutItem">
            <text>{label}</text>
            <text>{count}</text>
            <text></text>
        </div>
      )
    }
  // return (
  //   <div className = "checkoutItem">
  //       <text>{product.name}</text>
  //       <text>${product.price}</text>
  //       <text>X {count}</text>
  //   </div>
  // )
}
