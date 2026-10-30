package ut4.persistencia.lab3;

import java.util.List;
import ut4.persistencia.lab3.Nave;
import org.hibernate.Session;

public class HoloNetControl {
    private final Session session;

    public HoloNetControl(Session session) {
        this.session = session;
    }
    
    /**
     * Desafío 1: El Sensor Táctico (INNER JOIN)
     * Filtra naves por la afiliación de su planeta de origen y excluye las que están en 'Mantenimiento'.
     */
    public List<Nave> obtenerNavesPorBandoPlaneta(String bando) {
        String hql = "SELECT n FROM Nave n "
                   + "INNER JOIN n.planetaOrigen p "
                   + "WHERE p.afiliacion = :bando "
                   + "AND n.estadoSistema <> 'Mantenimiento'";

        return session.createQuery(hql, Nave.class)
                      .setParameter("bando", bando)
                      .getResultList();
    }
    
    /**
     * Desafío 2: Mitigación del Problema N+1 (FETCH JOIN)
     * Recupera las naves en estado 'Critico' inicializando su Planeta en una única consulta SQL.
     * Usamos LEFT JOIN FETCH por seguridad (o INNER JOIN FETCH) para hidratar el objeto Planeta de inmediato.
     */
    public List<Nave> obtenerFlotaCriticaConPlaneta() {
        String hql = "SELECT n FROM Nave n "
                   + "LEFT JOIN FETCH n.planetaOrigen "
                   + "WHERE n.estadoSistema = :estado";

        return session.createQuery(hql, Nave.class)
                      .setParameter("estado", "Critico")
                      .getResultList();
    }
    
    /**
     * Desafío 3: El Reporte del Alistamiento (LEFT JOIN y Proyecciones)
     * Extrae únicamente dos columnas (nombre de nave y nombre de planeta), incluyendo naves sin planeta (null).
     */
    public List<Object[]> obtenerReportePlanetasAsignados() {
        String hql = "SELECT n.nombre, p.nombre "
                   + "FROM Nave n "
                   + "LEFT JOIN n.planetaOrigen p";

        return session.createQuery(hql, Object[].class)
                      .getResultList();
    }
}
