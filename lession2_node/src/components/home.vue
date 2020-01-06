<!-- 主页面 -->
<style>
  .dvz{
    width: 100%;
    height: 500px;

  }
  .dvz .dv_goodslist{
    margin: auto;
    width: 1200px;
    height: 200px;

  }

</style>


<template>
  <div class="dvz">
    <div>

      <goodsbrowse></goodsbrowse>
      <goodstable2></goodstable2>
      <roll></roll>


      <shop></shop>


    </div>
  </div>
</template>

<script>
   import goodsbrowse from '@/components/goodsbrowse.vue'
   import goodstable2 from '@/components/goodstable2.vue'
   import shop from '@/components/shop.vue'
   import roll from '@/components/roll.vue'

   export default{
     components:{
       goodsbrowse,
       goodstable2,
       shop,
       roll
     },
     data(){
       return{
         keyword:"%韩%",

       }
     },
     methods:{
       


     },
     mounted:function(){


     }

   }
</script>
