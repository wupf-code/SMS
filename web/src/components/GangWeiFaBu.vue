<template>
  <form class="row g-3" @submit.prevent="add">
    <div class="col-md-6">
      <label for="inputEmail4" class="form-label">工作性质</label>
      <input v-model="xingzhi" type="text" class="form-control" id="inputEmail4">
    </div>
    <div class="col-md-6">
      <label for="inputPassword4" class="form-label">工作地点</label>
      <input v-model="didian" type="text" class="form-control" id="inputPassword4">
    </div>
    <div class="col-6">
      <label for="inputAddress" class="form-label">工作内容</label>
      <input v-model="neirong" type="text" class="form-control" id="inputAddress" >
    </div>
    <div class="col-6">
      <label for="inputAddress2" class="form-label">酬金</label>
      <input type="text" v-model="choujin" class="form-control" id="inputAddress2" >
    </div>
    <div class="row justify-content-center" >
    <div class="col-3">
      <button style="width: 100%;margin-top: 10px" type="submit" class="btn btn-primary">提交</button>
    </div>
    </div>
  </form>
</template>

<script>
import {ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";
import router from "@/router";

export default {
  name: "GangWeiFaBu",
  setup(){
    const store = useStore();
    let xingzhi = ref("");
    let didian = ref("");
    let neirong = ref("");
    let choujin = ref("");
    const add = () =>{
      $.ajax({
        url: "http://127.0.0.1:3000/teachermanager/gangwei/add/",
        type: 'post',
        data:{
          xingzhi:xingzhi.value,
          didian:didian.value,
          neirong:neirong.value,
          choujin:choujin.value,
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
      xingzhi,
      didian,
      neirong,
      choujin,
      add,
    }
  }
}
</script>

<style scoped>

</style>