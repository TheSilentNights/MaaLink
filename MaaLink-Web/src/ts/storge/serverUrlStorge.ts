import {defineStore} from "pinia";

const useServerUrlStore = defineStore("serverUrl", {
    state: () => ({
        serverUrl: "http://localhost:8080",
    }),
    getters: {
        status(state) {
            return state.serverUrl + "/api/v1/status/";
        },
        login(state) {
            return state.serverUrl + "/api/v1/login/";
        },
        appendTask(state) {
            return state.serverUrl + "/api/v1/appendTask/";
        },
        start(state) {
            return state.serverUrl + "/api/v1/start/";
        }
    },
    actions: {},
});

export default useServerUrlStore;