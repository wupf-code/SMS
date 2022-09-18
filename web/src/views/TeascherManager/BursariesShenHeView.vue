<template>
  <ContentField>
    <table class="table table-hover" >
      <thead>
      <tr>
        <th>姓名</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="kunnan_student in kunnan_students" :key="kunnan_student.id">
        <td>{{kunnan_student.username}}</td>
        <td>{{kunnan_student.state}}</td>
        <td><button type="button" @click="getInfoById(kunnan_student)" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#update-bot-button-'+kunnan_student.id">审核</button>
          <div class="modal fade" :id="'update-bot-button-'+kunnan_student.id" tabindex="-1" >
            <div class="modal-dialog modal-fullscreen">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">助学金审核</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <div>个人信息</div>
                  <table class="table table-hover">
                    <thead>
                    <tr>
                      <th>姓名</th>
                      <th>性别</th>
                      <th>手机号</th>
                      <th>学校</th>
                      <th>院系</th>
                      <th>大队</th>
                      <th>专业</th>
                      <th>身份证号</th>
                      <th>民族</th>
                      <th>生日</th>
                      <th>政治面貌</th>
                      <th>地址</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr >
                      <td>{{ bot.username }}</td>
                      <td>{{ bot.sex }}</td>
                      <td>{{ bot.phoneNumber }}</td>
                      <td>{{ bot.school }}</td>
                      <td>{{ bot.department }}</td>
                      <td> {{bot.dadui}} </td>

                      <td>{{bot.speciality}}</td>
                      <td>{{bot.idNumber}}</td>

                      <td>{{ bot.minzu }}</td>
                      <td> {{bot.birthday}} </td>
                      <td>{{bot.state}}</td>
                      <td>{{bot.address}}</td>
                    </tr>
                    </tbody>
                  </table>
                  <div>申请原因</div>
                  <textarea disabled v-model="kunnan_student.reason"></textarea>
                  <hr>
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
                    <tr v-for="member in member_list" :key="member.id">
                      <td>{{ member.username }}</td>
                      <td>{{ member.sex }}</td>
                      <td>{{ member.age }}</td>
                      <td>{{ member.relation }}</td>
                      <td>{{ member.occupation }}</td>
                      <td> {{member.danwei}} </td>
                      <td>{{member.salart}}</td>
                      <td>{{member.health}}</td>
                    </tr>
                    </tbody>
                  </table>
                  <div><label for="inputIdCard" class="form-label">困难生等级</label>
                    <input v-model="bot.level" type="text" class="form-control" id="inputIdCard"
                           placeholder="0"></div>
                  <div class="col-12">
                    <label for="inputReason" class="form-label">评审</label>
                    <textarea  v-if="kunnan_student.state!=='未审核'"  disabled v-model="kunnan_student.comments"  class="form-control" id="inputReason"></textarea>
                    <textarea  v-if="kunnan_student.state==='未审核'"  v-model="comments"  class="form-control" id="inputReason"></textarea>
                  </div>

                </div>
                <div class="modal-footer">
                  <!--                  <div class="error_message">{{bot.error_message}}</div>-->
                  <button type="button" class="btn btn-primary"  v-if=" kunnan_student.state=== '未审核'" @click="kunnan_student_update(kunnan_student)">{{shenhe}}</button>
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
              </div>
            </div>
          </div>
        </td>
      </tr>
      </tbody>
    </table>

  </ContentField>

</template>

<script>
import ContentField from "@/components/ContentField";
import {ref} from "vue";
import {useStore} from "vuex";
import $ from "jquery";
import {Modal} from 'bootstrap/dist/js/bootstrap';

export default {
  name: "BursariesShenHeView.vue",
  components: {ContentField},
  setup(){
    let shenhe = ref("通过");
    const store = useStore();
    let kunnan_students = ref("");
    let bot = ref("");
    let member_list = ref(null);
    let comments = ref("");
    const refresh  = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/kunnanstudent/getall/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp)
          kunnan_students.value = resp;
        },
        error(){
        }
      })
    }
    refresh();
    const getInfoById = (student_id) =>{
      console.log(student_id);
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/poorstudent/getinfobyid/",
        type: 'get',
        data:{
          student_id:student_id.studentId,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          bot.value = resp;
          console.log(bot);
        },
        error(){
        }
      });
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/family/getinfobyid/",
        type: 'get',
        data:{
          student_id:student_id.studentId,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          member_list.value = resp;
        }
      });

    };
    const kunnan_student_update = (kunnan_student) =>{
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/kunnanstudent/updateComment/",
        type: 'post',
        data:{
          student_id:kunnan_student.studentId,
          comments : comments.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          if(resp.error_message ==="success"){
            Modal.getInstance("#update-bot-button-"+kunnan_student.id).hide();
            refresh();
          }
        },
        error(){
        }
      })
    }
    return {
      shenhe,
      store,
      kunnan_students,
      bot,
      getInfoById,
      refresh,
      member_list,
      comments,
      kunnan_student_update,
    }
  }
}
</script>

<style scoped>

</style>