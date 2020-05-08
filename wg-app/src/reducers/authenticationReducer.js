import { userConstants } from '../constants';

function authReducer(state={loggedIn: false, loggingIn: false, user: null, tokenInfo: null}, action) {
    switch(action.type) {
        case userConstants.LOGIN_REQUEST:
            return {...state, loggingIn:true};
        case userConstants.LOGIN_SUCCESS:
            return {
                ...state,
                loggedIn: true,
                loggingIn: false,
                user: action.user
            };
        case userConstants.TOKEN_SUCCESS:
            return {
                ...state,
                tokenInfo: action.token
            }
        case userConstants.LOGIN_FAILURE:
            return {...state, loggedIn: false, loggingIn: false, user:null, tokenInfo: null};
        case userConstants.LOGOUT:
            return {...state, loggedIn: false, loggingIn: false, user:null, tokenInfo: null};
        default:
            return state;
    }
} 
export default authReducer;