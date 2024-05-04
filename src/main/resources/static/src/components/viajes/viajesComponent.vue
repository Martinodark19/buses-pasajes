<template>

<div>
        <h1>hola aqui se mostraran los viajes</h1>
       


    <!--
        <h5>{{this.$route.query.ciudad_de_inicio }}</h5>
         <div v-for="(viaje, index) in viajes" class="card" :key="index">
        <div class="card-body">
            <h5 class="card-title">Precio {{viaje.precio}}</h5>
            <p><strong>Identificador de bus:</strong> {{ viaje.identificador_bus }}</p>
            <p><strong>Hora de salida:</strong> {{ viaje.hora }}</p>
            <p><strong>Marca del bus:</strong> {{ viaje.marca }}</p>
            <p><strong>Fecha de viaje:</strong> {{ viaje.fecha_viaje }}</p>
            <p><strong>Ciudad de inicio:</strong> {{ viaje.ciudad_inicio }}</p>
            <p><strong>Ciudad de destino:</strong> {{ viaje.ciudad_destino }}</p>
            <p><strong>Terminal de inicio:</strong> {{ viaje.terminal_inicio }}</p>
            <p><strong>Terminales de destino:</strong> {{ viaje.terminales_destino }}</p>
        </div>
    <button class="boton-comprar">Comprar</button>

    </div>
    -->
 




</div>
</template>

<script>
import axios from 'axios'

export default

{

    data(){
        return{
            viajes: "",
        }
    },


    mounted()
    {
        axios.post('/mostrarViajes',{
            ciudad_de_inicio:this.$route.query.ciudad_de_inicio,
            ciudad_de_destino: this.$route.query.ciudad_de_destino,
            fecha_viaje: this.$route.query.fecha_viaje
        }).then(response => {
            console.log(response)
            // aqui mostraremos desde el backend, debemos esperar la respuesta del backend con los viajes para consumirla
            
          
        })
        .catch(error=>{
            console.log(error)
        })
    }


   
}
</script>



<style>
.card {
    background-color: aqua;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-body {
    padding: 20px;
}

.card-title {
    color: #333;
    font-size: 20px;
    margin-bottom: 15px;
}

.card-body p {
    margin: 10px 0;
    font-size: 16px;
}

.boton-comprar {
    background-color: #4CAF50; /* Color de fondo verde */
    border: none; /* Sin borde */
    color: white; /* Color del texto blanco */
    padding: 15px 32px; /* Espacio interno (padding) */
    text-align: center; /* Texto centrado */
    text-decoration: none; /* Sin subrayado */
    display: inline-block; /* Mostrar como bloque en línea */
    font-size: 16px; /* Tamaño de la fuente */
    margin: 4px 2px; /* Margen externo */
    cursor: pointer; /* Cambiar el cursor al pasar sobre el botón */
    border-radius: 10px; /* Bordes redondeados */
    transition-duration: 0.4s; /* Duración de la transición */
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2); /* Sombra */
    width: 25%;
}

.boton-comprar:hover {
    background-color: #45a049; /* Cambia el color de fondo al pasar el mouse */
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2); /* Cambia la sombra al pasar el mouse */
}



</style>
