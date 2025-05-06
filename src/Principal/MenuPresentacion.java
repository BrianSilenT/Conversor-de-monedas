package Principal;

public class MenuPresentacion {
    String adorno = "*******************************************";
    String saludo = "Hola binvenido/a al conversor de monedas =";
    String menuDeOpciones = "\"1) Dólar => Peso argentino\");\n" +
            "            \"2) Peso argentino => Dólar\");\n" +
            "            \"3) Dólar => Real brasileño\");\n" +
            "            \"4) Real brasileño => Dólar\");\n" +
            "            \"5) Dólar => Peso colombiano\");\n" +
            "            \"6) Peso colombiano => Dólar\");\n" +
            "\"7) otros coloque el tipo de cambio abreviando en mayusculas ej: MXN = USD.\" );\n" +
            "            \"8) Salir\")";

    public MenuPresentacion(String salida) {
        this.salida = salida;
    }

    String salida = (adorno + "\n" + saludo + adorno + menuDeOpciones + adorno);
}
