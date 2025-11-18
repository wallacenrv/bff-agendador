package com.wallacen.bff.agendador.controller;

import com.wallacen.bff.agendador.business.dtos.dot.EnderecoDto;
import com.wallacen.bff.agendador.business.dtos.dot.ResponseCepDto;
import com.wallacen.bff.agendador.business.dtos.dot.TelefoneDto;
import com.wallacen.bff.agendador.business.dtos.dot.UsuarioDto;
import com.wallacen.bff.agendador.business.dtos.service.UsuarioService;
import com.wallacen.bff.agendador.infrastructure.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name="Usuario", description = "cadastro e login e usuarios")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEMA)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereco via cep", description = "busca dados  via api cep")
    @ApiResponse(responseCode = "200", description = "Dados buscados com sucesso")
    @ApiResponse(responseCode = "400", description = "CEP invalido")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<ResponseCepDto> buscarDadosCep(@PathVariable("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscarCep(cep));
    }

    @PostMapping()
    @Operation(summary = "Cadastra um novo usuario", description = "Cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "usuario ja cadastrar usuario")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuario", description = "Login de usuário")
    @ApiResponse(responseCode = "200", description = "Usuario logado")
    @ApiResponse(responseCode = "401", description = "credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public String login(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.loginUsuario(usuarioDto);

    }

    @GetMapping()
    @Operation(summary = "Busca um  usuario", description = "Busca dados de usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<UsuarioDto> buscaUsuarioPorEmail(@RequestParam ("email")String email,
                                                           @RequestHeader("Authorization") String token){
        UsuarioDto usuario = usuarioService.buscarUsuarioPorEmail(email,token);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{email}")
    @Operation(summary = "Deleta usuario por DI", description = "Apaga um usuario usuario")
    @ApiResponse(responseCode = "200", description = "Usuario cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "usuario ja cadastrar usuario")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    // alterar dados de usuario
    // Quando eu quero pegar o usuario pelo token, tenho que colocar esse RequestHeader
    @PutMapping()
    @Operation(summary = "Atualiza dados de usuario", description = "Atualiza dados de usuario")
    @ApiResponse(responseCode = "200", description = "Atualizado usuario com sucesso")
    @ApiResponse(responseCode = "400", description = "usuario não cadastrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<UsuarioDto> atualizarDadosUsuario(@RequestHeader("Authorization") String token, @RequestBody UsuarioDto usuarioDto){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(token, usuarioDto));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza endereco de usuario", description = "Atualiza endereco do usuario")
    @ApiResponse(responseCode = "200", description = "Endereco Atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    public ResponseEntity<EnderecoDto> atualizarEndereco(@RequestParam("id") Long idEndereco,
                                                         @RequestBody EnderecoDto enderecoDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(idEndereco, enderecoDto, token));
    }

    @Operation(summary = "Cadastra um novo telefone do usuario", description = "Cria um contato de usuario")
    @ApiResponse(responseCode = "200", description = "Contato cadastrado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDto> atualizarTelefone(@RequestParam ("id") Long idTelefone,
                                                         @RequestBody TelefoneDto telefoneDto,
                                                         @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(idTelefone, telefoneDto, token));
    }

    @Operation(summary = "Salva telefone de usuario", description = "Salva telefone de usuario")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Telefone não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    @PostMapping("/telefone")
    public ResponseEntity<TelefoneDto> cadastraTelefone(@RequestHeader("Authorization") String token,
                                                         @RequestBody TelefoneDto telefoneDto){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneDto));
    }

    @Operation(summary = "Salva endereco de usuario", description = "Salva endereco de usuario")
    @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "erro interno de servidor")
    @PostMapping("/endereco")
    public ResponseEntity<EnderecoDto> cadastraEndereco(@RequestHeader("Authorization") String token,
                                                         @RequestBody EnderecoDto enderecoDto){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoDto));
    }



}
