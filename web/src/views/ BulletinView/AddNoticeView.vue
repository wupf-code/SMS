<template>
<ContentFiled>
  <form class="row g-3" @submit.prevent="add_new_notices">
    <div class="col-md-6">
      <label for="inputEmail4" class="form-label">标题</label>
      <input v-model="title" type="text" class="form-control" id="inputEmail4">
    </div>

    <div class="col-12">
      <label for="inputAddress" class="form-label">Address</label>
      <textarea v-model="content" style="height: 200px" class="form-control" id="inputAddress" >请输入公告内容</textarea>
    </div>

    <div class="col-12">
      <button type="submit" class="btn btn-primary">提交</button>
    </div>
  </form>
</ContentFiled>
</template>

<script>
import ContentFiled from "@/components/ContentField";
import {useStore} from "vuex";
import {ref} from "vue";
import $ from 'jquery';
import router from "@/router";
export default {
  name: "AddNoticeView",
  components: {ContentFiled},
  setup(){
    const store =  useStore();
    let title = ref("");
    let content = ref("");
    const add_new_notices = () => {
      $.ajax({
        url:"http://127.0.0.1:3000/notice/add/",
        type: 'post',
        data:{
          title:title.value,
          content:content.value,
        },
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp){
          if(resp.error_message == "success"){
            router.push({name: "bulletin"});
          }
        }
      })
    }
    return {
      title,
      content,
      store,
      add_new_notices,
    }
  }
}
</script>

<style scoped>

</style>