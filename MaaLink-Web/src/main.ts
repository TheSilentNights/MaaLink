import { createApp } from 'vue'
import './style.css'
import MaaLink from "./MaaLink.vue";
import ElementPlus from "element-plus"

const maaLink = createApp(MaaLink)

maaLink.use(ElementPlus)
maaLink.mount("#app")
