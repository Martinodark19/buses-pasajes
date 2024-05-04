package com.example.ventaPasajesBuses2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Prueba 
{



@GetMapping("/index")
public String index()
{
    return "hola desde index";
}
}
