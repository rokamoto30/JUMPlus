import React, {createContext, useState, useEffect } from "react";

export const ShopContext = createContext();

// const getDefaultCart = (products) => {
//     var cart = [];
//     for (let i = 0; i < products.length; i++) {
//         cart[products[i].id] = 0;
//     }
//     return cart;
// }


export const ShopContextProvider = (props) => {
    const [cartItems, setCartItems] = useState({});

    const addToCart = (id) => {
        let curItems = cartItems;
        if (id in cartItems) {
            curItems[id] += 1;
        } else {
            curItems[id] = 1;
        }
        setCartItems( () => ({...curItems}) );
    };


    const removeFromCart = (id) => {
        let curItems = cartItems;
        if (id in cartItems && curItems[id] > 0) {
            curItems[id] -= 1;
        }
        setCartItems( () => ({...curItems}) );
    };

    // if(cartItems.length === 0) {
    //     setCartItems(getDefaultCart(props.products));
    // }

    const contextValue = {cartItems, addToCart, removeFromCart};
    return (
        <ShopContext.Provider value={contextValue} >{props.children}</ShopContext.Provider>
    );
}
