<template>
  <NavBar/>
  <router-view/>
</template>


<script>
  import NavBar from "@/components/NavBar";
  import "bootstrap/dist/js/bootstrap";
  import "bootstrap/dist/css/bootstrap.min.css";
   export default {
    components: {
      NavBar,
    },
     setup(){

     }
  }
</script>
<style>
body{
  background-image:url("@/assets/R-C.jpg");
  background-size: cover;
}

</style>
