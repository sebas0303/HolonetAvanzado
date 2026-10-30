package ut4.persistencia.lab3;

import jakarta.persistence.*;

@Entity
@Table(name = "planetas")
public class Planeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planeta_id")
    private Integer planetaID;
    
    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;
    
    @Column(name = "afiliacion", length = 50)
    private String afiliacion = "Neutral";

    public Planeta() {}

    public Planeta(String nombre, String afiliacion) {
        this.nombre = nombre;
        this.afiliacion = afiliacion;
    }

    // Getters y Setters...

    public Integer getPlanetaID() {
        return planetaID;
    }

    public void setPlanetaID(Integer planetaID) {
        this.planetaID = planetaID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    @Override
    public String toString() {
        return "Planeta{" + "planetaID=" + planetaID + ", nombre=" + nombre + ", afiliacion=" + afiliacion + '}';
    }
}