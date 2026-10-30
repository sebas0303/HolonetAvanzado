package ut4.persistencia.lab3;

import jakarta.persistence.*;

@Entity
@Table(name = "naves")
public class Nave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nave_id")
    private Integer nave_id;
    
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    
    @Column(name = "modelo", length = 50, nullable = false)
    private String modelo;
    
    @Column(name = "estado_sistema", length = 20, nullable = false)
    private String estadoSistema;
    
    // Relación bidireccional (Lado propietario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeta_origen_id")
    private Planeta planetaOrigen;

    public Nave() {}

    public Nave(String nombre, String modelo, String estadoSistema, Planeta planetaOrigen) {
        this.nombre = nombre;
        this.modelo = modelo;
        this.estadoSistema = estadoSistema;
        this.planetaOrigen = planetaOrigen;
    }

    public Integer getNave_id() {
        return nave_id;
    }

    public void setNave_id(Integer nave_id) {
        this.nave_id = nave_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstadoSistema() {
        return estadoSistema;
    }

    public void setEstadoSistema(String estadoSistema) {
        this.estadoSistema = estadoSistema;
    }

    public Planeta getPlanetaOrigen() {
        return planetaOrigen;
    }

    public void setPlanetaOrigen(Planeta planetaOrigen) {
        this.planetaOrigen = planetaOrigen;
    }

    @Override
    public String toString() {
        return "Nave{" + "nave_id=" + nave_id + ", nombre=" + nombre + ", modelo=" + modelo + ", estadoSistema=" + estadoSistema + ", planetaOrigen=" + planetaOrigen + '}';
    }
    
    
}