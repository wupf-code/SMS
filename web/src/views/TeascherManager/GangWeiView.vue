<template>
<ContentFiled>
  <ul class="nav  nav-tabs">
    <li class="nav-item">
      <a :class=" num === 1 ? 'active nav-link' :  'nav-link'" @click="btn_num(1)" aria-current="page" href="#">勤工俭学岗位发布</a>
    </li>
    <li class="nav-item">
      <a :class=" num === 2 ? 'active nav-link' :  'nav-link'" @click="btn_num(2)" href="#">查看</a>
    </li>
    <li class="nav-item">
      <a :class="num === 3 ? 'active nav-link' :  'nav-link'" @click="btn_num(3)" href="#">审核</a>
    </li>
  </ul>
  <GangWeiFaBu v-if="num === 1" class="juli" />
  <GangweiChakan v-if="num === 2 " class="juli"/>
  <GangWeiShenhe v-if="num === 3" class="juli"/>
</ContentFiled>
</template>

<script>
import ContentFiled from "@/components/ContentField";
import GangWeiFaBu from "@/components/GangWeiFaBu";
import {ref} from "vue";
import GangweiChakan from "@/components/GangweiChakan";
import GangWeiShenhe from "@/components/GangWeiShenhe";
export default {
  name: "GangWeiView",
  components: {GangWeiShenhe, GangweiChakan, GangWeiFaBu, ContentFiled},
  setup(){
    let num = ref(-1);
    let btn_num = (id) =>{
      num.value = id;
    }
    return {
      num,
      btn_num,
    }
  }
}
</script>

<style scoped>
.juli{
  margin-top: 5px;
}
</style>