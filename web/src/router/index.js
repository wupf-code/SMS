import { createRouter, createWebHistory } from 'vue-router'
import BulletinView from "@/views/ BulletinView/BulletinView";
import StudentManagerView from "@/views/StrudentManager/StudentManagerView";
import TeacherManagerView from "@/views/TeascherManager/TeacherManagerView";
import UserAccountLoginView from "@/views/User/Account/UserAccountLoginView";
import UserRegisterView from "@/views/User/Account/UserRegisterView";
import ErrorView from "@/views/ErrorView/ErrorView";
import PersonalInformationView from "@/views/User/Account/PersonalInformationView";
import store from "@/store";
import FamilyInformationView from "@/views/User/Account/FamilyInformationView";
// import store from "@/store";
const routes = [
  {
    path:"/",
    name:"home",
    redirect:"/studentmanager/",
    meta:{
      requestAuth:true
    }
  },
  {
    path:"/studentmanager/",
    name:"student_manager",
    component :StudentManagerView,
    meta:{
      requestAuth:true
    }
  },
  {
    path:"/teachermanager/",
    name:"teacher_manager",
    component :TeacherManagerView,
    meta:{
      requestAuth:true
    }
  },
  {
    path:"/bulletin/",
    name:"bulletin",
    component :BulletinView,
    meta:{
      requestAuth:true
    }
  },
  {
    path:"/user/account/login/",
    name:"login",
    component :UserAccountLoginView,
    meta:{
      requestAuth:false,
    }
  },

  {
    path:"/user/account/register/",
    name:"register",
    component :UserRegisterView,
    meta:{
      requestAuth:false,
    }
  },

  {
    path:"/user/account/personalinformation/",
    name:"personalinformation",
    component :PersonalInformationView,
    meta:{
      requestAuth:true
    }
  },
  {
    path:"/user/account/familyinformation/",
    name:"familyinformation",
    component:FamilyInformationView,
    meta:{
      requestAuth:true
    }
  },

  {
    path:"/404/",
    name:"404",
    component: ErrorView,
    meta:{
      requestAuth:false,
    }
  },
  {
    path:"/:catchAll(.*)",
    redirect: "/404/",
    meta:{
      requestAuth:false
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
router.beforeEach((to, from, next)=>{
  if(to.meta.requestAuth && !store.state.user.is_login){
    next({name : "login"});
  }else{
    next();
  }

})

export default router
