package com.example.demo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    
    public Optional<Empleado> findByCorreo(String correo);
    
}
