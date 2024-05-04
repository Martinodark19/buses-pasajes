import { createApp } from 'vue';
import router from './routes'; 
import indexPasajesComponent from './components/indexPasajesComponent.vue';

createApp(indexPasajesComponent).use(router).mount('#app');

