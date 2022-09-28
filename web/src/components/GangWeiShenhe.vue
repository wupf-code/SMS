<template>
<ContentFiled>
  <table class="table table-hover">
    <thead>
    <tr>
      <th>
        姓名
      </th>
      <th>
        岗位
      </th>
      <th>状态</th>
      <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <tr v-for="(bot,index) in member_list " :key="index">
        <td>{{bot.user.username}}</td>
        <td>{{bot.gangwei.neirong}}</td>
        <td>{{bot.state}}</td>
        <td>
          <button @click="update_state(bot)" v-if="bot.state === '未审核'">通过</button>
          <button @click="update_state(bot)" v-else-if="bot.state ==='已通过'">取消</button>
        </td>
    </tr>
    </tbody>
  </table>
</ContentFiled>
</template>

<script>
import ContentFiled from "@/components/ContentField";
import {useStore} from "vuex";
import $ from "jquery";
import {ref} from "vue";
export default {
  name: "GangWeiShenhe",
  components: {ContentFiled},
  setup(){
    const store = useStore();
    let member_list = ref("");
    const refresh = ()=>{
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/gangwei/getinfoall/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
            member_list.value = resp;
            console.log(resp);
        },
        error(){
          console.log(123)
        }
      });
    }
    const update_state = (bot)=>{
      let state = "";
      if(bot.state === "未审核"){
         state = "已通过";
      }else {
        state = "未审核";
      }
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/gangwei/updatestate/",
        type: 'post',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data:{
          id:bot.id,
          state:state,
        },
        success() {
          refresh();
        },
        error(){
          console.log(123)
        }
      });
    }
    refresh();
    return {
      member_list,
      update_state,
    }
  }
}
</script>

<style scoped>

</style>