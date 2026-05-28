package com.androvixj.api.auth;

import com.androvixj.api.model.Usuario;
import com.androvixj.api.repository.UsuarioRepository;
import com.androvixj.api.security.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Tag(name = "Autenticación", description = "Registro e inicio de sesión")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // ─── POST /auth/register ─────────────────────────────────────────────────

    @Operation(summary = "Registrar nuevo usuario")
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequestDTO dto) {
        Usuario usuario = new Usuario(
                dto.getNombreCompleto(),
                dto.getTelefono(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                dto.getRol(),
                LocalDateTime.now()
        );
        usuarioRepository.save(usuario);
        String token = jwtUtil.generateToken(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("token", token));
    }

    // ─── POST /auth/login ────────────────────────────────────────────────────

    @Operation(summary = "Iniciar sesión")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String token = jwtUtil.generateToken(usuario);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
