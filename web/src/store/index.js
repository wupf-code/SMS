import { createStore } from 'vuex'
import ModuleUser from "./user.js"
import ModuleStudent from  './student'
import ModuleTeacher from  './teacher'
export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    user: ModuleUser,
    student:ModuleStudent,
    teacher:ModuleTeacher,
  }
})
