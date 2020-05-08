import { userConstants } from "../constants"
import { userServices } from "../services"


export const userActions = {
    login,
    logout,
    refreshToken
}

function login(username, password) {
    return async dispatch => {
        dispatch(requestLogin({username}))
        try{
            let loginData = await userServices.login(username, password);
           dispatch(successFetchToken(loginData[0]));
           dispatch(successLogin(loginData[1]));
        } catch (error) {
            const errorData = error || error.response || error.response.data;
           dispatch(failureLogin(errorData));
        }
    }

}

function logout() {
    userServices.logout();
    return {type: userConstants.LOGOUT};
}

function refreshToken(refreshToken) {
    return async dispatch => {
        let tokenInfo = await userServices.refreshToken(refreshToken);
        dispatch(successFetchToken(tokenInfo));
    }
}


function requestLogin(user){
    return {type: userConstants.LOGIN_REQUEST, user}
}

function successFetchToken(token) {
    return {type: userConstants.TOKEN_SUCCESS, token};
}

function successLogin(user){
    return {type: userConstants.LOGIN_SUCCESS, user};
}

function failureLogin(error) {
    return {type: userConstants.LOGIN_FAILURE, error};
}










