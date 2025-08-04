import {createRouter, createWebHistory, type Router} from "vue-router"
import MainPage from "../../components/MainPage.vue";
import LoginPage from "../../components/LoginPage.vue";
import {sendPrimary} from "../utils/elementMessage.ts"

const routes = [
    {
        path: "/",
        redirect: "/home"
    },
    {
        name: "Login",
        path: "/login",
        component: LoginPage
    },
    {
        name: "Home",
        path: "/home",
        component: MainPage,
        meta: {
            requiresAuth: true
        }
    }
]


const router: Router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to) => {
    if (to.meta.requiresAuth && !isLoggedIn()) {
        sendPrimary("You haven't logged in or your token has expired")
        router.push("/login")
    } else {
        return true
    }
})

router.onError((error) => {
    console.log(error)
})

function isLoggedIn(): boolean {
    return localStorage.getItem("token") !== null;
}

export default router