package algorithms.crypto;

import java.util.Scanner;

public class CaesarCipher {
    private static final int SHIFT = 5;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите текст для обработки: ");
            String text = scanner.nextLine();

            System.out.println("""
                    Выберите режим:
                    1 — шифрование
                    2 — расшифровка
                    """);
            int mode = scanner.nextInt();
            modeSelection(mode, text);
        }
    }

    private static void modeSelection(int mode, String text) {
        switch (mode) {
            case 1:
                System.out.println(process(text, SHIFT));
                break;
            case 2:
                System.out.println(process(text, -SHIFT));
                break;
            default:
                System.out.println("Неправильно выбран режим!!!");
        }
    }

    private static String process(String text, int shift) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);
            result.append(shiftSymbol(symbol, shift));
        }
        return result.toString();
    }

    private static char shiftSymbol(char symbol, int shift) {
        if (symbol >= 'A' && symbol <= 'Z') {
            int base = 'A';
            int alphabetSize = 26;
            int index = symbol - base;
            int newIndex = normalizeIndex(index + shift, alphabetSize);
            return (char) (base + newIndex);
        } else if (symbol >= 'a' && symbol <= 'z') {
            int base = 'a';
            int alphabetSize = 26;
            int index = symbol - base;
            int newIndex = normalizeIndex(index + shift, alphabetSize);
            return (char) (base + newIndex);
        } else if (symbol >= 'А' && symbol <= 'Я') {
            int base = 'А';
            int alphabetSize = 32;
            int index = symbol - base;
            int newIndex = normalizeIndex(index + shift, alphabetSize);
            return (char) (base + newIndex);
        } else if (symbol >= 'а' && symbol <= 'я') {
            int base = 'а';
            int alphabetSize = 32;
            int index = symbol - base;
            int newIndex = normalizeIndex(index + shift, alphabetSize);
            return (char) (base + newIndex);
        } else {
            return symbol;
        }
    }

    private static int normalizeIndex(int shiftedIndex, int alphabetSize) {
        int remainder = shiftedIndex % alphabetSize;
        if (remainder < 0) {
            remainder += alphabetSize;
        }
        return remainder;
    }
}