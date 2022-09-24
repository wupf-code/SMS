<template>
  <ContentFiled>
    <form  id ="poor-student" class="row g-lg-4" @submit.prevent="update_bursaries_student" >
      <div class="col-3">
        <label for="inputUsername" class="form-label">姓名</label>
        <input disabled type="text" :placeholder="username" class="form-control" id="inputUsername">
      </div>
      <div class="col-3">
        <label for="inputSex" class="form-label">性别</label>
        <input disabled v-model="sex" type="text" class="form-control" id="inputSex">
      </div>
      <div class="col-6">
        <label for="inputIphoneNumber" class="form-label">手机号</label>
        <input disabled v-model="phone_number" type="text" class="form-control" id="inputIphoneNumber"
               placeholder="请输入手机号">
      </div>

      <div class="col-3">
        <label for="inputIdCard" class="form-label">身份证号</label>
        <input disabled v-model="id_card" type="text" class="form-control" id="inputIdCard"
               placeholder="请输入身份证号">
      </div>
      <div class="col-3">
        <label for="inputBedroom" class="form-label">寝室</label>
        <input disabled v-model="bedroom" type="text" class="form-control" id="inputBedroom"
               placeholder="汉族">
      </div>
      <div class="col-3">
        <label for="inputBirthday" class="form-label">生日</label>
        <input  disabled v-model="birthday" type="text" class="form-control" id="inputBirthday"
                placeholder="2000-4-3">
      </div>
      <div class="col-md-3">
        <label for="inputState" class="form-label">政治面貌</label>
        <select disabled v-model="political_outlook" id="inputState" class="form-select">
          <option value="群众" selected>群众</option>
          <option value="团员">团员</option>
          <option value="党员">党员</option>
        </select>
      </div>
      <div class="col-12">
        <label for="inputAddress" class="form-label">地址</label>
        <input disabled v-model="address" type="text" class="form-control" id="inputAddress">
      </div>
      <div class="col-12">
        <label for="inputReason" class="form-label">申请原因</label>
        <textarea  v-if="state!=='未提交'"  disabled v-model="reason"  class="form-control" id="inputReason"></textarea>
        <textarea  v-if="state==='未提交'"  v-model="reason"  class="form-control" id="inputReason"></textarea>
      </div>
      <hr >
      <div>家庭信息</div>
      <table class="table table-hover">
        <thead>
        <tr>
          <th>姓名</th>
          <th>性别</th>
          <th>年龄</th>
          <th>关系</th>
          <th>职业</th>
          <th>工作单位</th>
          <th>年收入</th>
          <th>身体状况</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="bot in member_list" :key="bot.id">
          <td>{{ bot.username }}</td>
          <td>{{ bot.sex }}</td>
          <td>{{ bot.age }}</td>
          <td>{{ bot.relation }}</td>
          <td>{{ bot.occupation }}</td>
          <td> <input disabled v-model="bot.danwei" type="text" class="form-control" id="danwei"> </td>
          <td><input disabled v-model="bot.salary" type="text" class="form-control" id="salary"></td>
          <td><input disabled v-model="bot.health" type="text" class="form-control" id="health"></td>
        </tr>
        </tbody>
      </table>
      <div class="col-3" style="margin: 0 auto">
        <div style="color: red">{{state}}</div>
        <button v-if="state==='未提交'" type="submit" class="btn btn-primary ">提交申请</button>
      </div>
    </form>
  </ContentFiled>
</template>

<script>
import ContentFiled from "@/components/ContentField";
import $ from 'jquery';
import {useStore} from "vuex";
import { ref} from "vue";
import router from "@/router";
export default {
  name: "ScholarshipView",
  components: {ContentFiled},
  setup(){
    let message = ref("");
    let reason = ref("");
    const store = useStore();
    let member_list = ref("");
    let state = ref("未提交");
    let username = ref("");
    let sex = ref("");
    let phone_number =ref("");
    let id_card = ref("");
    let birthday= ref("");
    let address = ref("");
    let political_outlook = ref("群众");
    let bedroom = ref("");

    $.ajax({
      url: "http://127.0.0.1:3000/user/account/family/getinfo/",
      type: 'get',
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        member_list.value = resp;
      }
    });
    $.ajax({
      url:"http://127.0.0.1:3000/studentmanager/scholarship/getinfo/",
      type:"get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp){
        if(resp.error_message === "success"){
          state.value = resp.state;
          reason.value = resp.reason;
        }
      }
    })
    $.ajax({
      url: "http://127.0.0.1:3000/user/account/studentinfo/",
      type: "get",
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        if (resp.error_message === "success") {
          store.commit("updateStudent", {
            ...resp,
          });
          username.value = resp.username;
          sex.value = resp.sex;
          phone_number.value = resp.phone_number;
          id_card.value = resp.id_card;
          birthday.value = resp.birthday;
          address.value = resp.address;
          political_outlook.value = resp.political_outlook;
          bedroom.value = resp.bedroom;
          console.log(resp);
        } else {
          console.log(resp);
        }
      },
      error(resp) {
        console.log(resp);
      }
    });
    const update_bursaries_student = () =>{
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/scholarship/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data:{
          reason:reason.value,
        },
        success(resp) {
          if (resp.error_message === "success") {
            console.log(resp);
            state.value="未审核";
            router.push({name: "student_manager"});
          } else {
            console.log(resp);
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    return {
      username,
      sex,
      phone_number,
      id_card,
      bedroom,
      birthday,
      political_outlook,
      address,
      reason,
      message,
      state,
      update_bursaries_student,
      member_list,
    }
  }
}
</script>

<style scoped>

</style>