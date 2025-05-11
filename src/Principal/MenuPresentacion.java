package Principal;

import java.util.Scanner;

public class MenuPresentacion extends Principal{
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


}