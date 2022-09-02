<template>
  <div class="row justify-content-center">
    <div class="col-3">
      <form @submit.prevent="register" style="margin-top: 2vh">
        <ContentFiled>
          <div class="mb-3">
            <label for="username" class="form-label">用户名</label>
            <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">输入密码</label>
            <input  v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
          </div>
          <div class="mb-3">
            <label for="confirmed_password" class="form-label">确认密码</label>
            <input v-model="confirmedPassword" type="password" class="form-control" id="confirmed_password" placeholder="请确认输入密码">
          </div>
          <div class="mb-3">
            <label for="identify" class="form-label" >选择身份</label>
            <select v-model="identify" class="form-select" aria-label="Default select example">
              <option value="student" selected>学生</option>
              <option value="teacher">指导员</option>

            </select>
          </div>
          <div class = "error-message">{{error_message}}</div>
          <button  type="submit" class="btn btn-primary">注册</button>
        </ContentFiled>
      </form>

    </div>
  </div>
</template>

<script>
import ContentFiled from "@/components/ContentField";
import $ from 'jquery';
import {ref} from "vue";
// import {useStore} from "vuex";
import router from "@/router";
export default {
  name: "UserRegisterView",
  components:{
    ContentFiled,

  },
  setup() {
    // const store = useStore();
    let username = ref("");
    let password = ref("");
    let confirmedPassword = ref("");
    let error_message = ref("");
    let identify = ref("student");
    const register = () => {
      error_message.value="";
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/register/",
        type: "post",
        data: {
          username: username.value,
          password: password.value,
          confirmedPassword: confirmedPassword.value,
          identify: identify.value,
        },
        success(resp) {
          console.log(resp);
          if (resp.error_message === "success") {
            router.push({name: "login"});
          } else {
            error_message.value = resp.error_message;
          }

        },
        error(resp) {
          console.log(resp);
        }
      });

    }
    return {
      username,
      password,
      confirmedPassword,
      error_message,
      identify,
      register,
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