import React from 'react';
import {  testLogin } from '../services/userService';

const Test = () => {

    return (<div onClick={()=>testLogin("davidgao","password")}>
        Hello
    </div>)


}

export default Test;