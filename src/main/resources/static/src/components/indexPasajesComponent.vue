<template>
<div id="app">

    <form v-on:submit.prevent="getForm(ciudadDePartidaLista,ciudadDeSalidaLista,fechaSeleccionada)" >

      <div class="form-group col-md-4">
        <label>desde donde partes?</label>
        <input class="inputInicio" list="ciudadesInicio" placeholder="ciudad deseada" required v-model="ciudadDePartida" v-on:blur="verificarCiudadInicio()" v-on:click="borrarCiudadesInput()" > 

        <datalist id="ciudadesInicio">
          <option v-for="(ciudad, index) in ciudades" :key="index">
            {{ciudad}}
          </option>
        </datalist>
        <div v-if="msgErrorInicio">{{ msgErrorInicio }}</div>
        <div v-else></div>

      </div>

      <div class="form-group col-md-4">
        <label>hacia donde quieres ir?</label>
        <input class="inputDestino" list="ciudadesDestino" placeholder="ciudad deseada" required v-model="ciudadDeSalida" v-on:blur="verificarCiudadSalida()">

        <datalist id="ciudadesDestino">
          <option v-for="(ciudad, index) in ciudadesDisponiblesSalida" :key="index">
            {{ciudad}}
          </option>
        </datalist>
        <div v-if="msgErrorDestino">{{ msgErrorDestino }}</div>
        <div v-else></div>


      </div>

      <div>
        <label for="datepicker">Selecciona una fecha:</label>
        <input type = "date" v-model="fechaSeleccionada">
      </div>



      <!--este form enviara los datos de la ciudad de inicio y la ciudad de destino para poder filtrar los buses disponibles-->
      <button type="submit">Buscar</button>
    </form>
    
    <router-view ></router-view>

</div>

</template>


<script>
import axios from 'axios'

