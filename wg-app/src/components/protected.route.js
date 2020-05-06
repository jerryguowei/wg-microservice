import {React} from 'react';
import { Route } from 'react-router-dom';

export const ProtectedRoute = ({children, ...rest}) => {

    return (
        <Route {...rest}  
            render={
                props => {
                   return (children)
                }
            }
        />
    );
}