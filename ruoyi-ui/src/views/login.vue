<template>
  <div class="login">
    <div v-if="flag_video" class="video_style">
      <video2  @ended="videoEnd()"/>
    </div>
    <!-- <audio src="@/assets/music/todokanaikoi.mp3" autoplay loop preload="auto">届不到的爱恋</audio> -->
    
    <el-tabs v-model="activeTab" class="custom-tab-pane">
      <el-tab-pane label="账号密码登录" name="first" >
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
          <h3 class="title">NDSC博客平台</h3>
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              type="text"
              auto-complete="off"
              placeholder="账号"
            >
              <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              :type="showPassword ? 'text' : 'password'"
              auto-complete="off"
              placeholder="密码"
              @keyup.enter.native="handleLogin('loginForm')"
            >
              <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
              <el-button slot="append" @click="toggleShowPassword" style="color: blue;">{{ showPassword ? '隐藏' : '显示' }}</el-button>
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaEnabled">
            <el-input
              v-model="loginForm.code"
              auto-complete="off"
              placeholder="验证码"
              style="width: 63%"
              @keyup.enter.native="handleLogin('loginForm')"
            >
              <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img"/>
            </div>
          </el-form-item>
          <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;">记住密码</el-checkbox>
          <el-form-item style="width:100%;">
            <el-button
              :loading="loading"
              size="large"
              type="primary"
              style="width:100%;"
              @click.native.prevent="handleLogin('loginForm')"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
          </el-form-item>
          <div v-if="captchaEnabled" class="centered-text">
            <router-link class="link-type smaller-text" :to="'/register'" >还没有账号？立即注册</router-link>
          </div>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="微信扫码登录" name="second" >
        <el-form ref="loginForm2" :model="loginForm2" :rules="loginRules" class="login-form">
          <h3 class="title">NDSC博客平台</h3>
             
            <img src="../assets/images/WechatLogin-1.jpg" 
              alt="NDSC Blog Platform" 
              style="display: block; margin: 0 auto; width: 250px; height: auto;
              padding: 5px 15px 25px 5px"
              />

          <el-form-item style="width:100%;">
            <el-button
              type="success"
              :loading="loading"
              size="large"
              style="width:100%; "
              @click.native.prevent="handleLogin('loginForm2')"
            >
            
              <span v-if="!loading">微 信 登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
          </el-form-item>
          <div v-if="captchaEnabled" class="centered-text">
            <router-link class="link-type smaller-text" :to="'/register'" >还没有账号？立即注册</router-link>
          </div>
        </el-form>
      </el-tab-pane>

      <el-tab-pane label="QQ扫码登录" name="third" >
        <el-form ref="loginForm2" :model="loginForm2" :rules="loginRules" class="login-form">
          <h3 class="title">NDSC博客平台</h3>
             
            <img src="../assets/images/Genshin.png" 
              alt="NDSC Blog Platform" 
              style="display: block; margin: 0 auto; width: 220px; height: auto;
              padding: 5px 15px 27px 5px"
              />

          <el-form-item style="width:100%;">
            <el-button
              color="purple"
              :loading="loading"
              size="large"
              style="width:100%; "
              @click.native.prevent="handleLogin('loginForm2')"
            >
              <span v-if="!loading"> Q Q 登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
          </el-form-item>
          <div v-if="captchaEnabled" class="centered-text">
            <router-link class="link-type smaller-text" :to="'/register'" >还没有账号？立即注册</router-link>
          </div>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt'
import video2 from "./video2.vue";

export default {
  components:{
    video2,
  },
  name: "Login",
  data() {
    return {
      flag_video:false,
      codeUrl: "",
      activeTab: 'first',
      loginForm1: {
        username: "admin",
        password: "admin123",
        code: '',
        rememberMe: false
      },
      loginForm2: {
        username: "admin",
        password: "admin123",
        code: '',
        rememberMe: false
      },
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" }
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" }
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      // 验证码开关
      captchaEnabled: true,

      redirect: undefined,
      // 用于控制密码显示与隐藏的状态
      showPassword: false ,

    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  mounted() {
    setTimeout(() => {
      this.showContent = true;
    }, 500); // 500ms delay
  },
  methods: {
    videoEnd(){
      console.log("fjahoaiiiiiiii");
      console.log("fjahoaiiiiiiii");
      console.log("fjahoaiiiiiiii");
      console.log("fjahoaiiiiiiii");
      console.log("fjahoaiiiiiiii");
      console.log("fjahoaiiiiiiii");
    },
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
            this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password: password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
      };
    },
    toggleShowPassword() {
      this.showPassword = !this.showPassword;
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          
          
          setTimeout(() => {
            // 方法区
            this.$store.dispatch("Login", this.loginForm).then(() => {
              this.flag_video=true;
              setTimeout(() => {
            this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
          }, 14000);
          }).catch(() => {
            this.loading = false;
            if (this.captchaEnabled) {
              this.getCode();
            }
          });
          	// 过500毫秒调用
          }, 200);
        }
      });
    },
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  background-image: url("../assets/images/login-background.jpg");
  background-size: cover;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}
.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
.el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
.input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 38px;
}
.smaller-text {
  font-size: 15px;
}
.centered-text {
  text-align: center;
  margin-bottom: 2ch;
}

.custom-tab-pane {
  border-radius: 6px;
  background-color: #ffffff; 
  padding: 15px 15px 5px 15px;
  opacity: 0.90;
}
.video_style{
  position:absolute;
  width: 1530px;
  // height: 100px;
  max-height: 200px;
  z-index: 5;
  top: 0px;
}
</style>