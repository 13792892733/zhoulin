<style>
  .slide-fade-enter-active {
    transition: all .5s ease;
  }
  .slide-fade-leave-active {
    transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active for below version 2.1.8 */ {
    transform: translateX(10px);
    opacity: 0;
  }
  .table2{
    width: 100%;
    height: 100px;
    z-index: 19;
    position: relative;
    display: inline-block;
    background-color: white;
  }
  .table2_2{
     position: fixed;
     top:0px;
  }
  .dv_input{
    width: 552px;
    height: 48px;
    display: inline-block;
    margin-left: 80px;
    margin-top: 20px;
    border-radius: 9px;
    background: gainsboro;
    float: left;
  }
  .dv_input input{
    height: 48px;
    width: 492px;
    background-color: ghostwhite;
    outline: none;
    border: 0px;
  }
  .dv_input button{
    width: 56px;
    height: 100%;
    border: 0px;
    background-color: #8D8D8D;
    border-radius: 0px 9px 9px 0px;
  }
  .dv_seleve{
    width: 100%;
    padding-bottom: 30px;
    background-color: white;
    padding-left: 20px;
    line-height: 30px;
    border-radius: 8px;
    opacity: 0.9;
    box-shadow: 0px 6px 16px black;
  }
  .dv_select{
    background-color: gainsboro;
  }
  .logo1{
    width: 50px;
    height: 43px;
    
    float: left;
    margin-left: 50px;
    margin-top: 15px;
  }
  .table_logo{
    width: 30px;
    height: 30px;
    float: left;
   margin-left: 16px;
   margin-top: 23px;
    background-color: white;
    background-size: 100% 100%;
    background-repeat: no-repeat;
    margin-right: 10px;
    text-align: ;
  }
.table_2_denglu{
  float: left;
  height: 40px;
  margin-left:5px;
  margin-top: 24px;
}
.hellopage{
  width: 85px;
  height: 35px;
  float: left;
  color: deeppink;
  font-weight: 200;
  font-size: 18px;
  margin-top: 30px;
  margin-left: 10px;

}
</style>
<template>
  <transition name="slide-fade">
    <div class="table2" :class="{'table2_2':show_table}">

      <div class="logo1" v-for="p in logop" :style="p.logo"></div>
      <div style="float: left;">
        <h3>欢迎</h3>
      </div>

      <div class="dv_input">
        <input type="text" v-model="keyword" @keyup.38="chosebykeyup(-1)" @keyup.40="chosebykeyup(1)" @input="getgjzinfo()" />
        <button>
          <i class="fa fa-search fa-2x" style="font-size: 19px; color: white;"></i>
        </button>
        <div class="dv_seleve" v-if="keywordlist.length>0">
          <div class="dv_gjz" v-for="(k,i) in keywordlist" :class="{'dv_select':i==listindex}">{{k.gtkeywords}}</div>
        </div>
      </div>
      <div style="width: 240px; height: 80px; float: left;">
        <div v-for="p in logop" :style="p.xiaoxi" class="table_logo"></div>
        <div class="table_logo">
          <i class="fa fa-star-o" style="font-size: 31px;"></i>
        </div>
        <div @click="toshopcar_page()" v-for="p in logop" :style="p.shopcar" class="table_logo"></div>
        <div v-for="p in logop" :style="p.erweima" class="table_logo"></div>
        <div style="width: 240px; height: 20px;float: left; margin-top: 3px;">
          &nbsp;&nbsp;&nbsp;&nbsp;消息 &nbsp; &nbsp; &nbsp; 收藏 &nbsp; &nbsp; &nbsp; 购物车&nbsp; &nbsp; 二维码
        </div>
      </div>
      <div class="table_2_denglu" v-if="(!showlog)">
        <button class="btn btn-success" @click="toenroll_page()">用户登录</button>
        <button class="btn btn-info" @click="topos_page()">商户登录</button>
      </div>
      <div v-if="showlog" class="hellopage">
        欢迎:{{user.logname}}
      </div>


    </div>
  </transition>
</template>
<script>
  export default {
    data: function() {
      return {
        show_table: false,
        tran1: true,
        keyword: "",
        keywordlist: [],
        listindex: -1,
        logop: [{
          "logo": "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/aa.jpg)",
          "xiaoxi": "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/b.png)",
          "shopcar": "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/c.png)",
          "erweima": "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/d.png)",
        }],
        showlog:false,
        user:{}
      }
    },
    mounted() {
      var ob = this;

      $.ajax("http://127.0.0.1:8090/Goods_shop1/userinfo/useronline",{
        xhrFields:{
          withCredentials:true
        },
        success:function(result){
          if(result.logname!=undefined){
            ob.user=result;
            ob.showlog=true;
          }
        }
      });
      $.ajax("http://127.0.0.1:8090/Goods_shop1/posonline",{
        xhrFields:{
          withCredentials:true
        },
        success:function(result){
          if(result.logname!=undefined){
            ob.user=result;
            ob.showlog=true;
          }
        }
      });
      $(window).scroll(function() {
        if ($(window).scrollTop()>500) {
          if (ob.show_table == false) {
            ob.show_table = true;
            ob.tran1 = false;
            window.setTimeout(function() {
              ob.tran1 =true;
            }, 500);
          }

        } else {
          ob.show_table = false;
          ob.tran1 = false;
        }

      });

    },
    methods: {
      toshopcar_page(){
        if(this.user.logname==undefined){
          this.$router.push("logon");
          return;
        }else{
          this.$router.push({name:"shopcar"});
        }

      },
      /* 跳转到用户登录页面*/
      toenroll_page(){
        this.$router.push("logon")
      },
      /* 跳转到商户登录页面*/
      topos_page(){
        this.$router.push("registe1r")
      },

      /* 键盘上下选中*/
      chosebykeyup(num) {
        this.listindex += num;
        if (this.listindex < 0) {
          this.listindex = (this.keywordlist.length - 1);
        }
        if (this.listindex > (this.keywordlist.length - 1)) {
          this.listindex = 0;
        }
        this.keyword = this.keywordlist[this.listindex].gtkeywords;
      },
      /* 根据关键字查询*/
      getgjzinfo() {
        var ob = this;
        if (ob.keyword == "") {
          ob.keywordlist = [];
          return;
        }
        var url = "http://127.0.0.1:8090/Goods_shop1/goodsinfo/getkeywordall"
        $.ajax(url, {
          data: {
            keyword: ob.keyword
          },
          success: function(result) {
            ob.keywordlist = result;
          },
        });
      }


    }











  }
</script>
