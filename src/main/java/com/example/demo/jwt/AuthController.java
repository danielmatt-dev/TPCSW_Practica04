package com.example.demo.jwt;

import com.example.demo.Empleado;
import com.example.demo.EmpleadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<EmpleadoDTO> register(@RequestBody Empleado registerUserDto) {
        Empleado registeredUser = authenticationService.signup(registerUserDto);

        EmpleadoDTO emp = new EmpleadoDTO();
        emp.setClave(registeredUser.getClave());
        emp.setNombre(registeredUser.getNombre());
        emp.setTelefono(registeredUser.getTelefono());
        emp.setDireccion(registeredUser.getDireccion());
        emp.setDepartamento(registeredUser.getDepto());
        
        return new ResponseEntity(emp, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        Empleado authenticatedUser = authenticationService.authenticate(request);
 
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    
}
