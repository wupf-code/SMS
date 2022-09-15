<template>

  <ContentField >
    <table class="table table-hover" >
      <thead>
      <tr>
        <th>姓名</th>
        <th>状态</th>
        <th>操作</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="bot in poor_students" :key="bot.id">
        <td>{{bot.username}}</td>
        <td>{{bot.shenhe}}</td>
        <td><button type="button" @click="getFamily(bot.studentId)" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#update-bot-button-'+bot.id">审核</button>
          <div class="modal fade" :id="'update-bot-button-'+bot.id" tabindex="-1" >
            <div class="modal-dialog modal-fullscreen">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">困难生审核</h5>
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
                </div>
                <div class="modal-footer">
<!--                  <div class="error_message">{{bot.error_message}}</div>-->
                  <button type="button" class="btn btn-primary" @click="bot_update(bot)">通过审核</button>
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
import $ from 'jquery'
import {useStore} from "vuex";
export default {
  name: "PoorStudentShenHeView",
  components:{ContentField},
  setup(){
    const store = useStore();
    let poor_students = ref("");
    const refresh  = () => {
      $.ajax({
        url: "http://127.0.0.1:3000/studentmanager/poorstudent/getall/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          console.log(resp)
          poor_students.value = resp;
        },
        error(){
        }
      })
    }
    refresh();
    let member_list = ref(null);
    const getFamily = (student_id) =>{
      console.log(student_id)
      $.ajax({
        url: "http://127.0.0.1:3000/user/account/family/getinfobyid/",
        type: 'get',
        data:{
          student_id:student_id
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp) {
          member_list.value = resp;
          console.log(member_list.value);
        }
      });
    }
    return {
      poor_students,
      member_list,
      getFamily,
    }
  }
}
</script>

<style scoped>

</style>