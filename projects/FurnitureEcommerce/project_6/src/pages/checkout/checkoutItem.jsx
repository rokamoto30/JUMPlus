import React from 'react'
import "./checkout.css";


export const CheckoutItem = (props) => {
    const{product, count} = props.data;
  return (
    <div className = "checkoutItem">
        <text>{product.name}</text>
        <text>${product.price}</text>
        <text>X{count}</text>
    </div>
  )
}
