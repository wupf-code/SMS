<template>
  <ContentFiled  style="overflow:scroll;width: 100%; height:80vh;">
    <form id ="poor-student" class="row g-lg-4" @submit.prevent="update_poor_student" >
      <div class="col-3">
        <label for="inputUsername" class="form-label">姓名</label>
        <input v-model="poor_students.username" type="text" class="form-control" id="inputUsername">
      </div>
      <div class="col-3">
        <label for="inputSex" class="form-label">性别</label>
        <input v-model="poor_students.sex" type="text" class="form-control" id="inputSex">
      </div>
      <div class="col-6">
        <label for="inputIphoneNumber" class="form-label">手机号</label>
        <input v-model="poor_students.phone_number" type="text" class="form-control" id="inputIphoneNumber"
               placeholder="请输入手机号">
      </div>
      <div class="col-3">
        <label for="school" class="form-label">学校</label>
        <input v-model="poor_students.school" type="text" class="form-control" id="inputUsername">
      </div>
      <div class="col-3">
        <label for="department" class="form-label">院系</label>
        <input v-model="poor_students.department" type="text" class="form-control" id="inputSex">
      </div>
      <div class="col-3">
        <label for="dadui" class="form-label">大队</label>
        <input v-model="poor_students.dadui" type="text" class="form-control" id="inputSex">
      </div>
      <div class="col-3">
        <label for="speciality" class="form-label">专业</label>
        <input v-model="poor_students.speciality" type="text" class="form-control" id="inputSex">
      </div>
      <div class="col-3">
        <label for="inputIdCard" class="form-label">身份证号</label>
        <input v-model="poor_students.id_card" type="text" class="form-control" id="inputIdCard"
               placeholder="请输入身份证号">
      </div>
      <div class="col-3">
        <label for="inputBedroom" class="form-label">民族</label>
        <input v-model="poor_students.minzu" type="text" class="form-control" id="inputBedroom"
               placeholder="汉族">
      </div>
      <div class="col-3">
        <label for="inputBirthday" class="form-label">生日</label>
        <input v-model="poor_students.birthday" type="text" class="form-control" id="inputBirthday"
               placeholder="2000-4-3">
      </div>
      <div class="col-md-3">
        <label for="inputState" class="form-label">政治面貌</label>
        <select v-model="poor_students.political_outlook" id="inputState" class="form-select">
          <option value="群众" selected>群众</option>
          <option value="团员">团员</option>
          <option value="党员">党员</option>
        </select>
      </div>
      <div class="col-12">
        <label for="inputAddress" class="form-label">地址</label>
        <input v-model="poor_students.address" type="text" class="form-control" id="inputAddress">
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
          <td> <input v-model="bot.danwei" type="text" class="form-control" id="danwei"> </td>
          <td><input v-model="bot.salary" type="text" class="form-control" id="salary"></td>
          <td><input v-model="bot.health" type="text" class="form-control" id="health"></td>
        </tr>
        </tbody>
      </table>
      <div class="col-3" style="margin: 0 auto">
        <button type="submit" class="btn btn-primary ">提交修改</button>
      </div>
    </form>
  </ContentFiled>
</template>

<script>
import {useStore} from "vuex";
import {reactive, ref} from "vue";
import ContentFiled from "@/components/ContentField";
import $ from "jquery";

export default {
  name: "PoorStudentView",
  components: {ContentFiled},
  setup() {
    const store = useStore();
    let studentinfo = reactive(store.state.student.student);
    // let school = ref("东南大学成贤学院");
    // let department = ref("电子与计算机工程学院");
    // let dadui = ref("dadui");
    // let speciality = ref("软件工程");
    // let danwei = ref("hh");
    // let salary = ref("10000");
    // let health = ref("健康");
    let member_list = ref("");
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
    let poor_students = reactive({
      ...studentinfo,
      family: member_list,
      minzu:"汉族",
      school :"东南大学成贤学院",
      department:"电子与计算机工程学院",
      dadui:"dadui",
      speciality:"软件工程",
    })
    console.log(poor_students);
    const update_poor_student = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/poorstudent/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data:{
          ...poor_students,
        },
        success(resp) {
          if (resp.error_message === "success") {
              console.log(resp);
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
      studentinfo,
      member_list,
      // school,
      // dadui,
      // department,
      // speciality,
      // danwei,
      // health,
      // salary,
      update_poor_student,
      poor_students,
    }
  }
}
</script>

<style scoped>

</style>