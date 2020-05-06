
import {combineReducers} from 'redux';
import authReducer from './authenticationReducer';

const rootReducer = combineReducers({
    authReducer
});

export default rootReducer;