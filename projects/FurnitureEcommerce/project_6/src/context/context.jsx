import React, {createContext, useState, useEffect } from 'react'

export const CartContext = createContext();
export const UserContext = createContext();
export const InventoryContext = createContext();
export const PaymentContext = createContext();


export const PaymentContextProvider = (props) => {
    const [Address, setAddress] = useState();
    const [CardHolder, setCardHolder] = useState();
    const [CardNumber, setCardNumber] = useState();
    const [CardType, setCardType] = useState();
    const [Exp, setExp] = useState();
    const [Ccv, setCcv] = useState();
    const [Total, setTotal] = useState();
    const [Time, setTime] = useState();

    const send = ({curUser, cartItems}) => {
        console.log("CALLING SEND")
        let body = JSON.stringify({curUser, Time, Address, CardHolder, CardNumber, CardType, Exp, Ccv, Total, cartItems})
        fetch('http://localhost:3000/orders', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: body
        })
        .then(response => response.json())
        .then(json => console.log("Json is: ", json))
        .catch(error => console.log("error"))
    }


    let contextValue = {Address, setAddress, CardHolder, setCardHolder, CardNumber, setCardNumber, CardType, setCardType, Exp, setExp, Ccv, setCcv, Total, setTotal, Time, setTime, send};
    return (
        <PaymentContext.Provider value={contextValue}>{props.children}</PaymentContext.Provider>
    )
}


export const CartContextProvider = (props) => {
    const [cartItems, setCartItems] = useState({});

    const clearCart = ()=> {
        setCartItems({});
    }

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

    let contextValue = {cartItems, addToCart, removeFromCart, clearCart};
    return (
        <CartContext.Provider value={contextValue}>{props.children}</CartContext.Provider>
    )
}

export const UserContextProvider = (props) => {
    const [curUser, setUser] = useState("");
    // const [errorState, setError] = useState("");
    // // let foundUser;
    // // let errorMsg;

    // // const geUser = (usernameVal, passwordVal) => {
    // //     fetch('http://localhost:3000/users?username=${usernameVal}?password=${passwordVal}')
    // //         .then(response => response.json())
    // //         .then(json => foundUser = json)
    // //         .catch(error => errorMsg = error);
    // // }

    // const login = (usernameVal, passwordVal) => {
    //     useEffect(() => { // make api call and parse as json
    //         fetch('http://localhost:3000/users?username=${usernameVal}?password=${passwordVal}')
    //         .then(response => response.json())
    //         .then(json => setUser(json.username))
    //         .catch(error => setError(error));
    //     }, []);
    // }
    

    let contextValue = {curUser, setUser};
    return (
        <UserContext.Provider value={contextValue}>{props.children}</UserContext.Provider>
    )
}

export const InventoryContextProvider = (props) => {
    const [products, setProducts] = useState([]);
    useEffect(() => { // make api call and parse as json
        fetch('http://localhost:3000/furniture')
        .then(response => response.json())
        .then(json => {
            console.log(json)
            setProducts(json)
        })
        .catch(error => console.error(error));
    }, []);
    let contextValue = {products};
    return (
        <InventoryContext.Provider value={contextValue}>{props.children}</InventoryContext.Provider>
    )
}
