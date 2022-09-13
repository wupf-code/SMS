// import $ from 'jquery';
import $ from "jquery";
import user from "@/store/user";
export default {
    state: {
        // username:"",
        // sex:"",
        // phone_number:"",
        // id_card:"",
        // bedroom:"",
        // birthday:null,
        // political_outlook:"",
        // address:"",
        student:null,
        active_id:-1,
    },
    getters: {
    },
    mutations: {
        updateStudent(state, student){
            state.student = student;
        },
        updateActiveId(state, id){
            state.active_id = id;
        },


    },
    actions: {
        getStudentInfo(context, data){
            console.log(user)
            $.ajax({
                url: "http://127.0.0.1:3000/user/account/studentinfo/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + user.state.token,
                },
                success(resp) {
                    if (resp.error_message === "success") {
                        context.commit("updateStudent", {
                            ...resp,
                        });
                        data.success(resp);
                    } else {
                        data.error(resp);
                    }
                },
                error(resp) {
                    data.error(resp);
                }
            })
        }

    },
    modules: {

    }
}
