<template>
  <div class="dv_top2">
    <div class="dv_top2_logo">
      <img src="../assets/mgjlogo.png" />
    </div>
    <div class="dv_top2_input">
      <input type="text" class="dv_top2_input_text" @keyup.38="chosebykeyup(-1)" @keyup.40="chosebykeyup(1)" @input="getgoodsbykeyword()"
        v-model="keyword" />
      <button class="dv_top2_input_btn"><i class="fa fa-search fa-2x"></i></button>
      <div class="dv_top2_show" v-for="(k,i) in keywordlist" :class="{'dv_top2_movecolor':i==listindex}">{{k.gtkeywords}}</div>
    </div>


  <!-- </div>按键上下选择下拉框内容 -->

</template>

<script>
  export default {
    data: function() {
      return {
        keyword: "",
        keywordlist: [],
        listindex: -1
      }
    },
    methods: {
      chosebykeyup(num) {
        this.listindex += num;
        if (this.listindex < 0) {
          this.listindex = (this.keywordlist.length - 1);
        }
        if (this.listindex > (this.keywordlist.length-1)) {
          this.listindex = 0;
        }
        this.keyword=this.keywordlist[this.listindex].gtkeywords;
      },
      getgoodsbykeyword() {
        var po = this;
        if (po.keyword == "") {
          po.keywordlist = [];
          return;
        }
        var url = "http://127.0.0.1:8082/backstage/goodsinfocontroller/selectbykeyword";
        $.ajax(url, {
          data: {
            "keyword": po.keyword
          },
          success: function(result) {
            po.keywordlist = result;
          }
        });
      }

    }
  }
</script>

<style>
  .dv_top2_logo{
    width: 200px;
    height: 100%;
    float: left;
  }
  .dv_top2_logo img{
    width: 130px;
    height: 100px;
  }
  .dv_top2 {
    width: 1200px;
    height: 100px;
    margin: auto;
    position: relative;
  }

  .dv_top2_input {
    width: 552px;
    height: 48px;
    background-color: #F7F7F7;
    margin-left: 250px;
    float: left;
    margin-top: 30px;
    border-radius: 9px;
  }

  .dv_top2_input_text {
    height: 100%;
    width: 492px;
    border: 0px;
    background-color: transparent;
    outline: none;
    padding-left: 20px;
  }

  .dv_top2_input_btn {
    height: 100%;
    width: 56px;
    border-radius: 0px 9px 9px 0px;
    border: 0px;
    background-color: #8D8D8D;
  }

  .dv_top2_show {
    width: 552px;
    background-color: white;
    opacity: 0.97;
    margin-top: ;
    position: relative;
    z-index: 12;
    padding-left: 20px;
    line-height: 25px;
  }

  .dv_top2_show:last-child {
    padding-bottom: 20px;
  }

  .dv_top2_movecolor {
    background-color: hotpink;
    color: white;
  }
</style>
