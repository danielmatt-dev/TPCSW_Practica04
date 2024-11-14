package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/empleado")
public class EmpleadoController {
    
    private EmpleadoRepository repository;
    
    @Autowired
    public EmpleadoController(EmpleadoRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping()
    public ResponseEntity<List<Empleado>> list() {
        return ResponseEntity.ok(repository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> get(@PathVariable String id) {
        Optional<Empleado> empleado = repository.findById(Long.valueOf(id));
        if(!empleado.isPresent()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
       
        return ResponseEntity.ok(empleado.get());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> put(@PathVariable Long id, @RequestBody Empleado input) {
        Optional<Empleado> empleado = repository.findById(id);
        if(!empleado.isPresent()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        Empleado emp = empleado.get();
        emp.setNombre(input.getNombre());
        emp.setTelefono(input.getTelefono());
        emp.setDireccion(input.getDireccion());
        emp.setDepto(input.getDepto());
        return ResponseEntity.ok(repository.save(input));
    }
    
    @PostMapping
    public ResponseEntity<Empleado> post(@RequestBody Empleado input) {
        Empleado emp = repository.save(input);
        return new ResponseEntity<Empleado>(emp, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Empleado> delete(@PathVariable Long id) {
        
        Optional<Empleado> empleado = repository.findById(id);
        if(!empleado.isPresent()) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
