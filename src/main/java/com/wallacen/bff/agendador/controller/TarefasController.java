package com.wallacen.bff.agendador.controller;


import com.wallacen.bff.agendador.business.dtos.dot.TarefaDto;
import com.wallacen.bff.agendador.business.dtos.service.TarefaService;
import com.wallacen.bff.agendador.business.enums.StatusNotificacao;
import com.wallacen.bff.agendador.infrastructure.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name="Tarefas", description = "Cadastro de Tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEMA)
public class TarefasController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Cadastra uma nova tarefa", description = "Cria uma nova Tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Tarefa ja cadastrada")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<TarefaDto> gravarTarefa(@RequestBody TarefaDto tarefaDto,
                                                  @RequestHeader( value = "Authorization", required = false) String token ){ // deixa o header opcional para o usuario
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDto));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Lista Tarefas", description = "Busca todas as tarefas")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity <List<TarefaDto>> buscaListaDeTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,

                                                                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
                                                                          @RequestHeader( value = "Authorization", required = false) String token ){
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping()
    @Operation(summary = "Lista Tarefas", description = "Busca todas as tarefas po email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity <List<TarefaDto>> buscaListaDeTarefasPorEmail(@RequestHeader( value = "Authorization", required = false) String token ){
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Apaga tarefa", description = "Apaga tarefa por Id")
    @ApiResponse(responseCode = "200", description = "Tarefa apagada")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam String id, @RequestHeader( value = "Authorization", required = false) String token ){
        tarefaService.apagarTarefa(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera status da Tarefa ", description = "Altera status da tarefa")
    @ApiResponse(responseCode = "200", description = "Status alterado com sucesso")
    @ApiResponse(responseCode = "404", description = "Status alterado sem sucesso")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<TarefaDto> alteraStatusNotificacao(@RequestParam("status") StatusNotificacao status,
                                                             @RequestParam("id")String id,
                                                             @RequestHeader( value = "Authorization", required = false) String token ){
        return ResponseEntity.ok(tarefaService.alterarStatus(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Atualizar tarefa ", description = "Altera dados da tarefa ")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada")
    @ApiResponse(responseCode = "404", description = "Tarefa não atualizada")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<TarefaDto> alterarTarefa(@RequestBody TarefaDto tarefaDto,
                                                   @RequestParam("id")String id,
                                                   @RequestHeader( value = "Authorization", required = false) String token ){
        return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDto, id, token));
    }

}
