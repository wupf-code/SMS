<template>
  <!--  <ContentField>学生工作</ContentField>-->
  <div class="row justify-content-center" style="margin-top: 20px">
    <div class="col-2">
      <div class="container content-field" >
        <div class="card">
          <div class="card-body">
            <div class="list-group">
              <a @click="update_active_id(1)"  href="#" :class="$store.state.student.active_id === 1 ? 'list-group-item list-group-item-action active' : 'list-group-item list-group-item-action'" >
                困难生申请
              </a>
              <a @click="update_active_id(2)"  href="#" :class="$store.state.student.active_id === 2 ? 'list-group-item list-group-item-action active' : 'list-group-item list-group-item-action'">
                助学金申请
              </a>
              <a @click="update_active_id(3)"  href="#" :class="$store.state.student.active_id === 3 ? 'list-group-item list-group-item-action active' : 'list-group-item list-group-item-action'">
                奖学金申请
              </a>
              <a @click="update_active_id(4)"  href="#" :class="$store.state.student.active_id === 4 ? 'list-group-item list-group-item-action active' : 'list-group-item list-group-item-action'">
                勤工俭学
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="col-8">
<!--      <div class="container content-field">-->
<!--        <div class="card">-->
<!--          <div class="card-body">-->
<!--            wwww-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
      <PoorStudentView v-if="$store.state.student.active_id === 1 " />
      <BursariesView v-if="$store.state.student.active_id === 2 " />
      <ScholarshipView v-if="$store.state.student.active_id === 3 " />
      <QinGongView v-if="$store.state.student.active_id === 4"/>
    </div>
  </div>
</template>

<script>
import {useStore} from "vuex";
import PoorStudentView from "@/views/StrudentManager/PoorStudentView";
import BursariesView from "@/views/StrudentManager/BursariesView";
import ScholarshipView from "@/views/StrudentManager/ScholarshipView";
import QinGongView from "@/views/StrudentManager/QinGongView";
// import ContentField from "@/components/ContentField";
export default {
  name: "StudentManagerView",
  components: {
    QinGongView,
    ScholarshipView,
    // ContentField,
    PoorStudentView,
    BursariesView,
  },
  setup(){
    const store = useStore();
    const update_active_id = (e) =>{
      store.commit("updateStudentActiveId",e);
      console.log(e);
    }

    return {
      store,
      update_active_id,
    }
  }
}
</script>

<style scoped>

</style>