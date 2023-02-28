import { createApp } from 'vue'
import App from './App.vue'
import Vue from 'vue';
import router from './router';


createApp(App).use(router).mount('#app')

Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App)
}).$mount('#app');