<style>
  .table2_whole{
  width: 100%;
  z-index: 9;
  }
  .roll{
    height: 100%;
    width: 962px;
    float: left;

  }

  .theme{
    width: 300px;
    height: 421px;
    float: left;

    margin-bottom: 40px;
  }
  .dvp{
  width: 140px;
  height: 130px;
  float: right;
  margin: 3px;
  border-radius: 8px;
  }
  .dvp:hover{
    transform: translateY(-5px);
     box-shadow: 0px 4px 6px gray;
  }
</style>
<template>
  <div class="table2_whole">
  <div class="roll">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
              <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class=""></li>
                <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
                <li data-target="#carousel-example-generic" data-slide-to="2" class="active"></li>
              </ol>
              <div class="carousel-inner" role="listbox">
                <div class="item">
                  <img data-src="holder.js/700x500/auto/#777:#555/text:First slide" alt="First slide [700x500]" src="../assets/gg1.png" data-holder-rendered="true">
                </div>
                <div class="item">
                  <img data-src="holder.js/700x500/auto/#666:#444/text:Second slide" alt="Second slide [700x500]" src="../assets/gg2.png" data-holder-rendered="true">
                </div>
                <div class="item active">
                  <img data-src="holder.js/700x500/auto/#555:#333/text:Third slide" alt="Third slide [700x500]" src="../assets/gg3.png" data-holder-rendered="true">
                </div>
              </div>
              <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
              </a>
              <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
              </a>
            </div>
  </div>

  <div class="theme">
    <div class="dvp" v-for="a in array_theme" :style="a"></div>

  </div>

</div>




</template>

<script>
  export default{
    data(){
      return{
        array_theme:[
          "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/a1.gif)",
          "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/a2.jpg)",
          "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/a3.jpg)",
          "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/a4.jpg)",
          "background-image:url(http://127.0.0.1:8090/Goods_shop1/imgs/theme_p/a5.jpg)"
        ]
      };
    }
  }
</script>
