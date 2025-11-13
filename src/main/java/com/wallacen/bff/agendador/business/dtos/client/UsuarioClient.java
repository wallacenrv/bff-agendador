package com.wallacen.bff.agendador.business.dtos.client;

import com.wallacen.bff.agendador.business.dtos.dot.EnderecoDto;
import com.wallacen.bff.agendador.business.dtos.dot.TelefoneDto;
import com.wallacen.bff.agendador.business.dtos.dot.UsuarioDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping()
    UsuarioDto buscaUsuarioPorEmail(@RequestParam("email")String email,
                                    @RequestHeader("Authorization") String token);

    @PostMapping()
    UsuarioDto salvarUsuario(@RequestBody UsuarioDto usuarioDto);

    @PostMapping("/login")
    String login(@RequestBody UsuarioDto usuarioDto);

    @DeleteMapping("{email}")
    Void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    // alterar dados de usuario
    // Quando eu quero pegar o usuario pelo token, tenho que colocar esse RequestHeader
    @PutMapping()
    UsuarioDto atualizarDadosUsuario(@RequestHeader("Authorization") String token,
                                     @RequestBody UsuarioDto usuarioDto);

    @PutMapping("/endereco")
    EnderecoDto atualizarEndereco(@RequestParam("id") Long idEndereco,
                                  @RequestBody EnderecoDto enderecoDto,
                                  @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDto atualizarTelefone(@RequestParam ("id") Long idTelefone,
                                  @RequestBody TelefoneDto telefoneDto,
                                  @RequestHeader("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDto cadastraTelefone(@RequestHeader("Authorization") String token,
                                 @RequestBody TelefoneDto telefoneDto);

    @PostMapping("/endereco")
    EnderecoDto cadastraEndereco(@RequestHeader("Authorization") String token,
                                 @RequestBody EnderecoDto enderecoDto);

}
