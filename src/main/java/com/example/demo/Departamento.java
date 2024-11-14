package com.example.demo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamentos_clave_seq")
    @SequenceGenerator(
            name = "departamentos_clave_seq", 
            sequenceName = "departamentos_clave_seq", 
            initialValue = 1, 
            allocationSize = 1)
    private Long clave;
    
    private String nombre;

    @OneToMany(
            mappedBy = "depto", 
            fetch = FetchType.LAZY)
    private Set<Empleado> empleados;
    
    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
}
