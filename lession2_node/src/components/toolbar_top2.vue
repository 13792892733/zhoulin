<style>
  /* 可以设置不同的进入和离开动画 */
/* 设置持续时间和动画函数 */
.slide-fade-enter-active {
  transition: all 0.5s ease;
}
.slide-fade-leave-active {
  transition: all 0.5s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slide-fade-enter, .slide-fade-leave-to
/* .slide-fade-leave-active for below version 2.1.8 */ {
  transform: translateY(-10px);
  opacity: 0;
}



  .toolbar_top2{
    width: 100%;
    height: 100px;

    z-index: 99;
    position: relative;
    background-color: #FFFFFF;

  }

 .toolbar_top2_pos2{
    position: fixed;
    top:0px;
    left: 0px;
    box-shadow: 0px 2px 12px #222222;
    opacity: 0.98;
 }


  .dv_search{
    width: 552px;
    height: 50px;
    background-color: #F1F1F1;
    border-radius: 9px;
    margin-left:100px;
    float:left;
    margin-top: 30px;
  }
  .dv_search input{
    width: 492px;background-color: transparent;outline: none;border:0px;height: 100%;padding-left: 20px;
    float: left;
  }
  .dv_search button{
      width: 58px;
      height: 100%;
      background-color: #8D8D8D;
      border: 0px;
      border-radius: 0px 9px 9px 0px;
   }

   .dv_keywords{
     width: 100%;background-color: #ffffff;
     opacity: 0.96;
     box-shadow: 0px 10px 16px #F1F1F1;
     padding-bottom: 20px;
     line-height: 30px;
     border-radius: 3px;
     z-index: 99;
   }
   .dv_keywords div{
      padding-left: 20px;
   }
   .dv_selected{
      color:#ffffff;
     background-color: hotpink;
   }

    .dv_log{
      color:#FF4466;font-family: fantasy;
      width:300px;float: left;
      height: 100%;margin-left: 30px;
      font-size: 50px;line-height: 120px;

    }
    .dv_user{
      color:#FF4466;
      width:300px;float: left;
      height: 100%;margin-left: 30px;
      font-size: 16px;
      line-height: 100px;
    }
</style>

<template>
  <div style="height: 100px;">
  <transition name="slide-fade">

    <div class="toolbar_top2" v-if="tran1" :class="{'toolbar_top2_pos2':toolbar2_mv}">

      <div class="text-center dv_log" >

        s <i class="fa fa-viadeo"></i> phia

      </div>


      <div class="dv_search">

        <input type="text" v-model="keyword" @input="getInfoesByKeyword()" @keyup.38="chooseKeyword(-1)" @keyup.40="chooseKeyword(1)">
        <button>
          <i class="fa fa-search " style="color:#ffffff;font-size: 19px;"></i>

        </button>
        <div class="dv_keywords" v-if="keywordlist.length>0">
          <div v-for="(k,i) in keywordlist" :class="{'dv_selected':i==selectindex}">{{k.gtkeywords}}</div>

        </div>

      </div>

      <div class="text-center dv_log" v-if="(!islogined)" >
        <i class="fa fa-viadeo"></i><button @click="toLogin()" class="btn btn-link">登录</button>
      </div>
      <div class="dv_user" v-if="islogined">
           欢迎光临，{{user.logname}}
      </div>

    </div>

  </transition>
    </div>
</template>

<script>
  export default {
    data() {
      return {
        toolbar2_mv: false,
        keyword: "",
        keywordlist: [],
        selectindex: -1,
        tran1: true,
        islogined:false,
        user:{}


      }


    },
    mounted() {
      var ob = this;


      $.ajax("http://127.0.0.1:8090/backstage190820/useronline",{
        xhrFields:{
          withCredentials:true
        },
        success:function(result){
          if(result.logname!=undefined){

            ob.user=result;
            ob.islogined=true;
          }

        }

      });



      $(window).scroll(function() {

        if ($(window).scrollTop() > 400) {

          if (ob.toolbar2_mv == false) {

            ob.toolbar2_mv = true;
            ob.tran1 = false;
            window.setTimeout(function() {
              ob.tran1 = true;
            }, 100);
          }


        } else {

          if (ob.toolbar2_mv) {
            ob.toolbar2_mv = false;
            ob.tran1 = false;
            window.setTimeout(function() {
              ob.tran1 = true;
            }, 100);
          }


        }



      });



    },

    methods: {
      toLogin(){

        this.$router.push({"name":"login"});


      },
      chooseKeyword(limit) {
        this.selectindex += limit;
        if (this.selectindex < 0) {
          this.selectindex = 0;
        }
        if (this.selectindex == this.keywordlist.length) {
          this.selectindex = this.keywordlist.length - 1;
        }

        this.keyword = this.keywordlist[this.selectindex].gtkeywords;

      },

      /* 根据关键字获取下拉菜单信息*/
      getInfoesByKeyword() {

        var ob = this;
        if (ob.keyword == "") {
          ob.keywordlist = [];
          return;
        }
        var url = "http://127.0.0.1:8090/backstage190820/goodsinfocontroller/getkeywords"
        $.ajax(url, {
          data: {
            keyword: this.keyword
          },
          success: function(result) {
            ob.keywordlist = result;
          },


        });

      },

    }
  }
</script>
