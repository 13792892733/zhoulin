<style>
  .enroll_page{
    width: 100%;
    height: 755px;
    
  }
  .enroll_table{
    width: 100%;
    height: 100px;
    float: left;
    background-color: white;
  }
  .logo_table_logo{
    width: 130px;
    height: 100%;
    background-image: url(../router/mg.png);
    background-repeat: no-repeat;
    background-size: 100%;
    background-position: 0px,-20px;
    margin-left: 40px;

  }
  .dv_bottom_p{
    width: 100%;
    height:650px;
    float: left;
    background-image: url(../router/di.png);
    background-size: 100% 90%;
    background-repeat: no-repeat;


  }
  .input_enroll{
    width: 400px;
    height: 470px;
    float: right;
    margin-top: 30px;
    margin-right: 50px;
    opacity: 0.9;
    background-color: white;
    border-radius: 10px;
  }
  .input_enroll table{
    width: 320px;
    margin-left: 40px;
    margin-top: 25px;
    text-align: center;
  }
  .input_enroll tr{
    height: 58px;
  }
  .input_enroll input{
    height: 50px;
  }
  .input_enroll button{
    width: 300px;
    height: 50px;
    background-color: orchid;
    color: white;
    font-size: 20px;
    border-radius: 5px;
    font-size: 26px;
  }
</style>
<template>
  <div class="enroll_page">
    <!-- 标题-->
    <div class="enroll_table">
      <div class="logo_table_logo"></div>
    </div>
    <!-- 底图-->
    <div class="dv_bottom_p">
      <!-- 注册框-->
      <div class="input_enroll">
        <table>
          <tr v-if="showmp" style="background-color: pink; color: black;">
            <td>{{mp}}</td>
          </tr>
          <tr>
            <input type="text" class="form-control" placeholder="请输入用户名" v-model="logname"/>
          </tr>
          <tr>
            <input type="password" class="form-control" placeholder="请输入6位密码" v-model="password" maxlength="6"/>
          </tr>
          <tr>
            <input type="password" class="form-control" placeholder="请再次输入密码" v-model="password2" maxlength="6"/>
          </tr>
          <tr>
            <input type="text" class="form-control" placeholder="请输入验证码" v-model="keycold"/>
          </tr>
          <tr>
            <img id="imgyzm" @click="changkeychod($event)" src="http://127.0.0.1:8090/Goods_shop1/defaultKaptcha" />
            &nbsp;  &nbsp;  &nbsp;
            <a>看不清，换一张</a>
          </tr>
          <tr>
           <button class="btn" @click="doinsert()">注&nbsp; &nbsp; 册</button>
          </tr>

        </table>


      </div>
    </div>


  </div>
</template>

<script>
  export default{
    data(){
      return{
        logname:"",
        password:"",
        password2:"",
        keycold:"",
        errorarry:[
          "验证码错误","注册成功","用户名已存在"
        ],
        mp:"",
        showmp:false
      };
    },
    watch:{
      logname:function(){
        if(this.logname!=""||this.logname.length>0){
          this.showmp=false;
          this.mp="";
        }
      },
      password:function(){
        if(this.password!=""||this.password.length>0){
          this.showmp=false;
          this.mp="";
        }
      },
      keycold:function(){
        if(this.keycold!=""){
          this.showmp=false;
          this.mp="";
        }
      },
      keycold:function(){
        if(this.password=this.password2){
          this.showmp=false;
          this.mp="";
        }else{
          this.mp="密码两次输入不一致";
          this.showmp=true;
        }
      }

    },
    methods:{
      changkeychod(event){
        event.target.src="http://127.0.0.1:8090/Goods_shop1/defaultKaptcha";
      },
      doinsert(){
        var ob =this;
        if(ob.logname.length==0){
          ob.mp="用户名不能为空"
          ob.showmp=true;
          return;
        }
        if(ob.password.length==0){
          ob.mp="密码不能为空"
          ob.showmp=true;
          return;
        }
        if(ob.keycold.length==""){
          ob.mp="验证码不能为空"
          ob.showmp=true;
          return;
        }
        if(ob.password!=ob.password2){
          ob.mp="两次输入的密码不一致"
          ob.showmp=true;
          return;
        }
        var url="http://127.0.0.1:8090/Goods_shop1/userinfo/doinsert"
        $.ajax(url,{
          data:{
            logname:ob.logname,
            password:ob.password,
            keycold:ob.keycold
          },
          success:function(result){
           ob.mp=ob.errorarry[result.msg];
           ob.showmp=true;
           if(result.mp!=1){
              $("#imgyzm")[0].src="http://127.0.0.1:8090/Goods_shop1/defaultKaptcha";
           }
          },

          // 和服务器保存在一个session对象中
          xhrFields:{
            withCredentials:true
          }
        });

      }
    }

  }
</script>
