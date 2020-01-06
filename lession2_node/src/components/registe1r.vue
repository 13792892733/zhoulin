<!-- 点击进入登录和注册总界面 -->
<style scoped="scoped">

    .dv_rgst{
      width: 400px;min-height: 300px;padding: 40px;
      background-color:#FFFFFF ;margin: auto;
      opacity: 0.92;
    }

    .dv_rgst .table tr{
      height: 50px;
    }
    .dv_rgst .table tr .form-control{
      border-radius: 0px;
      height: 40px;
    }

    .dv_login{
      height: 500px;background-image: url(../../static/timg.jpg);background-position: -500px -100px;
      background-size: 100%;
      background-position-x: 10px;
      background-repeat: no-repeat;
    }


</style>


<template>

  <div class="container" style="">
    <div style="height: 40px;background-color: #FFFFFF;"></div>

    <div class="dv_login">
      <br/>      <br/>      <br/>
      <div class="dv_rgst pull-right" style="margin-right: 50px;" >

          <table class="table " >

            <tr v-if="showmsg" >
                <td>
                   {{msg}}
                </td>

            </tr>

            <tr >
                <td>
                  <input type="text" class="form-control" v-model="logname" placeholder="请填写商家名">

                </td>

            </tr>

              <tr>
                  <td>
                    <input type="password"  class="form-control" v-model="password"  placeholder="请输入密码:6-12位">

                  </td>

              </tr>

             <tr>
                 <td>
                   <input type="text" v-model="keycode"
                    class="form-control pull-left"  placeholder="验证码"
                     style="width:40%;margin-right: 20px;">

                     <img @click="changeKeycode($event)" id="sys_keycode" class="pull-left" src="http://192.168.1.157:8085/project_mgj/KaptchaController/defaultKaptcha" />

                 </td>

             </tr>

                <tr>
                    <td>
                      <button class="btn btn-danger btn-block" @click="doRegiste()"
                      style="background-color: #FE5F7B;border: 0px;border-radius: 6px;height: 40px;">登录</button>
                    </td>
                </tr>

                <tr>
                    <td>
                      <button class="btn btn-danger btn-logon " @click="doLogon()"
                      style="background-color: blue;border: 1px;border-radius: 6px;height: 40px;width: 350px;">注册</button>
                    </td>
                </tr>
          </table>

      </div>
    </div>

    <br/><br/>

  </div>
</template>

<script>


  export default{

    data(){
      return {
        logname:"",
        password:"",
        keycode:"",
        msg:"",
        showmsg:false,
        msgtext:[
            "验证码有误","ok","用户不存在","用户已被禁用，如有疑问请联系客服","密码错误"
        ]
      };

    },

    watch:{
      logname:function(){

        if(this.logname!=""||this.logname.length>0){
          this.showmsg=false;
          this.msg="";
        }

      },
      password:function(){

        if(this.password!=""||this.password.length>0){
          this.showmsg=false;
          this.msg="";
        }

      },
      password_repeat:function(){

        if(this.password_repeat==this.password){
          this.showmsg=false;
          this.msg="";
        }else{
          this.msg="密码两次输入不一致";
          this.showmsg=true;
        }

      },
      keycode:function(){

        if(this.keycode!=""){
          this.showmsg=false;
          this.msg="";
        }

      },



    },
    methods:{

      changeKeycode(event){

        event.target.src="http://192.168.1.157:8085/project_mgj/KaptchaController/defaultKaptcha";

      },
      doLogon(){
        this.$router.push("/logon3");
      },
      doRegiste(){
        var ob=this;

        if(ob.logname.length==0){

          ob.msg="用户名不能为空";
          ob.showmsg=true;
          return;
        }
        if(ob.password.length==0){

          ob.msg="密码不能为空";
          ob.showmsg=true;
          return;
        }

       if(ob.keycode.length==""){

         ob.msg="请输入验证码";
         ob.showmsg=true;
         return;
       }


         var url = "http://192.168.1.157:8085/project_mgj/IntoSupplier"
          $.ajax(url,{
            data:{
              spname:ob.logname,
              password:ob.password,
              code:ob.keycode

            },
            success:function(result){
              if(result.msg==1){
                ob.$router.back(-1);


              }else{
                ob.msg=ob.msgtext[result.msg];
                ob.showmsg=true;
                if(result.msg!=1){
                  $("#sys_keycode")[0].src="http://192.168.1.157:8085/project_mgj/KaptchaController/defaultKaptcha";
                }
              }

            }
            ,
            xhrFields:{
              withCredentials:true
            }
          });
      },





    }


  }



</script>
