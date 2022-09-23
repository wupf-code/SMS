<template>
<div class="container" v-for="notice in noticeLists" :key="notice.id">
  <div  class="row justify-content-center">
    <div class="col-9">
      <div class="card" style="margin-top: 20px">
        <div class="card-header" >
          <span class="text-start">{{notice.username}}</span>
          <button @click="open_model(notice)" class="btn btn-primary" data-bs-toggle="modal" :data-bs-target="'#exampleModal-'+notice.id" v-if="notice.userId == $store.state.user.id" style="margin-left: 40vw">
            修改
          </button>
          <!-- Modal -->
          <div class="modal fade" :id="'exampleModal-'+notice.id" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">修改公告</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="col-md-6">
                      <label for="inputEmail4" class="form-label">标题</label>
                      <input v-model="model_title" type="text" class="form-control" id="inputEmail4">
                    </div>

                    <div class="col-12">
                      <label for="inputAddress" class="form-label">Address</label>
                      <textarea v-model="model_content" style="height: 200px" class="form-control" id="inputAddress" ></textarea>
                    </div>

                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" @click="update_notice(notice)">Save changes</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="card-body">
          <h5 class="card-title">{{notice.title}}</h5>
          <p class="card-text">
            {{notice.content}}
          </p>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import {useStore} from "vuex";
import $ from 'jquery';
import {ref} from "vue";
import {Modal} from 'bootstrap/dist/js/bootstrap';

export default {
  name: "BulletinView",
  setup(){
    const store = useStore();
    let noticeLists = ref("");
    let model_title=ref("");
    let model_content = ref("");
    const open_model = (notice) =>{
      model_title.value = notice.title;
      model_content.value = notice.content;
    }
    const refresh = () => {
      $.ajax({
        url:"http://127.0.0.1:3000/notice/getall/",
        type: 'get',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        success(resp){
          noticeLists.value = resp;
          console.log(resp);
          console.log(store.state.user.id);
        }
      })
    }
   refresh();
    const update_notice = (bot) =>{
      $.ajax({
        url:"http://127.0.0.1:3000/notice/update/",
        type: 'post',
        headers: {
          Authorization: "Bearer " + store.state.user.token,
        },
        data:{
          id:bot.id,
          title:model_title.value,
          content:model_content.value,
        },
        success(resp){
          if((resp.error_message === "success")){
            Modal.getInstance("#exampleModal-"+bot.id).hide();
            refresh();
          }
        }
      })
    }
    return {
      noticeLists,
      store,
      update_notice,
      open_model,
      model_title,
      model_content,
    }
  }
}
</script>

<style scoped>

</style>