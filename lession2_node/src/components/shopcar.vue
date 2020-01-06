<style>
  .page_1{
    width: 100%;
    height: 100%;
  }
  .page_2{
    width: 100%;
    height: 1000px;
  }
  .goodsshow{
    width: 800px;
    height: 300px;
    float: left;
    margin-top: 40px;
  }
  .goodsshow th{
    text-align: center;
  }
  .settlement{
    width: 800px;
    height: 50px;
    background-color: pink;
    text-align: center;
    line-height: 50px;
    font-size: 18px;
    border-radius: 10px;
  }
  .shop_gimg{
    width: 60px;
    height: 70px;
    background-size: 100%;
  }
  .best_goods{
    width: 260px;
    height: 1000px;
    float: left;
    margin-left: 10px;
  }
</style>

<template>
  <div class="page_1">
    <goodsbrowse></goodsbrowse>
    <goodstable2></goodstable2>
    <div class="page_2">
      <beatgoods></beatgoods>
      <div class="goodsshow">
        <table class="table" style="line-height: 80px; text-align: center;">
          <tr>
            <th>编号</th>
            <th>商品图片</th>
            <th>商品名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>单项总计</th>
            <th>选择</th>
            <th>全选
              <input type="checkbox"  v-model="chooseAll"/>
            </th>
            <th>删除</th>
          </tr>
          <tr v-for="(a,index) in shopcars" style="line-height: 80px;">
            <td>{{a.ctid}}</td>
            <td>
              <div class="shop_gimg" :style="a.gimgurl"></div>

            </td>
            <td>{{a.gdname}}</td>
            <td>{{a.gstext}}</td>
            <td>{{a.price}}</td>
            <td>
              <div style="height: 20px; width: 60px; line-height: 20px;">
                <button @click="changcountbyctid(index,-1)" style="height: 20px;width: 20px;">-</button>
                {{a.gdcount}}
                <button @click="changcountbyctid(index,1)" style="height: 20px; width: 20px;">+</button>
              </div>
            </td>
            <td>{{a.price*a.gdcount}}</td>
            <td>
              <input type="checkbox" :value="a.ctid" v-model="counts"/>
            </td>
            <td></td>
            <td style="color: red;">
              <i class="fa fa-close fa-2x" @click="changcountbyctid(index,-a.gdcount)"></i>
            </td>
          </tr>
        </table>
        <div class="text-right">
        	<button @click="showshopcarpage(1)">首页</button>

        	<button @click="showshopcarpage((pagenum-1)<1?1:(pagenum-1))">上一页</button>

        	<button v-for="c in pagecount" @click="showshopcarpage(c)" class="btn" :class="{'btn-primary':c==pagenum,'btn-link':c!=pagenum}">{{c}}</button>

        	<button @click="showshopcarpage((pagenum+1)>pagecount?pagenum:(pagenum+1))">下一页</button>

        	<button @click="showshopcarpage(pagecount)">尾页</button>
        </div>
        <div class="settlement">
          总计：￥{{sum}}
          <button class="btn btn-danger">结算</button>
        </div>
      </div>
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
        shopcars:[],
        counts:[],
        sum:0,
        chooseAll:false,
        pagenum:1,
        pagecount:0
      }
    },
    watch:{
      counts:function(){
       this.counts_shop();
      },
      chooseAll:function(){
        if(this.chooseAll){
          for(var c in this.shopcars){
            this.counts.push(this.shopcars[c].ctid);
          }
        }else{
          this.counts=[];
        }
      }
    },
    methods:{
     /* 结算跳转页面到填写信息页面*/
     togoodsorder(){
       this.$router.push({
         name:"orderpage"

       })
     },

      /* 结算总价*/
      counts_shop(){
        this.sum=0;
        for(var i in this.counts){
          for(var c in this.shopcars){
            if(this.shopcars[c].ctid==this.counts[i]){
            this.sum+=this.shopcars[c].gdcount*this.shopcars[c].price;
            break;
            }
          }
        }
      },
      /* 显示购物车详情*/
      showshopcarpage(bn){
        var ob=this;
        ob.pagenum=bn;
        var url = "http://127.0.0.1:8090/Goods_shop1/shopcar/showshopcar"
        $.ajax(url,{
          async:false,
          data:{
            pagenum:ob.pagenum,

          },
          success:function(result){
            ob.shopcars=result.array;
            for(var a in ob.shopcars){
              ob.shopcars[a].gimgurl="background-image:url('http://127.0.0.1:8090/Goods_shop1/imgs/tp/"+ob.shopcars[a].gimgurl + "')";

            }
            ob.pagecount=result.pagecount;
          },
          xhrFields:{
            withCredentials:true
          }
        })
      },
      /* 通过-、+更改商品数量*/
      changcountbyctid(index,step){
        var ob=this;
        var url = "http://127.0.0.1:8090/Goods_shop1/shopcar/changcountbyid"
        $.ajax(url,{
          data:{
            ctid:ob.shopcars[index].ctid,
            count:(ob.shopcars[index].gdcount+step)
          },
          success:function(result){
           if(result==true){
             if((ob.shopcars[index].gdcount+step)==0){
               ob.shopcars.splice(index,1);
             }else{
               ob.shopcars[index].gdcount=(ob.shopcars[index].gdcount+step);
             }
             ob.counts_shop();
           }
          },
          xhrFields:{
            withCredentials:true
          }
        })
      }

    },
    mounted() {
      this.showshopcarpage(this.pagenum);
    }









  }
</script>
