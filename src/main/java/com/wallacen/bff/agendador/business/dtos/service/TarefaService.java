package com.wallacen.bff.agendador.business.dtos.service;

import com.wallacen.bff.agendador.business.dtos.client.TarefaClient;
import com.wallacen.bff.agendador.business.dtos.dot.TarefaDto;
import com.wallacen.bff.agendador.business.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {

 private final TarefaClient tarefaClient;

    public TarefaDto gravarTarefa(String token, TarefaDto tarefaDto){
        return tarefaClient.gravarTarefa(tarefaDto, token);
    }

public List<TarefaDto> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token){
    return tarefaClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);
}

    public List<TarefaDto> buscaTarefasPorEmail(String token){
   return tarefaClient.buscaListaDeTarefasPorEmail(token);  }

    public void apagarTarefa(String id, String token){
       tarefaClient.deletarTarefaPorId(id, token);
    }

    public TarefaDto alterarStatus(StatusNotificacao status, String id, String token){
    return  tarefaClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefaDto updateTarefas(TarefaDto tarefaDto, String id, String token){
       return tarefaClient.alterarTarefa(tarefaDto, id, token);

    }


}
