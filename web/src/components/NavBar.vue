<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
      <router-link class="navbar-brand" :to="{ name : 'home' }">SMS</router-link>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link :class=" route_name=='student_manager' ? 'nav-link active' : 'nav-link' " :to="{ name : 'student_manager' }">学生工作管理</router-link>

          </li>
          <li class="nav-item">
            <router-link :class=" route_name=='manager_manager' ? 'nav-link active' : 'nav-link' " :to="{ name : 'teacher_manager' }">教师工作管理</router-link>

          </li>
          <li class="nav-item">
            <router-link :class=" route_name=='bulletin' ? 'nav-link active' : 'nav-link' " :to="{ name : 'bulletin' }">公告</router-link>

          </li>

        </ul>
        <ul class="navbar-nav ">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            登录/注册
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li>
              <router-link class="dropdown-item" :to="{name:'login'}"  role="button" >
              登录
            </router-link>
            </li>
            <li>
              <router-link class="dropdown-item " :to="{name:'register'}"  role="button" >
                注册
              </router-link>
            </li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">退出</a></li>
          </ul>
        </li>
        </ul>

      </div>
    </div>
  </nav>
</template>

<script>
import { useRoute } from 'vue-router'
import { computed } from "vue";
import {useStore} from "vuex";
export default {
  name: "NavBar",
  setup(){
    const store = useStore();
    const route=useRoute();
    let route_name=computed(()=>{
      return route.name
    })
    const logout = () =>{
      store.dispatch("logout");
    }
    return {
      route_name,
      logout
    }
  }
}
</script>

<style scoped>

</style>