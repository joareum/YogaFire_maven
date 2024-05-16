<template>
  <div class="inner">
    <h2>아이디 찾기</h2>
    <p>계정에 등록된 이메일을 인증하시면 <br/>사용중인 계정의 아이디를 알려드립니다.</p>

    <form @submit.prevent="validateAuthCode">
      <div class="category email-category">
        <label for="email">이메일<span style="color: #dc3545">*</span></label>
        <div class="category-container">
          <input type="text" id="email" v-model="email" :class="{ 'field-error': emailError }" placeholder="이메일 입력"/>
          <button type="button" @click="requestAuth">인증요청</button>
        </div>
        <div class="field-error">{{ emailErrorMessage }}</div>
      </div>

      <h4 class="auth-request-message">{{ authRequestMessage }}</h4>

      <div class="category auth-category">
        <label for="auth-code"></label>
        <div class="category-container">
          <input type="text" id="auth-code" v-model="authCode" :class="{ 'field-error': authCodeError }" placeholder="인증번호 입력"/>
          <button type="submit">인증</button>
        </div>
        <div class="field-error">{{ authCodeErrorMessage }}</div>
      </div>

      <h4 class="auth-confirm-message">{{ authConfirmMessage }}</h4>
    </form>
  </div>
</template>

<script>
// axios 라이브러리 가져오기
import axios from 'axios';

export default {
  data() {
    return {
      email: '',
      authCode: '',
      emailError: false,
      authCodeError: false,
      emailErrorMessage: '',
      authRequestMessage: '',
      authConfirmMessage: '',
      authCodeErrorMessage: '',
    };
  },
  methods: {
    async requestAuth() {
      try {
        if (!this.hasEmailValue()) {
          this.emailError = true;
          this.emailErrorMessage = '이메일을 입력하세요';
          return;
        } else if (!this.checkEmailRegex()) {
          this.emailError = true;
          this.emailErrorMessage = '유효한 이메일을 입력하세요';
          return;
        }

        // axios를 사용하여 HTTP GET 요청 보내기
        const response = await axios.get('/email/auth', {
          params: {
            address: this.email
          }
        });

        // 응답 데이터 처리
        const result = response.data;
        let isSuccess = result.success;
        let message = result.message;

        if (isSuccess) {
          this.$el.querySelector('#email').style.borderColor = 'green';
        } else {
          this.$el.querySelector('#email').style.borderColor = 'red';
        }

        this.authRequestMessage = message;
      } catch (error) {
        console.error('Error occurred while sending GET request:', error);
        // 에러 처리
      }
    },

    async validateAuthCode() {
      try {
        if (!this.hasAuthCodeValue()) {
          this.authCodeError = true;
          this.authCodeErrorMessage = '인증번호를 입력하세요';
          return;
        }

        // axios를 사용하여 HTTP POST 요청 보내기
        const response = await axios.post('/email/auth', {
          address: this.email,
          authCode: this.authCode
        });

        // 응답 데이터 처리
        const result = response.data;
        let isSuccess = result.success;
        let message = result.message;

        if (isSuccess) {
          this.$el.querySelector('#auth-code').style.borderColor = 'green';
        } else {
          this.$el.querySelector('#auth-code').style.borderColor = 'red';
        }

        this.authConfirmMessage = message;
      } catch (error) {
        console.error('Error occurred while sending POST request:', error);
        // 에러 처리
      }
    },

    hasEmailValue() {
      return this.email.trim() !== '';
    },

    checkEmailRegex() {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(this.email);
    },

    hasAuthCodeValue() {
      return this.authCode.trim() !== '';
    }
  }
};
</script>

<style scoped>
.field-error {
  color: #dc3545;
}
</style>
