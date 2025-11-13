package com.wallacen.bff.agendador.business.dtos.service;

import com.wallacen.bff.agendador.business.dtos.client.TarefaClient;
import com.wallacen.bff.agendador.business.dtos.client.UsuarioClient;
import com.wallacen.bff.agendador.business.dtos.dot.LoginRequestDto;
import com.wallacen.bff.agendador.business.dtos.dot.TarefaDto;
import com.wallacen.bff.agendador.business.dtos.dot.UsuarioDto;
import com.wallacen.bff.agendador.business.enums.StatusNotificacao;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefaService tarefaService;
    private final EmailService emailService;
    private final UsuarioClient usuarioClient;

    // esta no application properties, preciso criar no banco tambem
    @Value("${usuario.email}")
    private String email;

    // esta no application properties, preciso criar no banco tambem
    @Value("${usuario.senha}")
    private String senha;


    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximasHora() {
        log.info("Iniciando busca de tarefas proximas a hora");
        String token = login(convertParaRequestDto());
        LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1);
        List<TarefaDto> tarefas = tarefaService.buscaTarefasAgendadasPorPeriodo(horaAtual, horaFuturaMaisCinco, token);
        tarefas.forEach(tarefa -> System.out.println(tarefa.getEmailUsuario()));
        log.info("tarefas encontradas {}", tarefas);
        tarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            tarefaService.alterarStatus(StatusNotificacao.NOTIFICADO, tarefa.getId(), token);
            log.info("tarefa enviada {}", tarefa.getEmailUsuario());
        });

    }

    //implementar login e senha do proprio sistema

    public String login(LoginRequestDto loginRequestDto) {
        UsuarioDto usuarioDto = loginRequestDtoParaUsuarioDto(loginRequestDto);
        return usuarioClient.login(usuarioDto);
    }

    public LoginRequestDto convertParaRequestDto() {
        return LoginRequestDto.builder()
                .email(email)
                .senha(senha)
                .build();
    }

    public UsuarioDto loginRequestDtoParaUsuarioDto(LoginRequestDto loginRequestDto) {
        return UsuarioDto.builder()
                .email(loginRequestDto.getEmail())
                .senha(loginRequestDto.getSenha())
                .build();
    }
}
