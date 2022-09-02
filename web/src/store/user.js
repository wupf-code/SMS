import $ from 'jquery';
export default {
    state: {
        id:"",
        username:"",
        token:"",
        is_login:false,
        identify:"",
        pulling_info:true,
    },
    getters: {
    },
    mutations: {
        updateUser(state, user){
            state.id = user.id;
            state.username = user.username;
            state.identify = user.identify;
            state.is_login = user.is_login;

        },
        updateToken(state, token){
            state.token = token;
        },
        logout(state){
            state.id="";
            state.username="";
            state.token="";
            state.identify="";
            state.is_login=false;
        },
        updatePullingInfo(state,pulling_info){
            state.pulling_info = pulling_info;
        },
    },
    actions: {
        login(context, data) {
            $.ajax({
                url:"http://127.0.0.1:3000/user/account/token/",
                type:"post",
                data: {
                    username:data.username,
                    password:data.password,
                },
                success(resp){
                    if(resp.error_message ==="success"){
                        localStorage.setItem("jwt_token",resp.token);
                        context.commit("updateToken",resp.token);
                        data.success(resp);
                    }else {
                        data.error(resp);
                    }

                },
                error(resp){
                    data.error(resp);
                }
            })
        },
        getinfo(context, data) {
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateUser", {
                            ...resp,
                            is_login: true,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    console.log(resp);
                    data.error(resp);
                }
            })
        },

        logout(context){
            localStorage.removeItem("jwt_token");
            context.commit("logout");
        }
    },
    modules: {

    }
}