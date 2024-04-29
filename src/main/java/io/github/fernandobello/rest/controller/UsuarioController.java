package io.github.fernandobello.rest.controller;

import io.github.fernandobello.domain.entity.Usuario;
import io.github.fernandobello.domain.repository.UsuariosRepository;
import io.github.fernandobello.exception.SenhaInvalidaException;
import io.github.fernandobello.rest.dto.CredenciaisDTO;
import io.github.fernandobello.rest.dto.TokenDTO;
import io.github.fernandobello.security.jwt.JwtService;
import io.github.fernandobello.service.impl.UsuarioServiceImpl;
import io.jsonwebtoken.Jwt;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.CREATED)
    public TokenDTO Autenticar(@RequestBody @Valid CredenciaisDTO credenciais) {
        try {
            Usuario usuario = new Usuario().builder().login(credenciais.getLogin()).senha(credenciais.getSenha()).build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
