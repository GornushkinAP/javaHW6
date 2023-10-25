import java.io.*;
import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        HashMap<String, List<String>> phoneBook = new HashMap<>();
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.print("Введите имя (или 'выход' для завершения): ");
            String name = scanner.nextLine();
            if (name.equals("выход")) {
                break;
            }

            System.out.print("Введите номер телефона: ");
            String phoneNumber = scanner.nextLine();

            
            if (phoneBook.containsKey(name)) {
                List<String> phoneNumbers = phoneBook.get(name);
                phoneNumbers.add(phoneNumber);
            } else {
                
                List<String> phoneNumbers = new ArrayList<>();
                phoneNumbers.add(phoneNumber);
                phoneBook.put(name, phoneNumbers);
            }
        }

        
        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phoneBook.entrySet());
        entries.sort((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()));

        
        try (PrintWriter writer = new PrintWriter(new FileWriter("phonebook.txt"))) {
            for (Map.Entry<String, List<String>> entry : entries) {
                String name = entry.getKey();
                List<String> phoneNumbers = entry.getValue();
                writer.println("Имя: " + name);
                writer.println("Телефоны: " + String.join(", ", phoneNumbers));
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
