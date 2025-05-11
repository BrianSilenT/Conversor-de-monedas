package Principal;

import java.util.Scanner;

public class MenuPresentacion{
    private final String adorno = "*******************************************";
    private final String saludo = "Hola bienvenido/a al conversor de monedas";
    private final String[] menuOpciones = {
            "1) Dólar => Peso argentino",
            "2) Peso argentino => Dólar",
            "3) Dólar => Real brasileño",
            "4) Real brasileño => Dólar",
            "5) Dólar => Peso colombiano",
            "6) Peso colombiano => Dólar",
            "7) Otros (coloque el tipo de cambio abreviado en mayúsculas, ej: MXN = USD)",
            "8) Salir"
    };

    private final Scanner scanner = new Scanner(System.in);
    public final Principal;

    public String obtenerMenu() {
        StringBuilder salida = new StringBuilder();
        salida.append(adorno).append("\n")
                .append(saludo).append("\n")
                .append(adorno).append("\n");

        for (String opcion : menuOpciones) {
            salida.append(opcion).append("\n");
        }

        salida.append(adorno);
        return salida.toString();
    }

    public void procesarSeleccion() {
        while (true) {
            System.out.println("Seleccione una opción:");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> convertirMoneda("USD", "ARS");
                case 2 -> convertirMoneda("ARS", "USD");
                case 3 -> convertirMoneda("USD", "BRL");
                case 4 -> convertirMoneda("BRL", "USD");
                case 5 -> convertirMoneda("USD", "COP");
                case 6 -> convertirMoneda("COP", "USD");
                case 7 -> {
                    System.out.println("Ingrese su tipo de cambio abreviado:");
                    String cambio = scanner.next().toUpperCase();
                    convertirMoneda(cambio, "USD");
                }
                case 8 -> {
                    System.out.println("Gracias por usar el conversor de monedas. ¡Hasta pronto!");
                    break;
                }
                default -> System.out.println("Opción inválida, por favor intente de nuevo.");
            }

            if (opcion == 8) {
                break;
            }
        }
    }

    private void convertirMoneda(String from, String to) {
        try {
            TiposCambio tipos = from.stripLeading(from);
            if (tipos != null && tipos.conversion_rates().containsKey(to)) {
                System.out.println("Tasa de cambio " + from + " => " + to + ": " + tipos.conversion_rates().get(to));
            } else {
                System.out.println("Error: No se pudo obtener la tasa de cambio.");
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al obtener datos de la API: " + e.getMessage());
        }
    }
}