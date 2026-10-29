/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ut4.persistencia.lab3;

import jakarta.persistence.*;

@Entity
@Table(name = "naves")
public class Nave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String modelo;

    // Relación bidireccional (Lado propietario)
    @ManyToOne
    @JoinColumn(name = "planeta_id")
    private Planeta planeta;

    public Nave() {}

    public Nave(String modelo) {
        this.modelo = modelo;
    }

    // Getters y Setters...
    public Long getId() { return id; }
    public String getModelo() { return modelo; }
    
    public Planeta getPlaneta() { return planeta; }
    public void setPlaneta(Planeta planeta) { this.planeta = planeta; }

    @Override
    public String toString() {
        return "Nave{" + "id=" + id + ", modelo='" + modelo + '\'' + '}';
    }
}