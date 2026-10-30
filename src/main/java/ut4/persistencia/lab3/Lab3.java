package ut4.persistencia.lab3;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lab3 {

    public static void main(String[] args) {
        // 1. Apertura y gestión automática de SessionFactory y Session con try-with-resources
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            HoloNetControl control = new HoloNetControl(session);

            // =========================================================================
            // PRUEBA DESAFÍO 1: Sensor Táctico (INNER JOIN)
            // =========================================================================
            String bandoBuscado = "Imperio";
            System.out.println("\n================ [DESAFÍO 1: SENSOR TÁCTICO - BANDO: " + bandoBuscado.toUpperCase() + "] ================");
            List<Nave> navesImperiales = control.obtenerNavesPorBandoPlaneta(bandoBuscado);

            for (Nave n : navesImperiales) {
                System.out.printf(" -> Nave: %-26s | Modelo: %-18s | Estado: %s%n",
                        n.getNombre(), n.getModelo(), n.getEstadoSistema());
            }

            // =========================================================================
            // PRUEBA DESAFÍO 2: Mitigación N+1 (FETCH JOIN)
            // =========================================================================
            System.out.println("\n================ [DESAFÍO 2: FLOTA EN ESTADO CRÍTICO (FETCH JOIN)] ================");
            List<Nave> flotaCritica = control.obtenerFlotaCriticaConPlaneta();

            for (Nave n : flotaCritica) {
                // Accedemos a n.getPlanetaOrigen().getNombre() sin disparar consultas SQL extra
                String nombrePlaneta = (n.getPlanetaOrigen() != null)
                        ? n.getPlanetaOrigen().getNombre()
                        : "Sin planeta";
                String afiliacion = (n.getPlanetaOrigen() != null)
                        ? n.getPlanetaOrigen().getAfiliacion()
                        : "N/A";

                System.out.printf(" -> [ALERTA CRÍTICA] Nave: %-25s | Origen: %-10s (%s)%n",
                        n.getNombre(), nombrePlaneta, afiliacion);
            }

            // =========================================================================
            // PRUEBA DESAFÍO 3: Reporte del Alistamiento (LEFT JOIN y Proyecciones)
            // =========================================================================
            System.out.println("\n================ [DESAFÍO 3: REPORTE DE PLANETAS ASIGNADOS (LEFT JOIN)] ================");
            List<Object[]> reporte = control.obtenerReportePlanetasAsignados();

            System.out.printf(" %-28s | %-20s%n", "NOMBRE DE LA NAVE", "PLANETA DE ORIGEN");
            System.out.println("------------------------------------------------------------");
            for (Object[] fila : reporte) {
                String nombreNave = (String) fila[0];
                String nombrePlaneta = (String) fila[1]; // Será null si la nave no tiene planeta asignado

                System.out.printf(" %-28s | %-20s%n", nombreNave, nombrePlaneta);
            }
            System.out.println("============================================================\n");

        } catch (Exception e) {
            System.err.println("Error en las transmisiones de la HoloNet: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
