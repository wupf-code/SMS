<template>
<ContentFiled>
  <form class="row g-lg-4" @submit.prevent="update_student">
    <div class="col-3">
      <label for="inputUsername" class="form-label">姓名</label>
      <input v-model="username" type="text" class="form-control" id="inputUsername">
    </div>
    <div class="col-3">
      <label for="inputSex" class="form-label">性别</label>
      <input v-model="sex" type="text" class="form-control" id="inputSex">
    </div>
    <div class="col-6">
      <label for="inputIphoneNumber" class="form-label">手机号</label>
      <input v-model="phone_number" type="text" class="form-control" id="inputIphoneNumber" placeholder="请输入手机号">
    </div>
    <div class="col-3">
      <label for="inputIdCard" class="form-label">身份证号</label>
      <input v-model="id_card" type="text" class="form-control" id="inputIdCard" placeholder="请输入身份证号">
    </div>
    <div class="col-3">
      <label for="inputBedroom" class="form-label">寝室</label>
      <input v-model="bedroom" type="text" class="form-control" id="inputBedroom" placeholder="请输入寝室号">
    </div>
    <div class="col-3">
      <label for="inputBirthday" class="form-label">生日</label>
      <input v-model="birthday" type="text" class="form-control" id="inputBirthday" placeholder="2000-4-3">
    </div>
    <div class="col-md-3">
      <label for="inputState" class="form-label">政治面貌</label>
      <select v-model="political_outlook" id="inputState" class="form-select">
        <option value="群众" selected>群众</option>
        <option value="团员">团员</option>
        <option value="党员">党员</option>
      </select>
    </div>
    <div class="col-12">
      <label for="inputAddress" class="form-label">地址</label>
      <input v-model="address" type="text" class="form-control" id="inputAddress">
    </div>
    <div class="col-3" style="margin: 0 auto">
      <button type="submit" class="btn btn-primary ">提交修改</button>
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
  name: "PersonalInformationView",
  components: {ContentFiled},
  setup(){
    const store = useStore();
    let username = ref("");
    let sex = ref("");
    let phone_number =ref("");
    let id_card = ref("");
    let birthday= ref("");
    let address = ref("");
    let political_outlook = ref("群众");
    let bedroom = ref("");
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
      })
    const update_student = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/updatastudentinformation/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data: {
          username: username.value,
          sex:sex.value,
          phone_number: phone_number.value,
          id_card: id_card.value ,
          birthday:birthday.value ,
          address: address.value ,
          political_outlook: political_outlook.value,
          bedroom: bedroom.value ,
        },
        success(resp) {
          console.log(resp);
          if (resp.error_message === "success") {
            router.push({name: "home"});
          } else {
            console.log(resp);
          }

        },
        error(resp) {
          console.log(resp);
        }
      });
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
      update_student,
    }
  }
}
</script>

<style scoped>
button{
  width: 100%;
  margin-top: 2vh;
}
</style>