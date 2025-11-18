package com.wallacen.bff.agendador.business.dtos.client.config;

import com.wallacen.bff.agendador.business.exception.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FeignError implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        String mensagem = mensagemErro(response);

        switch (response.status()) {
            case 403:
                return new ResourceNotFoundException("Erro " + mensagem);
            case 409:
                return new ConflictException("Erro " + mensagem);
            case 401:
                return new UnauthorizedException("Erro " + mensagem);
            case 400:
                return new IllegalArgumentsException("Erro " + mensagem);
            default:
                return new BusinessException("Erro " + mensagem);
        }
    }
    private String mensagemErro(Response response){
        try {
            if(response.body() == null){
                return "";
            }

            return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}