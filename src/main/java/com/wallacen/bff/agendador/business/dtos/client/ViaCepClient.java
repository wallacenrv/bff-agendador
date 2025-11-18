package com.wallacen.bff.agendador.business.dtos.client;

import com.wallacen.bff.agendador.business.dtos.dot.ResponseCepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "via-cep", url = "${viacep.url}")
public interface ViaCepClient {

    //usando uma path variable para receber o cep
        @GetMapping("/ws/{cep}/json/")
        ResponseCepDto buscaDadosEndereco(@PathVariable("cep")String cep);
}


