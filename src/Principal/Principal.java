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


        MenuPresentacion menu = new MenuPresentacion();
        System.out.println(menu.obtenerMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su tipo de cambio ejemplo: USD, MXN, ARG");
        String cambio = scanner.next();

        String direction = "https://v6.exchangerate-api.com/v6/c3f53235016ae4afdae7a201/latest/" + cambio;

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
            if (tipos != null && tipos.conversion_rates() != null) {
                System.out.println("Tasa USD: " + tipos.conversion_rates().get("USD"));
            } else {
                System.out.println("Error: Los datos de conversión son nulos.");
            }
            System.out.println("Moneda base: " + tipos.base_code());
            System.out.println("Tasa MXN: " + tipos.conversion_rates().get("USD"));
            System.out.println("Tasa ARG: " + tipos.conversion_rates().get("ARS"));
            System.out.println("Tasa BRL: " + tipos.conversion_rates().get("BRL"));

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
