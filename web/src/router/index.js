import { createRouter, createWebHistory } from 'vue-router'
import BulletinView from "@/views/ BulletinView/BulletinView";
import StudentManagerView from "@/views/StrudentManager/StudentManagerView";
import TeacherManagerView from "@/views/TeascherManager/TeacherManagerView";
import UserAccountLoginView from "@/views/User/Account/UserAccountLoginView";
import UserRegisterView from "@/views/User/Account/UserRegisterView";
import ErrorView from "@/views/ErrorView/ErrorView";
// import store from "@/store";
const routes = [
  {
    path:"/",
    name:"home",
    redirect:"/studentmanager/",
  },
  {
    path:"/studentmanager/",
    name:"student_manager",
    component :StudentManagerView
  },
  {
    path:"/teachermanager/",
    name:"teacher_manager",
    component :TeacherManagerView
  },
  {
    path:"/bulletin/",
    name:"bulletin",
    component :BulletinView
  },
  {
    path:"/user/account/login/",
    name:"login",
    component :UserAccountLoginView
  },

  {
    path:"/user/account/register/",
    name:"register",
    component :UserRegisterView,
  },
  {
    path:"/404/",
    name:"404",
    component: ErrorView,
  },
  {
    path:"/:catchAll(.*)",
    redirect: "/404/",

  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
