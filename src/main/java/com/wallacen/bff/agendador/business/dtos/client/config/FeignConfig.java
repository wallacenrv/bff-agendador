package com.wallacen.bff.agendador.business.dtos.client.config;

import com.wallacen.bff.agendador.business.exception.BusinessException;
import com.wallacen.bff.agendador.business.exception.ConflictException;
import com.wallacen.bff.agendador.business.exception.ResourceNotFoundException;
import com.wallacen.bff.agendador.business.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig implements ErrorDecoder {

    @Bean
    public FeignError feignError(){
        return new FeignError();
    }

    @Override
    public Exception decode(String s, Response response) {
        //podemos ter varios status, por isso vamos usar o switch case

        switch(response.status()){
            case 403:
                return new ResourceNotFoundException("Erro atributo não encontrado");
            case 409:
                return new ConflictException("Erro atributo j[a existente");
            case 401:
                return new UnauthorizedException("Erro usuário não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
