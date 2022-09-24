<template>
<ContentFiled>
  <form class="row g-3" @submit.prevent = "addLog">
    <div class="col-md-6">
      <label for="username" class="form-label">姓名</label>
      <input v-model="username" type="text" class="form-control" id="username">
    </div>
    <div class="col-md-6">
      <label for="inputPassword4" class="form-label">所属区队</label>
      <input type="text" v-model="qudui" class="form-control" id="inputPassword4">
    </div>
    <div class="col-6">
      <label for="inputAddress" class="form-label">填写日期</label>
      <input v-model="date" type="datetime-local" class="form-control" id="inputAddress" placeholder="2022-10-12">
    </div>
    <div class="col-6">
      <label for="inputAddress2" class="form-label">工作类型</label>
      <input type="text" v-model="leixing" class="form-control" id="inputAddress2" placeholder="谈心谈话">
    </div>
    <div class="col-md-12">
      <label for="inputCity" class="form-label">具体内容</label>
      <textarea type="text" v-model="neirong" class="form-control" id="inputCity">

      </textarea>
    </div>
    <div class="row justify-content-center">
    <div class="col-3 align-items-center" >
      <button style="width: 100%;margin-top: 10px" type="submit" class="btn btn-primary">Sign in</button>
    </div>
  </div>
  </form>
</ContentFiled>
</template>

<script>
import ContentFiled from "@/components/ContentField";
import {useStore} from "vuex";
import {ref} from "vue";
import $ from "jquery";
import router from "@/router";
export default {
  name: "TeacherLog",
  components: {ContentFiled},
  setup(){
      const store = useStore();
      let username = ref(store.state.user.username);
      let qudui = ref("");
      let date = ref(null);
      let leixing = ref("谈心谈话");
      let neirong = ref("");
      const addLog = () =>{
        $.ajax({
          url: "http://127.0.0.1:3000/teachermanager/teacher/addlog/",
          type: 'post',
          data:{
            username:username.value,
            qudui:qudui.value,
            date:date.value,
            leixing:leixing.value,
            neirong:neirong.value
          },
          headers: {
            Authorization: "Bearer " + store.state.user.token,
          },
          success(resp) {
            if(resp.error_message === "success")
            router.push({name : "teacher_manager"});
            store.commit("updateActiveId",-1);
            console.log(-1);
          },
          error(){
          }
        });
    }
      return {
        username,
        qudui,
        date,
        leixing,
        neirong,
        addLog,
      }
  }
}
</script>

<style scoped>

</style>