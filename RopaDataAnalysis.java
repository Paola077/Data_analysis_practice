import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RopaDataAnalysis {

    public static void main(String[] args) {
        String csvFile = "ropaVendida.csv";
        String line = ""; 
        String csvSplitBy = ","; 

        int totalPrendasVendidas = 0; 
        Map<String, Integer> ventasPorCategoria = new HashMap<>(); 
        Map<String, Integer> ventasPorItem = new HashMap<>(); 

        try (BufferedReader infoFichero = new BufferedReader(new FileReader(csvFile))) { 

            while ((line = infoFichero.readLine()) != null) { 
                String[] data = line.split(csvSplitBy);
                if (!data[0].equals("ItemID")) { 
                    String item = data[1]; 
                    String categoria = data[3];
                    int cantidadVendida = Integer.parseInt(data[4]); 

                    totalPrendasVendidas = totalPrendasVendidas + cantidadVendida;

                    ventasPorCategoria.put(categoria, ventasPorCategoria.getOrDefault(categoria, 0) + cantidadVendida);// aqui estoy almacenando las ventas por categoria
                   
                    ventasPorItem.put(item, ventasPorItem.getOrDefault(item, 0) + cantidadVendida); // aqui estoy almacenando los las ventas por items
                }
            }
            
            String itemMasVendido = ventasPorItem.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .get() 
                    .getKey(); 

            String categoriaMasVendida = ventasPorCategoria.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();

            System.out.println("Total de Prendas Vendidas: " + totalPrendasVendidas);
            System.out.println("Artículo Más Vendido: " + itemMasVendido);
            System.out.println("Categoría Más Vendida: " + categoriaMasVendida);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}