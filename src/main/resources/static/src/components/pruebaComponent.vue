<template>
<div>
<h1>hola</h1>

</div>

</template>


<script>
import axios from 'axios'
export default
{
    data()
    {
        return{
            mensaje:"hola desde el componente",
            mensajeParaMostrar: ""
            
        }
    },
    mounted()
    {
            axios.post("http://localhost:5000/prueba",{
                mensaje:this.mensaje
            }).then(response=>
            {
                if(response.status == 200)
                {
                    this.mensajeParaMostrar = "la wea llego bien wom"
                }
                console.log("funciono ")
            })
            .catch(error=>{
                console.log(error)
            })
        }
}
</script>

<style>

</style>