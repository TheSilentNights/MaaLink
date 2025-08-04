import {createApp} from 'vue'
import './style.css'
import 'element-plus/dist/index.css'
import MaaLink from "./MaaLink.vue";
import ElementPlus from "element-plus"
import mainRouter from "./ts/router/mainRouter.ts";
import {createPinia} from "pinia";

const maaLink = createApp(MaaLink)

maaLink.use(ElementPlus)
maaLink.use(mainRouter)
maaLink.use(createPinia())
maaLink.mount("#app")
