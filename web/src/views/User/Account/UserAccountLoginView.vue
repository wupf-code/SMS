<template>
<!--  <ContentFiled>-->
    <div class="row justify-content-center" v-if = "!$store.state.user.pulling_info">
      <div class="col-3">
        <form style="margin-top: 2vh" @submit.prevent="login">
          <ContentFiled>
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">输入密码</label>
            <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
            <div class = "error-message">{{error_message}}</div>
          <button type="submit" class="btn btn-primary btn-group-justified">登录</button>
        </ContentFiled>
        </form>

      </div>
    </div>

</template>

<script>
import {useStore} from "vuex";
import {ref} from "vue";
import ContentFiled from "@/components/ContentField";
import router from "@/router";
export default {
  name: "UserAccountLoginView",
  components:{
    ContentFiled

  },
  setup(){
    const  store = useStore();
    let username = ref("");
    let password = ref("");
    let error_message = ref("");
    const jwt_token = localStorage.getItem("jwt_token");

    if(jwt_token){
      store.commit("updateToken",jwt_token);
      store.dispatch("getinfo",{
        success() {
          store.commit("updatePullingInfo",false);
          if(store.state.user.identify === "student")
          {
            router.push({ name: 'home' });
            store.dispatch("getStudentInfo",{
              success(resp){
                console.log(resp);
              },
              error(resp){
                console.log(resp);
              }
            })
          }

          if(store.state.user.identify==="teacher")
            router.push({name: 'teacher_manager'})

        },
        error(){
          store.commit("updatePullingInfo",false);
        }

      })
    }else{
      store.commit("updatePullingInfo",false);
    }
    const login = () => {
      error_message.value = "";
      store.dispatch("login", {
        username: username.value,
        password: password.value,
        success() {
          store.dispatch("getinfo", {
            success() {
              if(store.state.user.identify === "student")
                router.push({ name: 'home' });
              if(store.state.user.identify==="teacher")
                router.push({name: 'teacher_manager'})
            }
          })
        },
        error() {
          error_message.value = "用户名或密码错误";
        }
      })
    }

    return{
      username,
      password,
      error_message,
      login,
    }

  }
}
</script>

<style scoped>
button{
  width: 100%;
}
.error-message{
  color: red;
}
</style>