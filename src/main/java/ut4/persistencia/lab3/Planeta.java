/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.persistencia.lab3;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planetas")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombrePlaneta;
    private String sectorGalactico;

    // Relación bidireccional (Lado inverso)
    @OneToMany(mappedBy = "planeta", cascade = CascadeType.ALL)
    private List<Nave> naves = new ArrayList<>();

    public Planeta() {}

    public Planeta(Long id, String nombrePlaneta, String sectorGalactico) {
        this.id = id;
        this.nombrePlaneta = nombrePlaneta;
        this.sectorGalactico = sectorGalactico;
    }

    // EL MÉTODO HELPER (Paso 2)
    public void agregarNave(Nave nave) {
        this.naves.add(nave);
        nave.setPlaneta(this); // Sincroniza el lado propietario
    }

    // Getters y Setters...
    public Long getId() { 
        return id; 
    }
    
    public String getNombrePlaneta() { 
        return nombrePlaneta; 
    }

    public String getSectorGalactico() {
        return sectorGalactico;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombrePlaneta(String nombrePlaneta) {
        this.nombrePlaneta = nombrePlaneta;
    }

    public void setSectorGalactico(String sectorGalactico) {
        this.sectorGalactico = sectorGalactico;
    }

    public void setNaves(List<Nave> naves) {
        this.naves = naves;
    }
    
    public List<Nave> getNaves() { return naves; }

    
}