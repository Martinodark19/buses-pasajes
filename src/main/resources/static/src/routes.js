import { createRouter, createWebHistory } from 'vue-router';
import crearViajesComponent from './components/crearViajesComponent.vue';
import viajesComponent from './components/viajes/viajesComponent.vue';
import PruebaComponent from './components/pruebaComponent.vue';


const router = createRouter({
    history: createWebHistory(),
    routes: 
    [
        {
            path: '/crearViajes',
            component: crearViajesComponent,
        },
      
        {
            name: 'viajesComponent',
            path: '/viajes',
            component:viajesComponent,
        },
        {
            name: 'prueba',
            path: '/prueba',
            component:PruebaComponent,
        }
    ]
  });


  
  export default router;