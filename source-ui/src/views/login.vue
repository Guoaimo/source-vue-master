<template>
  <div class="login">
    <h2 class="main-title"><span>郭爱默著</span></h2>
    <h3 class="sub-title">学海无涯、学无止境、学以致用</h3>
    <div class="login-box">
      <div class="pc">
        <div class="title">登  录</div>
        <el-form
          ref="loginForm"
          :model="loginForm"
          :rules="loginRules"
          class="login-form"
        >
          <el-form-item prop="username">
            <el-input
              v-model="loginForm.username"
              type="text"
              auto-complete="off"
              placeholder="账号"
            >
              <svg-icon
                slot="prefix"
                icon-class="user"
                class="el-input__icon input-icon"
                style="color: #e6e6e6"
              />
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input
              v-model="loginForm.password"
              type="password"
              auto-complete="off"
              placeholder="密码"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon
                slot="prefix"
                icon-class="password"
                class="el-input__icon input-icon"
              />
            </el-input>
          </el-form-item>
          <el-form-item prop="code" v-if="captchaOnOff">
            <el-input
              v-model="loginForm.code"
              auto-complete="off"
              placeholder="验证码"
              style="width: 63%"
              @keyup.enter.native="handleLogin"
            >
              <svg-icon
                slot="prefix"
                icon-class="validCode"
                class="el-input__icon input-icon"
              />
            </el-input>
            <div class="login-code">
              <img :src="codeUrl" @click="getCode" class="login-code-img" />
            </div>
          </el-form-item>
          <el-checkbox
            v-model="loginForm.rememberMe"
            style="margin: 0px 0px 25px 0px"
            >记住密码</el-checkbox
          >
          <el-form-item style="width: 100%">
            <el-button
              :loading="loading"
              size="medium"
              type="primary"
              style="width: 100%"
              @click.native.prevent="handleLogin"
            >
              <span v-if="!loading">登 录</span>
              <span v-else>登 录 中...</span>
            </el-button>
            <div style="float: right" v-if="register">
              <router-link class="link-type" :to="'/register'"
                >立即注册</router-link
              >
            </div>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <!--  底部  -->
    <div class="el-login-footer">
      <span>
        <a href="https://jinlaohei.com" target="_blank"
          >Copyright © 2022-2023 郭爱默著 Open Source Byte All Rights
          Reserved.</a
        >
      </span>
    </div>
  </div>
</template>

<script>
import { Notification } from "element-ui";
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from "@/utils/jsencrypt";
import "element-ui/lib/theme-chalk/display.css";

export default {
  name: "Login",
  data() {
    return {
      // wsuri: "ws://127.0.0.1:8088/websocket/message",
      codeUrl: "",
      loginForm: {
        username: "admin",
        password: "123456",
        rememberMe: false,
        code: "",
        uuid: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", message: "请输入您的账号" },
        ],
        password: [
          { required: true, trigger: "blur", message: "请输入您的密码" },
        ],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }],
      },
      loading: false,
      // 验证码开关
      captchaOnOff: true,
      // 注册开关
      register: false,
      redirect: undefined,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true,
    },
  },
  async created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    getCode() {
      getCodeImg().then((res) => {
        this.captchaOnOff =
          res.captchaOnOff === undefined ? true : res.captchaOnOff;
        if (this.captchaOnOff) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password:
          password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe),
      };
    },
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), {
              expires: 30,
            });
            Cookies.set("rememberMe", this.loginForm.rememberMe, {
              expires: 30,
            });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove("rememberMe");
          }
          this.$store
            .dispatch("Login", this.loginForm)
            .then(() => {
              this.$router.push({ path: this.redirect || "/" }).catch(() => {});
            })
            .catch(() => {
              this.loading = false;
              if (this.captchaOnOff) {
                this.getCode();
              }
            });
        }
      });
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  display: flex;
  // justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 100%;
  background-image: url("../assets/images/login-background2.jpg");
  background-size: cover;
  background-color: #f4f4f4;
}
.login-logo {
  width: 200px;
  height: 24px;
  margin-right: 10px;
  padding-right: 10px;
  background-image: url("../assets/logo/logo_w.png");
  background-repeat: no-repeat;
  background-size: auto 24px;
  display: inline-block;
  vertical-align: middle;
  zoom: 1;
  position: absolute;
  left: 150px;
  top: 50px;
  span {
    line-height: 24px;
    font-size: 20px;
    height: 24px;
    padding-left: 30px;
    color: #ffffff;
    vertical-align: middle;
    font-weight: 600;
  }
}
.main-title {
  margin: 100px 0 0 0;
  height: 34px;
  text-align: center;
  color: #ffffff;
  background-repeat: no-repeat;
  background-image: url("../assets/logo/logo_w.png");
  background-size: auto 34px;
  display: inline-block;
  vertical-align: middle;
  zoom: 1;
  span {
    line-height: 34px;
    font-size: 28px;
    height: 34px;
    padding-left: 40px;
    color: #ffffff;
    vertical-align: middle;
    font-weight: 500;
  }
}
.sub-title {
  margin: 20px 0 60px 0;
  text-align: center;
  color: #ffffff;
  font-size: 18px;
}

.login-box {
  border-radius: 6px;
  width: 400px;
  background: #ffffff;
  position: relative;
  margin: 0 auto;
  padding: 30px 0 0 0;
  -webkit-box-shadow: 0px 1px 12px 0px rgba(0, 0, 0, 0.2);
  box-shadow: 0px 1px 12px 0px rgba(0, 0, 0, 0.2);
  .weixin,
  .pc {
    .title {
        text-align: center;
    }
    .login-switch {
      width: 53px;
      height: 53px;
      position: absolute;
      right: 10px;
      top: 10px;
      cursor: pointer;
      .static-img {
        width: 53px;
        height: 53px;
        display: block;
      }
    }
    .open-weixin {
      margin: 0 auto;
      padding: 30px;
      text-align: center;
      img {
        width: 200px;
        border: 1px solid transparent;
      }
      .info {
        margin: 0 auto;
        font-size: 13px;
        color: #999999;
        text-align: center;
      }
    }
  }
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 20px 40px 20px 40px;
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
</style>
