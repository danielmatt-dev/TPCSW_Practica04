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
@RequestMapping("/api/departamento")
public class DepartamentoController {
    
    private DepartamentoRepository repository;
    
    @Autowired
    public DepartamentoController(DepartamentoRepository repository) {
        this.repository = repository;
    }
    
    @GetMapping()
    public ResponseEntity<List<Departamento>> list() {
        List<Departamento> departamentos = (List<Departamento>) repository.findAll();
        return ResponseEntity.ok(departamentos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Departamento> get(@PathVariable Long id) {
        Optional<Departamento> departamento = repository.findById(id);
        if (!departamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(departamento.get());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Departamento> put(@PathVariable Long id, @RequestBody Departamento input) {
        Optional<Departamento> optionalDepartamento = repository.findById(id);
        if (!optionalDepartamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
            
        Departamento departamento = optionalDepartamento.get();
        departamento.setNombre(input.getNombre());
      
        repository.save(departamento);
        return ResponseEntity.ok(departamento);
    }
    
    @PostMapping
    public ResponseEntity<Departamento> post(@RequestBody Departamento input) {
        Departamento nuevoDepartamento = repository.save(input);
        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Departamento> delete(@PathVariable Long id) {
        Optional<Departamento> optionalDepartamento = repository.findById(id);
        if (!optionalDepartamento.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   
        }
        
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
} 
