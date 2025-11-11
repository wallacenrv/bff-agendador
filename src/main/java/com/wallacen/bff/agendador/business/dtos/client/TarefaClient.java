package com.wallacen.bff.agendador.business.dtos.client;

import com.wallacen.bff.agendador.business.dtos.dot.TarefaDto;
import com.wallacen.bff.agendador.business.enums.StatusNotificacao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefa", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefaDto gravarTarefa(@RequestBody TarefaDto tarefaDto,
                           @RequestHeader("Authorization") String token);

    @GetMapping("/eventos")
    List <TarefaDto> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                   @RequestHeader("Authorization") String token);

    @GetMapping()
    List<TarefaDto> buscaListaDeTarefasPorEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping
    Void deletarTarefaPorId(@RequestParam String id,
                            @RequestHeader("Authorization") String token);


    @PatchMapping
    TarefaDto alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                      @RequestParam("id")String id,
                                      @RequestHeader("Authorization") String token);

    @PutMapping
    TarefaDto alterarTarefa(@RequestBody TarefaDto tarefaDto,
                            @RequestParam("id")String id,
                            @RequestHeader("Authorization") String token);

}
