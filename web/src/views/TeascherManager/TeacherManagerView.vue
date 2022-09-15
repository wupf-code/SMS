<template>
    <!--  <ContentField>学生工作</ContentField>-->
    <div class="row justify-content-center" style="margin-top: 20px">
      <div class="col-2">
        <div class="container content-field" >
          <div class="card">
            <div class="card-body">
              <div class="list-group">
                <a @click="update_active_id(1)"  href="#" :class="$store.state.student.active_id === 1 ? 'list-group-item list-group-item-action active' : 'list-group-item list-group-item-action'" >
                  困难生审核
                </a>
                <a @click="update_active_id(2)"  href="#" :class="$store.state.student.active_id === 2 ? 'list-group-item list-group-item-action active' : 'list-group-item list-group-item-action'">
                  助学金审核
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-8">
        <PoorStudentShenHeView v-if="$store.state.teacher.active_id === 1 " />
        <BursariesShenHeView v-if="$store.state.teacher.active_id === 2 " />
      </div>
    </div>
</template>

<script>
import {useStore} from "vuex";
import PoorStudentShenHeView from "@/views/TeascherManager/PoorStudentShenHeView";
import BursariesShenHeView from "@/views/TeascherManager/BursariesShenHeView";
export default {
  name: "TeacherManagerView",
  components:{
    BursariesShenHeView,
    PoorStudentShenHeView

  },
  setup(){
    const store = useStore();
    const update_active_id = (e) =>{
      store.commit("updateActiveId",e);
      console.log(e);
    }
    return {
      update_active_id,
      store,
    }
  }
}
</script>

<style scoped>

</style>