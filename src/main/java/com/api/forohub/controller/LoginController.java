package com.api.forohub.controller;

import com.api.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.api.forohub.infra.security.DatosJWToken;
import com.api.forohub.infra.security.TokenService;
import com.api.forohub.repository.UsuarioEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correo_electronico(),
                datosAutenticacionUsuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((UsuarioEntity) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWToken(JWTtoken));
    }
}
