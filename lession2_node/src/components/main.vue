<!-- 瀑布流 -->
<style >
    .container{
      width: 100%;
      height: 500px;
    }

    .container .dv_goodslist{
      margin: auto;
      width: 1200px;
      height: 200px;

    }
    /* 单张商品信息*/
    .dv_goods{

      background-size: 100%;
      border-radius: 6px;
      transition: all 0.3s;
    }
    .dv_goods:hover{
      transform: translateY(-10px);
      box-shadow: 0px 10px 16px gray;
    }


    /* 瀑布流*/

     .box {
    	height:250px;
    	margin:9px;
    	break-inside:avoid;
    	background-size: 100%;
    	background-repeat:no-repeat ;
    	border-radius: 6px;
    	margin-top: 12px;
    	position:relative;
    	overflow: hidden;
    }

    .box .goods_detail{
    	width: 100%;height: 100%;position: absolute;
    	box-shadow: 0px 0px 166px 6px black inset;
    	transform: translateX(100%);
    	transition: all 0.6s;
    	color:#FFFFFF;
    }

    .box:hover .goods_detail{
    	transform: translateX(0px);

    }

    .goods_detail button{

    	background-color: #FFFFFF;
    	color:gray;
    	width: 40px;
    	height: 40px;
    	border-radius: 90px;
    	border:0px;
    	font-size: 23px;

    	float: right;
    	margin-right: 20px;


    }
    .goods_detail button:first-child{
    	margin-top:20px;
    }




    .box:first-child{
    	margin-top: 0px;
    }
    .spe {
    	height:300px;

    }
    .box-wrapper {
        column-count:5;
    	column-gap:1px;
    	width: 1200px;
    	margin: auto;
    	margin-top: 20px;


    }




</style>

<template>
  <div class="container ">

      <div class="box-wrapper">

        <div v-for="(g,i) in goodsinfoes" class="dv_goods box" :class="{'spe':((i%3==0)||(i%7==0))}"  :style="g.gimgurl" ></div>




      </div>




  </div>


</template>

<script>
export default {
  data () {
    return {
      goodsinfoes:[],
      pagenum:1,
      locked:false
    }
  },
  mounted(){
    this.getInfoes();
    var ob=this;
    $(window).scroll(function(){
        //页面滚动距离
        var windowScrollTop=$(document).scrollTop();

        var lastDvGoods=$(".box:last");
        if($(lastDvGoods).offset().top-windowScrollTop<500){
           if(ob.locked==false){
             ob.locked=true;
             ob.pagenum+=1;
             ob.getInfoes();
           }
        }

    });
  },
  methods:{
    getInfoes(){
      var ob=this;
      var url="http://192.168.1.106:8090/Goods_shop1/goodsinfo/getAllGoods"
      $.ajax(url,{
          data:{
            pagenum:this.pagenum
          },
          success:function(result){
            var goodslist=result;

            for(var i in goodslist){
              goodslist[i].gimgurl="background-image:url('http://192.168.1.106:8090/Goods_shop1/imgs/tp/"+goodslist[i].gimgurl+"')";
              ob.goodsinfoes.push(goodslist[i]);
            }

            ob.locked=false;

          },


      });
    }


  }
}
</script>
