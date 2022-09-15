// import $ from 'jquery';
import $ from "jquery";
import user from "@/store/user";
export default {
    state: {
        username:"",
        sex:"",
        phone_number:"",
         id_card:"",
        bedroom:"",
        birthday:null,
        political_outlook:"",
         address:"",

        active_id:-1,
    },
    getters: {
    },
    mutations: {
        updateStudent(state, student){
            state.username=student.username;
                state.sex=student.sex;
                state.phone_number=student.phone_number;
                state.id_card=student.id_card;
                state.bedroom=student.bedroom;
                state.birthday=student.birthday;
                state.political_outlook=student.political_outlook;
                state.address=student.address;
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
