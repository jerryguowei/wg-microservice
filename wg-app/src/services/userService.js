import { userApi } from "../apis/userApi";

 export async function login(username, password) {
    var bodyFormData = new FormData();
    bodyFormData.set("username", username);
    bodyFormData.set("password", password);
    bodyFormData.set("grant_type", "password");
    let tokenResponse = await userApi.userAxios({
        method: 'post',
        url: '/api-uaa/oauth/token',
        data: bodyFormData,
        auth: {
            username: "front-end",
            password: "password"
        }
    });

    let data = tokenResponse.data || {};
    localStorage.setItem("token_info", JSON.stringify(data));

    const accessToken = data.access_token;
    
    let userResponse = await userApi.userAxios({
        method: 'get',
        url: '/user/users/current', 
        headers: {
            Authorization: "Bearer " + accessToken
        }
    });
    data = userResponse.data;
    localStorage.setItem("user_info", JSON.stringify(data));
    return Promise.all([tokenResponse.data, userResponse.data])
}


export function testLogin(username, password) {

    let promise = login(username, password);
    promise.then((d1)=>{
        console.log(d1);
    }).catch(e1=>{
        console.log(e1.response.data);
    })

}
