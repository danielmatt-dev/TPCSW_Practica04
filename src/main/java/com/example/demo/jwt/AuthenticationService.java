package com.example.demo.jwt;

import com.example.demo.Empleado;
import com.example.demo.EmpleadoRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    
    private final EmpleadoRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationService(
        EmpleadoRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public Empleado signup(Empleado input) {
        input.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(input);
    }

    public Empleado authenticate(LoginRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getCorreo(),
                        input.getPassword()
                )
        );

        return userRepository.findByCorreo(input.getCorreo())
                .orElseThrow();
    }
    
}
