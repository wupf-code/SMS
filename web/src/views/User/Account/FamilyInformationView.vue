<template>
  <div class="container content-field">
    <div class="row justify-content-center">
      <div class="col-9">
        <div class="card" style="margin-top: 20px">
          <div class="card-header">
            <span style="font-size: 130%">我的家庭信息</span>
            <button class="btn btn-primary float-end" data-bs-toggle="modal" data-bs-target="#add-bot-button">
              添加信息
            </button>

            <!-- Modal -->
            <div class="modal fade" id="add-bot-button" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">添加成员</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body">
                    <div class="row g-3">
                      <div class="col-md-4">
                        <label for="username" class="form-label">姓名</label>
                        <input v-model="member_add.username" type="text" class="form-control" id="username">
                      </div>
                      <div class="col-md-4">
                        <label for="sex" class="form-label">性别</label>
                        <input v-model="member_add.sex" type="text" class="form-control" id="sex">
                      </div>
                      <div class="col-md-4">
                        <label for="relation" class="form-label">关系</label>
                        <input v-model="member_add.relation" type="text" class="form-control" id="relation">
                      </div>
                      <div class="col-md-3">
                        <label for="age" class="form-label">年龄</label>
                        <input v-model="member_add.age" type="text" class="form-control" id="age">
                      </div>
                      <div class="col-md-3">
                        <label for="occupation" class="form-label">职业</label>
                        <input v-model="member_add.occupation" type="text" class="form-control" id="occupation">
                      </div>
                      <div class="col-md-3">
                        <label for="inputState" class="form-label">政治面貌</label>
                        <select v-model="member_add.state" id="inputState" class="form-select">
                          <option value="群众" selected>群众</option>
                          <option value="团员">团员</option>
                          <option value="党员">党员</option>
                        </select>
                      </div>
                      <div class="col-md-3">
                        <label for="xueli" class="form-label">学历</label>
                        <select v-model="member_add.xueli" id="xueli" class="form-select">
                          <option value="小学" selected>小学</option>
                          <option value="中学">中学</option>
                          <option value="专科">专科</option>
                          <option value="本科">本科</option>
                          <option value="硕士研究生">硕士研究生</option>
                          <option value="博士研究生">博士研究生</option>
                        </select>
                      </div>
                      <div class="col-md-6">
                        <label for="weixin" class="form-label">微信</label>
                        <input v-model="member_add.weixin" type="text" class="form-control" id="weixin">
                      </div>
                      <div class="col-md-6">
                      <label for="email" class="form-label">邮箱</label>
                      <input v-model="member_add.email" type="email" class="form-control" id="email">
                      </div>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="add">保存</button>
                  </div>
                </div>
              </div>
            </div>


          </div>
          <div class="card-body">
            <table class="table table-hover">
              <thead>
              <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>关系</th>
                <th>职业</th>
                <th>政治面貌</th>
                <th>学历</th>
                <th>微信</th>
                <th>邮箱</th>
              </tr>
              </thead>
              <tbody>
              <tr v-for="bot in member_list" :key="bot.id">
                <td>{{bot.username}}</td>
                <td>{{bot.sex}}</td>
                <td>{{bot.age}}</td>
                <td>{{bot.relation}}</td>
                <td>{{bot.occupation}}</td>
                <td>{{bot.state}}</td>
                <td>{{bot.xueli}}</td>
                <td>{{bot.weixin}}</td>
                <td>{{bot.email}}</td>

              </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import {reactive,ref} from "vue";
import $ from 'jquery';
import {useStore} from "vuex";
import {Modal} from 'bootstrap/dist/js/bootstrap';
export default {
  name: "FamilyInformationView",
  components: {},
  setup(){
    const store = useStore();
    const member_add = reactive({
      username: "",
      age: "",
      sex: "",
      relation: "",
      occupation:"",
      state:"群众",
      xueli:"小学",
      weixin:"",
      email:"",
    });
    let member_list = ref("");
    const refresh = ()=>{
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/family/getinfo/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp)
          member_list.value = resp;
        }
      })
    }
    const add = ()=>{
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/family/add/",
        type: "post",
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data:{
          username: member_add.username,
          age: member_add.age,
          sex: member_add.sex,
          relation: member_add.relation,
          occupation:member_add.occupation,
          state:member_add.state,
          xueli:member_add.xueli,
          weixin:member_add.weixin,
          email:member_add.email,
        },
        success(resp) {
          if (resp.error_message === "success") {
            member_add.username="";
                member_add.age= "";
                member_add.sex= "";
                member_add.relation= "";
                member_add.occupation="";
                member_add.state="群众";
                member_add.xueli="小学";
                member_add.weixin="";
                member_add.email="";
                Modal.getInstance("#add-bot-button").hide();
                refresh();
          } else {
            console.log(resp);
          }
        },
        error(resp) {
          console.log(resp);
        }
      })
    }
    refresh()
    return {
      member_add,
      member_list,
      add,
    }
  }
}
</script>

<style scoped>

</style>