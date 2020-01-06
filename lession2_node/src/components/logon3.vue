<!-- 注册页面 -->
<style scoped="scoped">

    .dv_rgst{
      width: 400px;min-height: 400px;padding: 40px;
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
</style>


<template>

  <div class="container" style="">
    <div style="height: 80px;background-color: #FFFFFF;"></div>

    <div style="height: 500px;background-image: url(../../static/timg.jpg);background-position: -500px -100px;  ">
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
                <input type="text" class="form-control" v-model="logname" placeholder="请填写用户名">

              </td>

          </tr>

            <tr>
                <td>
                  <input type="password"  class="form-control" v-model="password"  placeholder="请输入密码:6-12位">

                </td>

            </tr>

           <tr>
               <td>
                 <input v-model="password_repeat" type="password"  class="form-control"  placeholder="请确认密码">

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
                    style="background-color: #FE5F7B;border: 0px;border-radius: 0px;height: 40px;">注册</button>


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
        password_repeat:"",
        keycode:"",
        msg:"",
        showmsg:false,
        msgtext:[
           "注册成功","用户名已存在","验证码有误","密码不一致"


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

       if(ob.password!=ob.password_repeat){

         ob.msg="密码两次输入不一致";
         ob.showmsg=true;
         return;
       }

       if(ob.keycode.length==""){

         ob.msg="请输入验证码";
         ob.showmsg=true;
         return;
       }


        var url="http://192.168.1.157:8085/project_mgj/ReSupplier";
        $.ajax(url,{
          data:{
            spname:ob.logname,
            password:ob.password,
            code:ob.keycode,
            password_re:ob.password_repeat
          },
          success:function(result){
             alert(ob.msgtext[result.msg]);
             ob.msg=ob.msgtext[result.msg];
             ob.showmsg=true;
             if(result.msg!=1){
               $("#sys_keycode")[0].src="http://192.168.1.157:8085/project_mgj/KaptchaController/defaultKaptcha";
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
