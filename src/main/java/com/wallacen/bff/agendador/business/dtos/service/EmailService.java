package com.wallacen.bff.agendador.business.dtos.service;



import com.wallacen.bff.agendador.business.dtos.client.NotificacaoClient;
import com.wallacen.bff.agendador.business.dtos.dot.TarefaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificacaoClient notificacaoClient;

    public void enviarEmail(TarefaDto tarefaDto) {
       notificacaoClient.enviarEmail(tarefaDto);
    }
}
