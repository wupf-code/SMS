<template>
<ContentFiled>
  <div style="color: red">{{message}}</div>
  <form v-if ="poor_students.level!=='0'" id ="poor-student" class="row g-lg-4" @submit.prevent="update_bursaries_student" >
    <div class="col-3">
      <label for="inputUsername" class="form-label">姓名</label>
      <input disabled type="text" :placeholder="poor_students.username" class="form-control" id="inputUsername">
    </div>
    <div class="col-3">
      <label for="inputSex" class="form-label">性别</label>
      <input disabled v-model="poor_students.sex" type="text" class="form-control" id="inputSex">
    </div>
    <div class="col-6">
      <label for="inputIphoneNumber" class="form-label">手机号</label>
      <input disabled v-model="poor_students.phone_number" type="text" class="form-control" id="inputIphoneNumber"
             placeholder="请输入手机号">
    </div>
    <div class="col-3">
      <label for="school" class="form-label">学校</label>
      <input disabled v-model="poor_students.school" type="text" class="form-control" id="inputUsername">
    </div>
    <div class="col-3">
      <label for="department" class="form-label">院系</label>
      <input disabled v-model="poor_students.department" type="text" class="form-control" id="inputSex">
    </div>
    <div class="col-3">
      <label for="dadui" class="form-label">大队</label>
      <input disabled v-model="poor_students.dadui" type="text" class="form-control" id="inputSex">
    </div>
    <div class="col-3">
      <label for="speciality" class="form-label">专业</label>
      <input disabled v-model="poor_students.speciality" type="text" class="form-control" id="inputSex">
    </div>
    <div class="col-3">
      <label for="inputIdCard" class="form-label">身份证号</label>
      <input disabled v-model="poor_students.id_card" type="text" class="form-control" id="inputIdCard"
             placeholder="请输入身份证号">
    </div>
    <div class="col-3">
      <label for="inputBedroom" class="form-label">民族</label>
      <input disabled v-model="poor_students.minzu" type="text" class="form-control" id="inputBedroom"
             placeholder="汉族">
    </div>
    <div class="col-3">
      <label for="inputBirthday" class="form-label">生日</label>
      <input  disabled v-model="poor_students.birthday" type="text" class="form-control" id="inputBirthday"
             placeholder="2000-4-3">
    </div>
    <div class="col-md-3">
      <label for="inputState" class="form-label">政治面貌</label>
      <select disabled v-model="poor_students.political_outlook" id="inputState" class="form-select">
        <option value="群众" selected>群众</option>
        <option value="团员">团员</option>
        <option value="党员">党员</option>
      </select>
    </div>
    <div class="col-12">
      <label for="inputAddress" class="form-label">地址</label>
      <input disabled v-model="poor_students.address" type="text" class="form-control" id="inputAddress">
    </div>
    <div class="col-3">
    <label for="inputAddress" class="form-label">困难认定等级： {{poor_students.level}}</label>
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
      <tr v-for="bot in poor_students.family" :key="bot.id">
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
import {reactive, ref} from "vue";
import router from "@/router";
export default {
  name: "BursariesView",
  components: {ContentFiled},
  setup(){
    let message = ref("");
    let reason = ref("");
    const store = useStore();
    let member_list = ref("");
    let state = ref("未提交");
    let poor_students = reactive({
      username:"",
      sex:"",
      phone_number:"",
      school:"",
      department:"",
      dadui:"",
      speciality:"",
      id_card:"",
      minzu:"",
      birthday:"",
      political_outlook:"",
      address:"",
      family:null,
      shenhe:"",
      level : "",
    });

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
      uri:"http://127.0.0.1:3000/studentmanager/kunnanstudent/getinfo/",
      type:"get",
      header:{
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp){
        state.value = resp.state;
        reason = resp.reason;
      }
    })
    $.ajax({
      url: "http://127.0.0.1:3000/studentmanager/poorstudent/getinfo/",
      type: 'get',
      headers: {
        Authorization: "Bearer " + store.state.user.token,
      },
      success(resp) {
        console.log(resp);
        if(resp.error_message ==="success"){
          poor_students.username=resp.username;
          poor_students.sex=resp.sex;
          poor_students.phone_number=resp.phone_number;
          poor_students.school= resp.school;
          poor_students.department  = resp.department;
          poor_students.dadui= resp.dadui;
          poor_students.speciality= resp.speciality;
          poor_students.id_card= resp.id_card;
          poor_students.minzu= resp.minzu;
          poor_students.birthday= resp.birthday;
          poor_students.political_outlook= resp.political_outlook;
          poor_students.address= resp.address;
          poor_students.family= member_list;
          poor_students.shenhe= resp.shenhe;
          poor_students.level = resp.level;
          if(resp.level === '0')message.value = "未通过困难生认定，不能申请国家助学金";
        }else{
          message.value = "未通过困难生认定，不能申请国家助学金";
        }

      },
      error(){
        message = "未通过困难生认定，不能申请国家助学金";
      }
    })
    const update_bursaries_student = () =>{
        $.ajax({
          url: "http://127.0.0.1:3000/studentmanager/kunnanstudent/add/",
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
              state.value="待审核";
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
      poor_students,
      reason,
      message,
      state,
      update_bursaries_student,
    }
  }
}
</script>

<style scoped>

</style>