export default
{  

 


  data(){
    return{
        ciudades: ["Angol","Arauco","Bulnes","Cabrero","Cañete","Carampangue","Cauquenes","Chiguayante","Chillan","Cobquecura","Coelemu","Concepcion","Coronel","Curanilahue","Hualqui","Lagunillas","Laja","Lebu","Los Alamos","Los Angeles","Lota","Mulchen","Nacimiento","Ñipas","Penco","Quillon","Quirihue","Rancagua","Renaico","San Carlos","San Nicolas","San Pedro de la Paz","Santa Barbara","Santa Juana","Santiago","Talcahuano","Tome","Trehuaco","Valparaiso","Villuco","Viña Del Mar","Yumbel"],
        disponiblesPorCiudad : 
        {
        Angol: ['Chillan', 'Los Angeles', 'Rancagua', 'Renaico', 'Santiago'],
        Arauco: ['Rancagua', 'Santiago'],
        Bulnes: ['Chillan', 'Quillon', 'San Carlos', 'Santiago'],
        Cabrero: ['Laja', 'Santiago'],
        Cañete: ['Concepcion', 'Santiago'],
        Carampangue: ['Concepcion', 'Rancagua', 'Santiago'],
        Cauquenes: ['Coelemu', 'Concepcion', 'Quirihue', 'Trehuaco'],
        Chiguayante: ['Santiago', 'Valparaiso', 'Viña Del Mar'],
        Chillan: ['Angol', 'Bulnes', 'Cobquecura', 'Coelemu', 'Concepcion', 'Los Angeles', 'Mulchen', 'Nacimiento', 'Ñipas', 'Quillon', 'Quirihue', 'Rancagua', 'Renaico', 'San Carlos', 'Santa Barbara', 'Santiago', 'Valparaiso', 'Viña Del Mar'],
        Cobquecura: ['Chillan', 'Coelemu', 'Concepcion', 'Quirihue', 'San Carlos', 'Santiago', 'Trehuaco'],
        Coelemu: ['Cauquenes', 'Chillan', 'Cobquecura', 'Concepcion', 'Ñipas', 'Quirihue', 'Rancagua', 'San Carlos', 'Santiago', 'Trehuaco'],
        Concepcion: ['Cañete', 'Carampangue', 'Cauquenes', 'Chillan', 'Cobquecura', 'Coelemu', 'Curanilahue', 'Los Angeles', 'Lota', 'Penco', 'Quirihue', 'Rancagua', 'Santiago', 'Talcahuano', 'Tome', 'Trehuaco', 'Valparaiso', 'Viña Del Mar'],
        Coronel: ['Rancagua', 'Santiago', 'Valparaiso', 'Viña Del Mar'],
        Curanilahue: ['Santiago', 'Concepcion'],
        Hualqui: ['Rancagua', 'Santiago', 'Valparaiso', 'Viña Del Mar'],
        Lagunillas: ['Rancagua', 'Santiago', 'Valparaiso', 'Viña Del Mar'],
        Laja: ['Cabrero', 'Santiago'],
        Lebu: ['Santiago'],
        LosAlamos: ['Santiago'],
        LosAngeles: ['Angol', 'Cobquecura', 'Concepcion', 'Mulchen', 'Nacimiento', 'Rancagua', 'Renaico', 'San Carlos', 'Santa Barbara', 'Santiago', 'Valparaiso', 'Viña Del Mar'],
        Lota: ['Rancagua', 'Santiago', 'Valparaiso', 'Viña Del Mar'],
        Mulchen: ['Chillan', 'Los Angeles', 'Rancagua', 'Santiago'],
        Nacimiento: ['Chillan', 'Los Angeles', 'Rancagua', 'Santiago'],
        Ñipas: ['Chillan', 'Rancagua', 'Santiago', 'Coelemu', 'San Carlos'],
        Penco: ['Concepcion', 'Rancagua', 'Santiago', 'Tome', 'Valparaiso', 'Viña Del Mar'],
        Quillon: ['Bulnes', 'Chillan', 'San Carlos', 'Santiago'],
        Quirihue: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Rancagua: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Renaico: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        SanCarlos: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        SanNicolas: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        SanPedroDeLaPaz: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        SantaBarbara: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        SantaJuana: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Santiago: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Trehuaco'],
        Talcahuano: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Tome: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Trehuaco: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Valparaiso: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Villuco: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        ViñaDelMar: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
        Yumbel: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco']
          },
        ciudadDePartida: "",
        ciudadDePartidaLista: "", // esta enviaremos al controlador de ciudad de partida
        ciudadDeSalida: "",
        ciudadDeSalidaLista: "", // esta enviaremos al controlador de ciudad de salida
        ciudadesDisponiblesSalida: "", // aqui mostraremos las ciudades disponibles para cada ciudad de inicio
        msgErrorInicio: "",
        msgErrorDestino: "",
        fechaSeleccionada: null,
      
       
    }
  },
  methods:{
    // esta funcion hara la logica para confirmar que el usuario haya seleccionado una ciudad existen dentro de nuestro catalogo
    verificarCiudadInicio()
    {
      if(this.ciudades.includes(this.ciudadDePartida))
      {

        this.ciudadDePartidaLista = this.ciudadDePartida        
        this.ciudadesDisponibles();
        this.msgErrorInicio = ""
      
      }
      else
      {
        this.ciudadDePartida = ""
        this.msgErrorInicio = "Seleccione una ciudad valida"
        this.ciudadDeSalida = ""
      }
    },

    borrarCiudadesInput()
    {
      if(this.ciudadDePartidaLista && this.ciudadDeSalidaLista)
      {
        this.ciudadDePartida = ""
        this.ciudadDeSalida = ""
      }
      else
      {
        this.msgErrorInicio = "Seleccione una ciudad valida"
        this.msgErrorDestino = "seleccione una ciudad de destino"
      }

    },
    
    
    ciudadesDisponibles() {
    switch(this.ciudadDePartidaLista) 
    {
          case "Angol":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Angol;
              break;
          case "Arauco":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Arauco;
              break;
          case "Bulnes":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Bulnes;
              break;
          case "Cabrero":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Cabrero;
              break;
          case "Cañete":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Cañete;
              break;
          case "Carampangue":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Carampangue;
              break;
          case "Cauquenes":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Cauquenes;
              break;
          case "Chiguayante":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Chiguayante;
              break;
          case "Chillan":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Chillan;
              break;
          case "Cobquecura":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Cobquecura;
              break;
          case "Coelemu":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Coelemu;
              break;
          case "Concepcion":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Concepcion;
              break;
          case "Coronel":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Coronel;
              break;
          case "Curanilahue":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Curanilahue;
              break;
          case "Hualqui":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Hualqui;
              break;
          case "Lagunillas":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Lagunillas;
              break;
          case "Laja":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Laja;
              break;
          case "Lebu":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Lebu;
              break;
          case "Los Alamos":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.LosAlamos;
              break;
          case "Los Angeles":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.LosAngeles;
              break;
          case "Lota":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Lota;
              break;
          case "Mulchen":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Mulchen;
              break;
          case "Nacimiento":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Nacimiento;
              break;
          case "Ñipas":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Ñipas;
              break;
          case "Penco":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Penco;
              break;
          case "Quillon":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Quillon;
              break;
          case "Quirihue":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Quirihue;
              break;
          case "Rancagua":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Rancagua;
              break;
          case "Renaico":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Renaico;
              break;
          case "San Carlos":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.SanCarlos;
              break;
          case "San Nicolas":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.SanNicolas;
              break;
          case "San Pedro de la Paz":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.SanPedroDeLaPaz;
              break;
          case "Santa Barbara":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.SantaBarbara;
              break;
          case "Santa Juana":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.SantaJuana;
              break;
          case "Santiago":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Santiago;
              break;
          case "Talcahuano":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Talcahuano;
              break;
          case "Tome":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Tome;
              break;
          case "Trehuaco":
              this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Trehuaco;
              break;
        case "Valparaiso":
            this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Valparaiso;
            break;
        case "Villuco":
            this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Villuco;
            break;
        case "ViñaDelMar":
            this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.ViñaDelMar;
            break;
        case "Yumbel":
            this.ciudadesDisponiblesSalida = this.disponiblesPorCiudad.Yumbel;
            break;
        default:
            this.msgErrorDestino = "No se encontraron trayectos para esta ciudad de partida";
            this.ciudadDeSalida = "";
            break;
    }
},


    verificarCiudadSalida()
    {
      if(this.ciudades.includes(this.ciudadDeSalida))
      {
        this.ciudadDeSalidaLista = this.ciudadDeSalida
        this.msgErrorDestino = ""
      }
      else
      {
        this.ciudadDeSalida = ""
        this.msgErrorDestino = "seleccione una ciudad de destino"
      }
    },

    getForm(ciudadDeInicio,ciudadDeDestino,fechaSeleccionada)
    {
      axios.post('http://localhost:8080/pasajesDisponibles',{
        
        ciudad_inicio: ciudadDeInicio,
        ciudad_de_destino: ciudadDeDestino,
        fecha_seleccionada:fechaSeleccionada
      }).then(response => {
        
        if(response.status == 200)
        {
          this.$router.push({name:'viajesComponent', query:{ciudad_de_inicio:ciudadDeInicio, ciudad_de_destino:ciudadDeDestino,fecha_viaje:fechaSeleccionada}})

        }
        else
        {
          console.log("error en procesar la solicitud")
        }
        
       
      })
        .catch(error =>{
          console.log(error)
        })
    }
  }
}
</script>


<style>


</style>








