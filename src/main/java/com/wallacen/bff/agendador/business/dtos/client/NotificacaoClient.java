package com.wallacen.bff.agendador.business.dtos.client;

import com.wallacen.bff.agendador.business.dtos.dot.TarefaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificador", url = "${notificador.url}")
public interface NotificacaoClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefaDto tarefaDto);
}
