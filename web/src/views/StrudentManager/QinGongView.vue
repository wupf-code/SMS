<template>
  <ContentFiled>
  <table v-if="identy === true" class="table table-hover">
    <thead>
    <tr>
      <th>
        工作性质
      </th>
      <th>
        工作地点
      </th>
      <th>工作内容</th>
      <th>酬金</th>
    </tr>
    </thead>

    <tbody>
    <tr v-for="bot in member_list" :key="bot.id">
      <td>{{ bot.xingzhi }}</td>
      <td>{{ bot.didian }}</td>
      <td>{{ bot.neirong }}</td>
      <td>{{ bot.choujin }}</td>
      <td>
        <button v-if="(bot.id in shenqing_list)">{{shenqing_list[bot.id]}}</button>
        <button @click="add(bot)" v-else>申请</button>
      </td>

    </tr>
    </tbody>
  </table>
  <div v-else>你不是困难生</div>
  </ContentFiled>
</template>

<script>
import {ref} from "vue";
import $ from "jquery";
import {useStore} from "vuex";
import ContentFiled from "@/components/ContentField";

export default {
  name: "QinGongView",
  components: {ContentFiled},
  setup(){
    const store = useStore();
    let identy = ref(false);
    let shenqing_list = ref("");
    let member_list = ref("");
    const get_gangwei_list = () =>{
      $.ajax({
        url: "http://127.0.0.1:3000/teachermanager/gangwei/getall/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          member_list.value = resp;
          console.log(resp);
        },
        error(){
        }
      });
    }
    const refresh = ()=>{
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/gangwei/getall/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          shenqing_list.value = resp;
          console.log(resp);
        },
        error(){
        }
      });
    }
    refresh();
    get_gangwei_list()
    $.ajax({
      url: "http://127.0.0.1:3000/studentmanager/poorstudent/getinfo/",
      type: 'get',
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        if(resp.error_message === "success" && resp.shenhe==="已通过"){
          identy.value = true;

        }
        else {
          identy.value = false;
        }
      },
      error(){
      }
    });


    const add = (mem)=>{
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/gangwei/add/",
        type: 'post',
        data:{
          gangwei_id : mem.id,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success() {
          refresh();
          // get_gangwei_list();
        },
        error(){
        }
      });
    }

    return {
      identy,
      member_list,
      add,
      shenqing_list,
    }
  }
}
</script>

<style scoped>

</style>