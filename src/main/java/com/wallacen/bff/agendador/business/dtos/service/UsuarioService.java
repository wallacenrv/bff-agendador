package com.wallacen.bff.agendador.business.dtos.service;

import com.wallacen.bff.agendador.business.dtos.client.ViaCepClient;
import com.wallacen.bff.agendador.business.dtos.dot.EnderecoDto;
import com.wallacen.bff.agendador.business.dtos.dot.ResponseCepDto;
import com.wallacen.bff.agendador.business.dtos.dot.TelefoneDto;
import com.wallacen.bff.agendador.business.dtos.dot.UsuarioDto;
import com.wallacen.bff.agendador.business.dtos.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;
    private final ViaCepClient viaCepClient;

    public UsuarioDto salvarUsuario(UsuarioDto usuarioDto) {
       return usuarioClient.salvarUsuario(usuarioDto);
    }


   public String loginUsuario(UsuarioDto usuarioDto){
        return usuarioClient.login(usuarioDto);
   }

    public UsuarioDto buscarUsuarioPorEmail(String email, String token) {
       return usuarioClient.buscaUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioDto atualizarUsuario(String token, UsuarioDto usuarioDto) {
       return usuarioClient.atualizarDadosUsuario(token, usuarioDto);
    }

    public EnderecoDto atualizarEndereco(Long idEndereco, EnderecoDto enderecoDto, String token) {
       return usuarioClient.atualizarEndereco(idEndereco, enderecoDto, token);

    }

    public TelefoneDto atualizarTelefone(Long idTelefone, TelefoneDto telefoneDto, String token) {
        return usuarioClient.atualizarTelefone(idTelefone, telefoneDto, token);

    }

    public EnderecoDto cadastraEndereco(String token, EnderecoDto enderecoDto) {
       return usuarioClient.cadastraEndereco(token, enderecoDto);
    }

    public TelefoneDto cadastraTelefone(String token, TelefoneDto telefoneDto) {
        return usuarioClient.cadastraTelefone(token, telefoneDto);
    }

    public ResponseCepDto buscarCep(String cep) {
        return viaCepClient.buscaDadosEndereco((cep));

    }

}
