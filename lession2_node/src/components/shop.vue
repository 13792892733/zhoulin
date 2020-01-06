<style>
  /* 商品图片信息*/
  .dv_goods {

    background-size: 100%;
    border-radius: 6px;
    transition: all 0.3s;
  }

  .dv_goods:hover {
    transform: translateY(-10px);
    box-shadow: 0px 10px 16px gray;
  }

  /* 瀑布流*/

  .box {
    height: 250px;
    margin: 9px;
    break-inside: avoid;
    background-size: 100%;
    background-repeat: no-repeat;
    border-radius: 6px;
    margin-top: 20px;
    position: relative;
    overflow: hidden;
  }

  .box .goods_detail {
    width: 100%;
    height: 100%;
    position: absolute;
    box-shadow: 0px 0px 166px 6px black inset;
    opacity: 0;
    transition: all 0.6s;
    color: white;
  }

  .box:hover .goods_detail {
    opacity: 1;

  }

  .goods_detail button {
    background-color: #FFFFFF;
    color: gray;
    width: 40px;
    height: 40px;
    border-radius: 90px;
    border: 0px;
    font-size: 23px;
    float: right;
    margin-right: 20px;
  }

  .goods_detail button:first-child {
    margin-top: 20px;
  }

  .box:first-child {
    margin-top: 0px;
  }

  .spe {
    height: 300px;
  }

  .box-wrapper {
    column-count: 5;
    column-gap: 1px;
    width: 1200px;
    margin: auto;
    margin-top: 30px;
  }

  .dv_top {
    width: 60px;
    height: 60px;
    background-color: orchid;
    position: fixed;
    right: 30px;
    bottom: 50px;
    z-index: 9;
    border-radius: 9px;
    box-shadow: 0, 0, 3, white;
    line-height: 60px;
    opacity: 0.6;
  }
</style>
<template>
  <div style="margin-top: 40px;">
    <div class="box-wrapper">

      <div @click="jump_getailpage(g.gdid)" v-for="(g,i) in goodsinfoes" class="dv_goods box" :class="{'spe':((i%3==0)||(i%7==0))}"
        :style="g.gimgurl">
        <div class="goods_detail">
          <button>
            <i class="fa fa-star-o"></i>
          </button>
          <br /> <br /> <br /> <br />

          <button>
            <i class="fa fa-thumbs-o-up"></i>
          </button>
          <br /><br /> <br /> <br /> <br />
          <div style="text-align: center;">
            <hr />
            {{g.gdname}}
          </div>
        </div>
      </div>

    </div>

    <div class="dv_top text-center" @click="totop()">
      <i class="fa fa-arrow-up fa-2x" style="color: white;"></i>
    </div>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        goodsinfoes: [],
        pagenum: 1,
        locked: false
      }
    },
    mounted() {
      this.getInfoes();
      var ob = this;
      $(window).scroll(function() {
        //页面滚动距离
        var windowScrollTop = $(document).scrollTop();

        var lastDvGoods = $(".box:last");
        if ($(lastDvGoods).offset().top - windowScrollTop < 500) {
          if (ob.locked == false) {
            ob.locked = true;
            ob.pagenum += 1;
            ob.getInfoes();
          }
        }

      });
    },
    methods: {
      // 点击主图到详情页
      jump_getailpage(gdid) {
        this.$router.push({
          name: "detail_page",
          query: {
            gdid: gdid
          }
        });
      },
      // 返回到顶部
      totop() {
        $(document).scrollTop(0);
      },
      // 查询数据库遍历图片
      getInfoes() {
        var ob = this;
        var url = "http://127.0.0.1:8090/Goods_shop1/goodsinfo/getAllGoods"
        $.ajax(url, {
          data: {
            pagenum: this.pagenum
          },
          success: function(result) {
            var goodslist = result;

            for (var i in goodslist) {
              goodslist[i].gimgurl = "background-image:url('http://127.0.0.1:8090/Goods_shop1/imgs/tp/" +
                goodslist[i].gimgurl + "')";
              ob.goodsinfoes.push(goodslist[i]);
            }

            ob.locked = false;

          },


        });
      }


    }
  }
</script>
