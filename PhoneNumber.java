//Задание
//        Реализуйте структуру телефонной книги с помощью HashMap.
//        Программа также должна учитывать, что в во входной структуре
//        будут повторяющиеся имена с разными телефонами, их необходимо считать,
//        как одного человека с разными телефонами. Вывод должен быть отсортирован
//        по убыванию числа телефонов.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class PhoneNumber {
    // Метод, который добавляет числа в книгу
    public static void addNumber(String key, int value, Map<String, ArrayList<Integer>> map) {
        if (map.containsKey(key)) {
            if (map.get(key).contains(value))
                System.out.println("ВНИМАНИЕ: повторный запрос на добавление значения " + value + " к ключу «" + key + "». Проигноринован!");
            else map.get(key).add(value);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
        }
    }

    // Метод, который печатает список контактов
    public static void printBook(Map<String, ArrayList<Integer>> map) {
        map.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue().size()))
                .forEach(entry -> {
                    String phones = entry.getValue().stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", "));
                    System.out.printf("%s: %s (Количество номеров: %d)\n", entry.getKey(), phones, entry.getValue().size());
                });
    }

    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> bookPhone = new HashMap<>();
        addNumber("Павлов", 123, bookPhone);
        addNumber("Иванова", 1234, bookPhone);
        addNumber("Петров", 546585, bookPhone);
        addNumber("Сидоров", 8956477, bookPhone);
        addNumber("Иванов", 12356233, bookPhone);
        addNumber("Петров", 787897, bookPhone);
        addNumber("Иванов", 12345, bookPhone);
        addNumber("Павлов", 123457, bookPhone);
        addNumber("Иванов", 12345789, bookPhone);
        addNumber("Иванова", 1234567, bookPhone);
        addNumber("Иванов", 123459999, bookPhone);
        addNumber("Павлов", 123457899, bookPhone);
        addNumber("Иванова", 1234, bookPhone);
        printBook(bookPhone);
    }
}