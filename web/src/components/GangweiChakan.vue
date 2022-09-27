<template>
  <table class="table table-hover">
    <thead>
    <tr>
      <th>
        工作性质
      </th>
      <th>
        工作地点
      </th>
      <th>工作内容</th>
      <th>工作酬金</th>
    </tr>
    </thead>

    <tbody>
    <tr v-for="bot in member_list" :key="bot.id">
      <td>{{ bot.xingzhi }}</td>
      <td>{{ bot.didian }}</td>
      <td>{{ bot.neirong }}</td>
      <td>{{ bot.choujin }}</td>
    </tr>
    </tbody>
  </table>
</template>

<script>
import {useStore} from "vuex";
import {ref} from "vue";
import $ from "jquery";

export default {
  name: "GangweiChakan",
  setup(){
    const store = useStore();
    let member_list = ref("");
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
    return {
      member_list,
    }
  }
}
</script>

<style scoped>

</style>