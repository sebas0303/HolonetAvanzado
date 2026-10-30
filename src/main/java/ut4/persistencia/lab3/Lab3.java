package ut4.persistencia.lab3;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Lab3 {
    public static void main(String[] args) {
        // La sesión se abre y se cierra automáticamente al salir del try
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            HoloNetControl control = new HoloNetControl(session);

            // Desafío 1: Sensor Táctico
            System.out.println("\n--- DESAFÍO 1: NAVES IMPERIALES ---");
            List<Nave> navesImperiales = control.obtenerNavesPorBandoPlaneta("Imperio");
            navesImperiales.forEach(n -> 
                System.out.printf("Nave: %-25s | Estado: %s%n", n.getNombre(), n.getEstadoSistema())
            );

            // Desafío 2: FETCH JOIN
            System.out.println("\n--- DESAFÍO 2: FLOTA CRÍTICA ---");
            List<Nave> criticas = control.obtenerFlotaCriticaConPlaneta();
            criticas.forEach(n -> {
                String origen = (n.getPlanetaOrigen() != null) ? n.getPlanetaOrigen().getNombre() : "Sin asignar";
                System.out.printf("Nave: %-25s | Origen: %s%n", n.getNombre(), origen);
            });

            // Desafío 3: LEFT JOIN
            System.out.println("\n--- DESAFÍO 3: REPORTE GENERAL ---");
            List<Object[]> reporte = control.obtenerReportePlanetasAsignados();
            reporte.forEach(fila -> 
                System.out.printf("%-25s -> %s%n", fila[0], fila[1])
            );

        } catch (Exception e) {
            System.err.println("Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Liberamos los recursos globales de la conexión
            HibernateUtil.shutdown();
        }
    }
}
