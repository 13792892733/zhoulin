<style>
  .dv_header{
    margin: auto;
    width: 1000px;
    height: 200px;
    background-image: url(../assets/timgs.jpg);
    background-size: 100%;
    margin-top: 30px;
  }
  .dv_personal{
    float: left;
    width: 300px;
    height: 150px;
    margin: 20px;
  }
  .dv_head{
    background-color: orange;
    width: 90px;
    height: 90px;
    float: left;

  }
  .dv_names{
    font-size: 40px;
    float: left;
  }
  .dv_hname{
    font-size: 30px;
    float: left;

  }
  .dv_ccu{
    font-size: 20px;
    clear: both;
    margin-right: 20px;
  }
  .dv_btn{
    position: absolute;
    right: 200px;
    top: 230px;
    background-color: aqua;
    font-size: 20px;

  }




  .dv_search{
    margin-top: 25px;
    width: 1000px;
    height: 50px;
  }
  .dv_search input{
      float: left;
      margin-left: 400px;
      width: 492px;
      height: 45px;
      background-color: transparent;
      border-radius: 6px 0px 0px 6px;
  }
  .dv_search button{
      width: 58px;
      height: 100%;
      background-color: #8D8D8D;
      border: 0px;
      border-radius: 0px 9px 9px 0px;
   }

   .goods_detail{
   	width: 100%;height: 100%;position: absolute;

   	box-shadow: 0px 0px 166px 6px black inset;
   	transition: all 0.6s;
     opacity: 0;
   	color:#FFFFFF;
   }
    .box-wrappe{
      width: 1000px;
      width: auto;
      float: left;
      margin-top: 25px;
      margin-left: 100px;
    }
    .dv_goods{
      width: 220px;
      height: 330px;
      float: left;
      margin-right: 10px;
      background-color: orange;
    }
   /* .box:hover .goods_detail{
     opacity: 1;

   } */

   /* .goods_detail button{

   	background-color: #FFFFFF;
   	color:gray;
   	width: 40px;
   	height: 40px;
   	border-radius: 90px;
   	border:0px;
   	font-size: 23px;

   	float: right;
   	margin-right: 20px; */




</style>

<template>
  <div class="container">
    <div class="dv_search">
      <input type="text" v-model="keyword">
      <button>
        <i class="fa fa-search " style="color:#ffffff;font-size: 19px;"></i>
      </button>

    </div>


    <div class="dv_header">
      <div class="dv_personal">
        <!-- 商家头像 -->
        <div class="dv_head"></div>
        <!-- 店铺名 -->
        <div class="dv_names">美妆博士店</div>
        <!-- 商家名 -->
        <div class="dv_hname">hello!</div>
        <br />
        <!-- 商家联系方式 -->
        <div class="dv_ccu">1870983987</div>
        <!-- 商家地址 -->
        <div class="dv_address">山东省青岛市市南28号</div>

        <div style="border-radius: 8px;">
          <button class="dv_btn " style="border-radius: 12px;width: 200px;height: 50px;border: 1px;" @click="doupdate()">修改用户详细信息</button>
        </div>
      </div>
    </div>

    <div class="dv_page"></div>



    <div class="box-wrappe">
      <div v-for="i in 5" class="dv_goods" :style="i" >
          <div class="goods_detail">

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default{
    data () {
      return {
        goodsinfoes:[],
       /* pagenum:1,
        locked:false */
      }
    },
    /* mounted(){
      this.getInfoes();
      var ob=this;
      $(window).scroll(function(){
          //页面滚动距离
          var windowScrollTop=$(document).scrollTop();

          var lastDvGoods=$("#");
          if($(lastDvGoods).offset().top-windowScrollTop<500){
            if(ob.locked==false){
              ob.locked=true;
              ob.pagenum+=1;
              ob.getInfoes();
            }
          }

      });
    }, */
    methods:{
      doupdate(){
        this.$router.push();
      }
    },
    doajax(){
      var ob=this;
      var url="#"
      $ajax(url,{
        /* data:{

        }, */
        success:function(result){
          alert(resurl);

          /* for(var i in goodslist){
            goodslist[i].gimgurl="()";
            ob.goodsinfoes.push(goodslist[i]);
          } */

          // ob.locked=false;

        },


      });
    }
  }
</script>
