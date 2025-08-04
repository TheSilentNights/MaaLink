<script setup lang="ts">
import {ref} from "vue";
import {sendPost} from "../ts/request/requests.ts";
import serverUrlStorge from "../ts/storge/serverUrlStorge.ts";
import StatusCode from "../ts/storge/StatusCode.ts";
import router from "../ts/router/mainRouter.ts";

const loginForm = ref({
    username: '',
    password: '',
})

async function login() {
    console.log(loginForm.value)
    console.log(JSON.stringify(loginForm.value));
    const promise = await sendPost(serverUrlStorge().login, {
        'username': loginForm.value.username,
        'password': loginForm.value.password
    }, false);
    console.log(promise)
    if (promise.data.code == StatusCode.ACTION_SUCCEED) {
        console.log(promise.data)
        localStorage.setItem("token", promise.data.data);
        await router.push("/home")
    }
    return false;
}
</script>

<template>
    <div class="login">
        <div class="login-box">
            <div class="login-title font-color">Login</div>
            <form @submit.prevent="login()">
                <div class="form-group font-color username">
                    <input type="text" id="username" v-model="loginForm.username" placeholder="用户名" required>
                </div>
                <div class="form-group font-color password">
                    <input type="password" id="password" v-model="loginForm.password" placeholder="密码" required>
                </div>
                <button type="submit" id="submit-button">登录</button>
            </form>
        </div>
    </div>

</template>

<style scoped lang="less">
.login {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;

    .login-box {
        align-self: center;
        width: 40rem;
        height: 30rem;
        background-color: rgba(255, 255, 255, 0.6);
        flex-direction: column;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 1.5rem;

        .login-title {
            font-size: 2rem;
            position: relative;
            top: 70px;
        }

        form {
            width: 100%;
            display: flex;
            height: 100%;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            position: relative;
            bottom: 40px;

            :deep(input:-webkit-autofill) {
                background-color: transparent !important;
                transition: background-color 5000s ease-in-out 0s !important;
                -webkit-box-shadow: 0 0 0 400px transparent inset;
            }

            input {
                outline: none;
                border: none;
                border-bottom: 1px solid rgba(166, 84, 253, 0.78);
                background-color: rgba(255, 255, 255, 0);
                width: 80%;
                height: 50px;
                font-size: 18px;
                color: rgba(166, 84, 253, 0.78);
            }

            .form-group {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 70%;
                outline: none;
                background-color: rgba(0, 0, 0, 0);
            }

            .username {
                margin-top: 20px;
                margin-bottom: 30px;
            }

            #submit-button {
                position: relative;
                top: 50px;
                background-image: linear-gradient(120deg, #e0c3fc 0%, #8ec5fc 100%);
                border: 0;
                color: white;
                width: 35%;
                height: 9%;
                font-size: 16px;
                border-radius: 20px;
            }

            #submit-button:hover {
                background-image: linear-gradient(120deg, #8ec5fc 0%, #e0c3fc 100%);
                cursor: pointer;
            }

        }


    }
}

.font-color {
    color: rgba(166, 84, 253, 0.78);
}

</style>