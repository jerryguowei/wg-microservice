import { userConstants } from '../constants';

function authReducer(state={loggedIn: false, loggingIn: false, user: null}, action) {
    switch(action.type) {
        case userConstants.LOGIN_REQUEST:
            return {...state, loggingIn:true};
        case userConstants.LOGIN_SUCCESS:
            return {
                ... state,
                loggedIn: true,
                loggingIn: false,
                user: action.user
            };
        case userConstants.LOGIN_FAILURE:
            return {...state, loggedIn: false, loggingIn: false, user:null};
        case userConstants.LOGOUT:
            return {...state, loggedIn: false, loggingIn: false, user:null};
        default:
            return state;

    }
} 

export default authReducer;