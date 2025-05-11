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

        String cambio = "";
        String destino = "";
        double cantidad = 0 ;

        MenuPresentacion menu = new MenuPresentacion();
        System.out.println(menu.obtenerMenu());

        System.out.println("ingrese la opcion del menu que desea utilizar");
        int decision = scanner.nextInt();

        if (decision >=1 && decision <= 6){
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

        String apiKey = System.getenv("EXCHANGE_API_KEY");
        String direction = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" +
                cambio + "/" + destino + "/" + cantidad;


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direction))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            TiposCambio tipos = gson.fromJson(response.body(), TiposCambio.class);
            System.out.println(tipos);
            if (tipos != null && tipos.conversion_rate() != null) {
                System.out.println("Tasa: " + tipos.conversion_rate());
            } else {
                System.out.println("Error: Los datos de conversión son nulos.");
            }
            System.out.println("Moneda base: " + tipos.base_code());
            System.out.println("Tasa: " + cambio + tipos.conversion_rate());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
