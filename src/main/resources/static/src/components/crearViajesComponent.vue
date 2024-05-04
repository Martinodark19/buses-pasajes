<template>
 <div class="container">
    <form v-on:submit.prevent="getForm(ciudadDePartidaLista,ciudadDeSalidaLista,fechaSeleccionada,marca,asientos_totales,hora,terminal_inicio,terminal_destino,paradasDisponibles)">
        <div class="form-group">
            <label for="marca">Marca:</label>
            <input type="text" id="marca" name="marca" class="form-control" v-model="marca" required>
    
            <label for="asientos_totales">Asientos Totales:</label>
            <input type="number" id="asientos_totales" name="asientos_totales" class="form-control" v-model="asientos_totales" required>
        
            <label for="fecha_viaje">Fecha de Viaje:</label>
            <input type = "date" v-model="fechaSeleccionada">

    
            <label for="fecha_viaje">ciudad de inicio:</label>
            <input list="ciudadesInicio" placeholder="ciudad inicio" class="form-control" required v-model="CrearCiudadDePartida" v-on:blur="verificarCiudadInicio()">
            <datalist id="ciudadesInicio">
                <option v-for="(ciudad, index) in ciudades" :key="index">
                {{ciudad}}
                </option>
            </datalist>
            <div v-if="msgErrorInicio">{{ msgErrorInicio }}</div>
            <div v-else></div>
            

            <label for="fecha_viaje">ciudad de destino:</label>
            <input list="ciudadesDestino" placeholder="ciudad destino" class="form-control" required v-model="CrearCiudadDeSalida" v-on:blur="verificarCiudadSalida()">
            <datalist id="ciudadesDestino">
                <option v-for="(ciudad, index) in ciudadesDisponiblesSalida" :key="index">
                {{ciudad}}
                </option>
            </datalist>
            <div v-if="msgErrorDestino">{{ msgErrorDestino }}</div>
            <div v-else></div>

            
            <label for="hora">Hora:</label>
            <input type="time" id="hora" name="hora" class="form-control" placeholder="ejemplo: 18:15 AM" v-model="hora" required maxlength="5">

        
            <label for="terminal_inicio">Terminal de Inicio:</label>
            <select id="terminal_inicio" name="terminal_inicio" class="form-control" v-model="terminal_inicio" required >
                <option v-for="(terminal,index) in terminales_disponibles_inicio" :value="terminal" :key="index">{{ terminal }} </option>
            </select>
            <div>{{ msgErrorTerminal_Inicio }}</div>

        
    
            <label for="terminal_destino">Terminal de Destino:</label>
            <select id="terminal_destino" name="terminal_destino" class="form-control" v-model="terminal_destino" required >
                <option v-for="(terminal,index) in terminales_disponibles_salida" :value="terminal" :key="index">{{ terminal }}</option>
            </select>
            <div v-if="msgErrorTerminal_destino">{{ msgErrorTerminal_destino }}</div>
            <div v-else></div>
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
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
                Santiago: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Trehuaco','Cobquecura'],
                Talcahuano: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
                Tome: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
                Trehuaco: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
                Valparaiso: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
                Villuco: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
                ViñaDelMar: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco'],
                Yumbel: ['Chillan', 'Coelemu', 'Concepcion', 'San Carlos', 'Santiago', 'Trehuaco']
            },
        terminalesPorCiudad : 
            {
                Angol: ['Terminal Angol'],
                Arauco: ['Terminal Arauco'],
                Bulnes: ['Terminal Bulnes'],
                Cabrero: ['Terminal Cabrero'],
                Cañete: ['Terminal Cañete'],
                Carampangue: ['Terminal Carampangue'],
                Cauquenes: ['Terminal Cauquenes'],
                Chiguayante: ['Terminal Chiguayante'],
                Chillan: ['Terminal Chillan'],
                Cobquecura: ['Terminal Cobquecura'],
                Coelemu: ['Terminal Coelemu'],
                Concepcion: ['Terminal Concepcion'],
                Coronel: ['Terminal Coronel'],
                Curanilahue: ['Terminal Curanilahue'],
                Hualqui: ['Terminal Hualqui'],
                Lagunillas: ['Terminal Lagunillas'],
                Laja: ['Terminal Laja'],
                Lebu: ['Terminal Lebu'],
                Los_Alamos: ['Terminal Los Alamos'],
                Los_Angeles: ['Terminal Los Angeles'],
                Lota: ['Terminal Lota'],
                Mulchen: ['Terminal Mulchen'],
                Nacimiento: ['Terminal Nacimiento'],
                Ñipas: ['Terminal Ñipas'],
                Penco: ['Terminal Penco'],
                Quillon: ['Terminal Quillon'],
                Quirihue: ['Terminal Quirihue'],
                Rancagua: ['Terminal Rancagua'],
                Renaico: ['Terminal Renaico'],
                San_Carlos: ['Terminal San Carlos'],
                San_Nicolas: ['Terminal San Nicolas'],
                San_Pedro_de_la_Paz: ['Terminal San Pedro de la Paz'],
                Santa_Barbara: ['Terminal Santa Barbara'],
                Santa_Juana: ['Terminal Santa Juana'],
                Santiago: ['Terminal Santiago'],
                Talcahuano: ['Terminal Talcahuano'],
                Tome: ['Terminal Tome'],
                Trehuaco: ['Terminal Trehuaco'],
                Valparaiso: ['Terminal Valparaiso'],
                Villuco: ['Terminal Villuco'],
                Viña_Del_Mar: ['Terminal Viña Del Mar'],
                Yumbel: ['Terminal Yumbel']
            },
        CrearCiudadDePartida: "",
        ciudadDePartidaLista: "", // al controlador
        CrearCiudadDeSalida: "",
        ciudadDeSalidaLista: "", // al controlador
        paradasDisponibles: "", // al controldor
        ciudadesDisponiblesSalida: "", 
        msgErrorInicio: "",
        msgErrorDestino: "",
        fechaSeleccionada: null, // al controlador
        customFormat: 'dd-MM-yyyy',
        marca: "", // al controlador
        asientos_totales: "", // al controlador
        hora: "", // al controlador
        terminales_disponibles_inicio: "",
        terminales_disponibles_salida: "",
        msgErrorTerminal_Inicio: "",
        msgErrorTerminal_destino: "",
        terminal_inicio: "", // al controlador
        terminal_destino: "" // al controlador
    }
  },
  methods:{
    // esta funcion hara la logica para confirmar que el usuario haya seleccionado una ciudad existen dentro de nuestro catalogo
    verificarCiudadInicio()
    {
      if(this.ciudades.includes(this.CrearCiudadDePartida))
      {
        this.ciudadDePartidaLista = this.CrearCiudadDePartida
        this.ciudadesDisponibles();
        this.msgErrorInicio = ""
        this.verificarTerminalesInicio();
      }
      else
      {
        this.ciudadDePartida = ""
        this.msgErrorInicio = "seleccione una ciudad valida"
        this.ciudadDeSalida = "" 
        this.terminal_inicio = ""
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
        if(this.ciudades.includes(this.CrearCiudadDeSalida))
        {
            this.ciudadDeSalidaLista = this.CrearCiudadDeSalida
            this.verificarTerminalesSalida();
            this.paradasDisponiblesPorCiudad();
            this.msgErrorDestino = ""

        }
        else
        {
            this.CrearCiudadDeSalida = ""
            this.msgErrorDestino = "seleccione una ciudad de destino"
            this.terminal_destino = ""
        }
    },

    verificarTerminalesInicio()
    {
      
        if(this.ciudadDePartidaLista)
        {
            if (this.ciudadDePartidaLista === "Angol") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Angol;
            } 
            else if (this.ciudadDePartidaLista === "Arauco") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Arauco;
            } 
            else if (this.ciudadDePartidaLista === "Bulnes") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Bulnes;
            } 
            else if (this.ciudadDePartidaLista === "Cabrero") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Cabrero;
            } 
            else if (this.ciudadDePartidaLista === "Cañete") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Cañete;
            } 
            else if (this.ciudadDePartidaLista === "Carampangue") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Carampangue;
            } 
            else if (this.ciudadDePartidaLista === "Cauquenes") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Cauquenes;
            } 
            else if (this.ciudadDePartidaLista === "Chiguayante") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Chiguayante;
            } 
            else if (this.ciudadDePartidaLista === "Chillan") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Chillan;
            } 
            else if (this.ciudadDePartidaLista === "Cobquecura") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Cobquecura;
            } 
            else if (this.ciudadDePartidaLista === "Coelemu") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Coelemu;
            } 
            else if (this.ciudadDePartidaLista === "Concepcion") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Concepcion;
            } 
            else if (this.ciudadDePartidaLista === "Coronel") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Coronel;
            } 
            else if (this.ciudadDePartidaLista === "Curanilahue") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Curanilahue;
            } 
            else if (this.ciudadDePartidaLista === "Hualqui") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Hualqui;
            } 
            else if (this.ciudadDePartidaLista === "Lagunillas") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Lagunillas;
            } 
            else if (this.ciudadDePartidaLista === "Laja") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Laja;
            }
            else if (this.ciudadDePartidaLista === "Lebu") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Lebu;
            } 
            else if (this.ciudadDePartidaLista === "Los Alamos") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Los_Alamos;
            } 
            else if (this.ciudadDePartidaLista === "Los Angeles") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Los_Angeles;
            } 
            else if (this.ciudadDePartidaLista === "Lota") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Lota;
            } 
            else if (this.ciudadDePartidaLista === "Mulchen") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Mulchen;
            } 
            else if (this.ciudadDePartidaLista === "Nacimiento") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Nacimiento;
            } 
            else if (this.ciudadDePartidaLista === "Ñipas") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Ñipas;
            } 
            else if (this.ciudadDePartidaLista === "Penco") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Penco;
            } 
            else if (this.ciudadDePartidaLista === "Quillon") 
            {
            this.terminales_disponibles_inicio = this.terminalesPorCiudad.Quillon;
            } 
            else if (this.ciudadDePartidaLista === "Quirihue") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Quirihue;
            } 
            else if (this.ciudadDePartidaLista === "Rancagua") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Rancagua;
            } 
            else if (this.ciudadDePartidaLista === "Renaico") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Renaico;
            } 
            else if (this.ciudadDePartidaLista === "San Carlos") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.San_Carlos;
            } 
            else if (this.ciudadDePartidaLista === "San Nicolas") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.San_Nicolas;
            } 
            else if (this.ciudadDePartidaLista === "San Pedro de la Paz") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.San_Pedro_de_la_Paz;
            } 
            else if (this.ciudadDePartidaLista === "Santa Barbara") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Santa_Barbara;
            } 
            else if (this.ciudadDePartidaLista === "Santa Juana") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Santa_Juana;
            } 
            else if (this.ciudadDePartidaLista === "Santiago") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Santiago;
            } 
            else if (this.ciudadDePartidaLista === "Talcahuano") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Talcahuano;
            } 
            else if (this.ciudadDePartidaLista === "Tome") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Tome;
            } 
            else if (this.ciudadDePartidaLista === "Trehuaco") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Trehuaco;
            } 
            else if (this.ciudadDePartidaLista === "Valparaiso") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Valparaiso;
            } 
            else if (this.ciudadDePartidaLista === "Villuco") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Villuco;
            } 
            else if (this.ciudadDePartidaLista === "Viña Del Mar") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Viña_Del_Mar;
            } 
            else if (this.ciudadDePartidaLista === "Yumbel") 
            {
                this.terminales_disponibles_inicio = this.terminalesPorCiudad.Yumbel;
            } 
            else 
            {
                this.msgErrorInicio = "no se encontro la ciudad especificada"
            }   
        }
        else
        {
            this.msgErrorInicio = "seleccione una ciudad de partida valida "
        }
        
    },

    verificarTerminalesSalida()
    {
        if(this.ciudadDeSalidaLista)
        {
            if (this.ciudadDeSalidaLista === "Angol") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Angol;
            } 
            else if (this.ciudadDeSalidaLista === "Arauco") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Arauco;
            } 
            else if (this.ciudadDeSalidaLista === "Bulnes") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Bulnes;
            } 
            else if (this.ciudadDeSalidaLista === "Cabrero") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Cabrero;
            } 
            else if (this.ciudadDeSalidaLista === "Cañete") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Cañete;
            } 
            else if (this.ciudadDeSalidaLista === "Carampangue") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Carampangue;
            } 
            else if (this.ciudadDeSalidaLista === "Cauquenes") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Cauquenes;
            } 
            else if (this.ciudadDeSalidaLista === "Chiguayante") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Chiguayante;
            } 
            else if (this.ciudadDeSalidaLista === "Chillan") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Chillan;
            } 
            else if (this.ciudadDeSalidaLista === "Cobquecura") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Cobquecura;
            } 
            else if (this.ciudadDeSalidaLista === "Coelemu") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Coelemu;
            } 
            else if (this.ciudadDeSalidaLista === "Concepcion") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Concepcion;
            } 
            else if (this.ciudadDeSalidaLista === "Coronel") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Coronel;
            } 
            else if (this.ciudadDeSalidaLista === "Curanilahue") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Curanilahue;
            } 
            else if (this.ciudadDeSalidaLista === "Hualqui") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Hualqui;
            } 
            else if (this.ciudadDeSalidaLista === "Lagunillas") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Lagunillas;
            } 
            else if (this.ciudadDeSalidaLista === "Laja") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Laja;
            }
            else if (this.ciudadDeSalidaLista === "Lebu") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Lebu;
            } 
            else if (this.ciudadDeSalidaLista === "Los Alamos") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Los_Alamos;
            } 
            else if (this.ciudadDeSalidaLista === "Los Angeles") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Los_Angeles;
            } 
            else if (this.ciudadDeSalidaLista === "Lota") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Lota;
            } 
            else if (this.ciudadDeSalidaLista === "Mulchen") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Mulchen;
            } 
            else if (this.ciudadDeSalidaLista === "Nacimiento") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Nacimiento;
            } 
            else if (this.ciudadDeSalidaLista === "Ñipas") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Ñipas;
            } 
            else if (this.ciudadDeSalidaLista === "Penco") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Penco;
            } 
            else if (this.ciudadDeSalidaLista === "Quillon") 
            {
            this.terminales_disponibles_salida = this.terminalesPorCiudad.Quillon;
            } 
            else if (this.ciudadDeSalidaLista === "Quirihue") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Quirihue;
            } 
            else if (this.ciudadDeSalidaLista === "Rancagua") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Rancagua;
            } 
            else if (this.ciudadDeSalidaLista === "Renaico") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Renaico;
            } 
            else if (this.ciudadDeSalidaLista === "San Carlos") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.San_Carlos;
            } 
            else if (this.ciudadDeSalidaLista === "San Nicolas") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.San_Nicolas;
            } 
            else if (this.ciudadDeSalidaLista === "San Pedro de la Paz") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.San_Pedro_de_la_Paz;
            } 
            else if (this.ciudadDeSalidaLista === "Santa Barbara") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Santa_Barbara;
            } 
            else if (this.ciudadDeSalidaLista === "Santa Juana") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Santa_Juana;
            } 
            else if (this.ciudadDeSalidaLista === "Santiago") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Santiago;
            } 
            else if (this.ciudadDeSalidaLista === "Talcahuano") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Talcahuano;
            } 
            else if (this.ciudadDeSalidaLista === "Tome") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Tome;
            } 
            else if (this.ciudadDeSalidaLista === "Trehuaco") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Trehuaco;
            } 
            else if (this.ciudadDeSalidaLista === "Valparaiso") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Valparaiso;
            } 
            else if (this.ciudadDeSalidaLista === "Villuco") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Villuco;
            } 
            else if (this.ciudadDeSalidaLista === "Viña Del Mar") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Viña_Del_Mar;
            } 
            else if (this.ciudadDeSalidaLista === "Yumbel") 
            {
                this.terminales_disponibles_salida = this.terminalesPorCiudad.Yumbel;
            } 
            else 
            {
                this.msgErrorTerminalDestino = "seleccione una ciudad de destino valida"

            }   
        }
        else
        {
            this.msgErrorTerminalDestino = "seleccione una ciudad de destino"

        }
        
    },

    paradasDisponiblesPorCiudad()
    {
        // aqui mostraremos las paradas disponibles por viaje y haremos la logica para procesar su terminal de destino
        if(this.ciudadDePartidaLista == "Santiago" && this.ciudadDeSalidaLista == "Concepcion")
        {
            var paradasDisponiblesLogica = ["Chillan"]
         
            
            this.paradasDisponibles = paradasDisponiblesLogica

        }
        else if(this.ciudadDePartidaLista == "Concepcion" && this.ciudadDeSalidaLista == "Santiago")
        {
            this.paradasDisponibles = ["Chillan"]
        }
        else if(this.ciudadDePartidaLista == "Santiago" && this.ciudadDeSalidaLista == "Cobquecura")
        {
            
            this.paradasDisponibles = ["Chillan"]
        }
        else if(this.ciudadDePartidaLista == "Cobquecura" && this.ciudadDeSalidaLista == "Santiago")
        {           
            this.paradasDisponibles = ["Chillann","Quirihue"]
        }
        else
        {
            this.paradasDisponibles = ""
        }
       
    },

    // esta funcion enviara todos los datos validados al controlador 
    getForm(ciudadDePartidaLista,ciudadDeSalidaLista,fechaSeleccionada,marca,asientos_totales,hora,terminal_inicio,terminal_destino,paradasDisponibles)
    {

        axios.post('/crearViajes/creando',{
            marca:marca,
            asientos_totales:asientos_totales,
            fecha_de_viaje:fechaSeleccionada,
            ciudad_de_inicio: ciudadDePartidaLista,
            ciudad_de_destino:ciudadDeSalidaLista,
            hora:hora,
            terminal_inicio:terminal_inicio,
            terminal_destino:terminal_destino,
            paradasDisponibles:paradasDisponibles
        }).then(response => {
            console.log(response)
        })
        .catch(error =>{
            console.log(error)
        })
    }
  }
}
</script>