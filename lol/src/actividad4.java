import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class AddressBook {
    private static HashMap<String, String> Contacto = new HashMap<>();


    public static void load() throws FileNotFoundException, IOException  {
        String filePath = "imput.txt";

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        while ((line = reader.readLine()) != null)
        {
            String[] parts = line.split("=", 2);
            if (parts.length >= 2)
            {
                String key = parts[0];
                String value = parts[1];
                Contacto.put(key, value);
            }
        }
    }
    public static void save() throws IOException {
        Properties properties = new Properties();

        for (Map.Entry<String,String> entry : Contacto.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }

        properties.store(new FileOutputStream("imput.txt"), null);
    }
    public static void list() {
        for (Map.Entry<String, String> entry : Contacto.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String output = String.format("{%s}:{%s}", key, value);
            System.out.println(output);
        }
    }
    public static void create(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("introduce nombre");
        String Nombre = null;
        try {
            Nombre = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("introduce telefono");
        String Telefono = null;
        try {
            Telefono = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!Contacto.containsKey(Telefono)){
            Contacto.put(Telefono,Nombre);
            System.out.println("Contacto guardado");
        }else{
            System.out.println("Contacto existente");
        }

    }
    public static void delete(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("introduce nombre");
        String Nombre = null;
        try {
            Nombre = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("introduce telefono");
        String Telefono = null;
        try {
            Telefono = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Contacto.containsKey(Telefono)){
            if(Contacto.containsValue(Nombre)) {
                Contacto.remove(Telefono);
                System.out.println("Contacto Borrado");
            }else{
                System.out.println("Contacto no existente");
            }
        }else{
            System.out.println("Contacto no existente");
        }

    }
}
 public class actividad4 {
         public static void main (String[]args) throws FileNotFoundException, IOException {
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             AddressBook addressBook = new AddressBook();
             System.out.println("\n");
             addressBook.load();
             boolean salir = false;
             int option = 0;
             while(!salir) {
                 System.out.println("1. list");
                 System.out.println("2. Create");
                 System.out.println("3. Delete");
                 System.out.println("4. Salir");

                 System.out.println("Introduce un numero: ");
                 try {
                     option = Integer.parseInt(reader.readLine());
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
                 switch (option) {
                     case 1:
                     addressBook.list();
                     break;
                     case 2:
                         System.out.println("agregar a un contacto");
                     int a = 0;
                     while (a == 0) {
                         addressBook.create();
                         System.out.println("desea agregar otro contacto 1/0");
                         int L = 1;
                         try {
                             L = Integer.parseInt(reader.readLine());
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                         if (L == 1) {
                             a = 0;
                         } else {
                             a = 1;
                         }
                     }
                     break;
                     case 3:
                     int b = 0;
                     while (b == 0) {
                         System.out.println("borrar a un contacto");
                         addressBook.delete();
                         System.out.println("desea borrar otro contacto 1/0");
                         int g = 1;
                         try {
                             g = Integer.parseInt(reader.readLine());
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                         if (g == 1) {
                             b = 0;
                         } else {
                             b = 1;
                         }
                     }
                     break;
                     case 4:
                         salir=true;
                         break;
                 }

             }
             addressBook.save();
             System.out.println("Fin del menu");
         }
     }