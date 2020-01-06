<style>
.jump_page_whole{
      width: 100%;
      height: 500px;
    }
  .pagept{
    width: 350px;
    height: 100%;
    margin-left: 100px;
    float: left;
    background-size: 100%;
    background-color: orchid;
  }
  .makeup{
    width: 470px;
    height: 480px;
    float: left;
    margin-left: 20px;
  }
  .detail_x{
    width:470px ;
    height: 60px;
    margin-top: 1px;
    line-height: 40px;
  }
  .dv_label{
    width: 80px;
    height: 30px;

    float: left;
    text-align: right;
    margin-top: 3px;
  }
  .size_dv{
    min-width: 30px;
    height: 30px;
    float: left;
    margin-left: 10px;
    margin-top: 4px;
    text-align: center;
    line-height: 30px;
    border: 2px solid gray;
    font-size: 16px;
  }
  .size_dv_dsid{
    background-color: orchid;
  }
  .count_dv{
    width: 102px;
    height: 25px;
    border: 2px solid gray;
    float: left;
    margin-top: 6px;
    margin-left: 20px;
    text-align: center;
    line-height: 25px;
  }
  .count_dv div{
    width: 24px;
    height: 100%;
    float: left;

  }
  .dv_shop{
    width: 150px;
    height: 40px;
    margin-left: 10px;
    border-radius: 10px;
    color: white;
    float: left;
    text-align: center;
  }
  .dv_shop :hover{
    cursor: pointer;
  }
  .logo{
    width: 20px;
    height: 20px;
    float: left;
    margin-left: 20px;
    background-image: url(../../static/a.png);
    border-radius: 2px;
    margin-top: 14px;
  }
  .logo_ali{
    background-position: -38px -295px;

  }
  .logo_weixin{
    background-position: -65px -295px;
  }
  .page_remind{
    float: left;
    width: 40px;
    height: 30px;
    position: absolute;
    margin-left: 300px;
    margin-top: -30px;
    color: red;
    font-size: 20px;

  }

</style>
<template>
  <div>
  <goodsbrowse></goodsbrowse>
  <goodstable2></goodstable2>

  <div class="jump_page_whole">
    <div class="pagept" :style="goods.gimgurl">
    </div>
    <!-- 详细信息 -->
    <div class="makeup">
      <div class="detail_x">
        <h3>{{goods.gdname}}</h3>
      </div>
      <!-- 单行块detail_x -->
      <div class="detail_x">
        <div class="dv_label">
          价格：
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span style="font-size: 26px; color: red; font-weight: 200;">{{goods.price}}</span>
      </div>

      <div class="detail_x">
        <div class="dv_label">
          颜色：
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      </div>
      <div class="detail_x">
        <div class="dv_label">
          尺码：
        </div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <div @click="clicksize(s.gsid)" class="size_dv" v-for="s in picture_list" :class="{'size_dv_dsid':s.gsid==gsid}">{{s.gstext}}</div>
      </div>
      <div class="detail_x">
        <div class="dv_label">
          数量：
        </div>
        <div class="count_dv">
          <div @click="changGoodsjia(-1)">-</div>
          <div style="width: 46px; border-left: 2px solid gray; border-right: 2px solid gray;">
            {{goods_count}}
          </div>
          <div @click="changGoodsjia(1)">+</div>
        </div>
      </div>
      <div class="detail_x">
        <div class="dv_label">
        </div>
        <div class="dv_shop" style="background-color: red;"><h4>立即购买</h4></div>
        <transition name="slide-fade">
          <div class="page_remind" v-if="showshop">+{{goods_count}}</div>
        </transition>
        <div @click="toshopcar_page()" class="dv_shop" style="background-color: orchid;">
          <h4>加入购物车</h4>
          </div>
      </div>

      <div class="detail_x">
        <div class="dv_label">
          服务说明：
        </div>
        <div v-for="a in fuwuarry" style="width: 120px; height:30px ; float: left;">
          <i class="fa fa-check-square-o" style="color: deeppink;"></i>
          {{a}}
        </div>

      </div>

      <div class="detail_x">
        <div class="dv_label">
          支付方式：
        </div>
        <div class="logo logo_ali"></div>
        <div class="logo logo_weixin"></div>

      </div>
    </div>
    <!-- 热销商品 -->
    <beatgoods></beatgoods>


  </div>




  </div>
</template>

<script>
import goodsbrowse from '@/components/goodsbrowse.vue'
import goodstable2 from '@/components/goodstable2.vue'
import beatgoods from '@/components/beatgoods.vue'
export default{
  components:{
    goodsbrowse,
    goodstable2,
    beatgoods
  },
  data(){
    return{
      picture_list:[],
      goods:{},
     fuwuarry:[
       "72小时发货",
       "7天无理由退货",
       "延误必赔",
       "退货补运费",
       "全国包邮"
     ],
     /* 商品数量*/
     goods_count:1,
     /* 商品尺寸id*/
     gsid:1,
     useronline:false,
     showshop:false


    }
  },
  methods:{
    clicksize(s){
      this.gsid=s;
    },
    userlogname_yn(){
      var ob=this;
     var url="http://127.0.0.1:8090/Goods_shop1/userinfo/useronline"
      $.ajax(url,{
        async:false,
        success:function (result) {
          if(result.logname!=undefined){
            ob.useronline=true;
          }
        },
        xhrFields:{
          withCredentials:true
        }

      })
    },
    toshopcar_page(){
      this.userlogname_yn();
      if(!this.useronline){
        this.$router.push({name:"logon"})
        return;
      }

      var ob=this;
      var url="http://127.0.0.1:8090/Goods_shop1/shopcar/doinsert"
        $.ajax(url,{
          data:{
            gdid:ob.goods.gdid,
            gdcount:ob.goods_count,
            gsid:ob.gsid
          },

           success:function (result) {

           },
           xhrFields:{
             withCredentials:true
           }

        })
       ob.showshop=true;
       window.setTimeout(function(){
          ob.showshop=false;
       },1000)





    },

    changGoodsjia(num){
      this.goods_count+=num;
      if(this.goods_count<1){
        this.goods_count=1;
      }
    },
    getpicture(){
      var ob=this;
      var url="http://127.0.0.1:8090/Goods_shop1/goodsinfo/getGoodsbygdid"
      $.ajax(url,{
        data:{
          gdid:ob.goods.gdid
        },
        success:function(result){
          ob.picture_list=result.sizelist;
          ob.goods=result.good;
          ob.goods.gimgurl="background-image:url('http://127.0.0.1:8090/Goods_shop1/imgs/tp/"+ob.goods.gimgurl+"')";
        }
      })
    }
  },
  mounted(){
    $(window).unbind("scroll");
    this.goods.gdid=this.$route.query.gdid;
    this.getpicture();
    console.log(this.goods.gimgurl);
  }


}
</script>
