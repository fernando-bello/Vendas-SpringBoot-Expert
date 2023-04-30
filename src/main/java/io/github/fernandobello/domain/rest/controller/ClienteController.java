package io.github.fernandobello.domain.rest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @RequestMapping(
            value = {"/hello/{nome}", "/teste"}, //caminho url (podem ser varios)
            method = RequestMethod.GET, //método
            consumes = {"application/json", "application/xml"}, //recebido em json ou xml
            produces = {"application/json", "application/xml"} //produz json ou xml
    )


            @ResponseBody
    public String helloCliente( @PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s ", nomeCliente);
    }







}
