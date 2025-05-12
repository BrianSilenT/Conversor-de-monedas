package Principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {


        String apikey = System.getenv("Exchange_API_KEY");
        Scanner scanner = new Scanner(System.in);

        int decision;
        do {
        String cambio = "";
        String destino = "";
        double cantidad = 0 ;

        MenuPresentacion menu = new MenuPresentacion();
        System.out.println(menu.obtenerMenu());

        System.out.println("ingrese la opcion del menu que desea utilizar \n ");
         decision = scanner.nextInt();

        if (decision >=1 && decision <= 7){
            switch (decision){
                case 1 -> {cambio = "USD"; destino ="ARS"; }
                case 2 -> {cambio = "ARS"; destino ="USD"; }
                case 3 -> {cambio = "USD"; destino ="BRL"; }
                case 4 -> {cambio = "BRL"; destino ="USD"; }
                case 5 -> {cambio = "USD"; destino ="COP"; }
                case 6 -> {cambio = "COP"; destino ="USD"; }
                case 7 -> {System.out.println("Ingrese su tipo de cambio ejemplo: USD, MXN, ARG");
                    cambio = scanner.next();
                    System.out.println("Ingrese la moneda destino");
                    destino = scanner.next();
                }
            }
        }

            System.out.println("ingrese la cantidad de monedas que desa cambiar");
        cantidad = scanner.nextDouble();

        TiposCambio tipos = ExchangeApiClient.obtenerTasaCambio(cambio, destino, cantidad);

        if (tipos != null && tipos.conversion_rate() != null) {
            System.out.println("Tasa de conversión: " + tipos.conversion_rate());
            System.out.println("Moneda base: " + tipos.base_code());
            System.out.println("Cambio: " + cambio + " → " + tipos.conversion_rate());
        } else {
            System.out.println("Error: No se pudo obtener la tasa de conversión.");
        }

        System.out.println("¿Desea seguir utilizando el conversor? (1: Sí, 0: No)");
        decision = scanner.nextInt();

    } while (decision == 1);

        System.out.println("Gracias por usar el conversor de monedas. ¡Hasta pronto!");
        scanner.close();
    }
}
