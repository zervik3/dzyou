import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChatHistory {
    private static final String FILE_NAME = "chat_history.txt";

    public List<String> loadHistory() {
        List<String> history = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                history.add(line);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке истории: " + e.getMessage());
        }
        return history;
    }

    public void saveMessage(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении сообщения: " + e.getMessage());
        }
    }
}

import java.util.List;
import java.util.Scanner;

public class ChatClient {
    private static ChatHistory chatHistory = new ChatHistory();

    public static void main(String[] args) {
        List<String> history = chatHistory.loadHistory();
        displayHistory(history);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите ваше сообщение (или 'exit' для выхода):");
        while (true) {
            String message = scanner.nextLine();
            if ("exit".equalsIgnoreCase(message)) {
                break;
            }
            sendMessage(message);
        }
    }

    private static void displayHistory(List<String> history) {
        System.out.println("История чата:");
        for (String message : history) {
            System.out.println(message);
        }
    }

    private static void sendMessage(String message) {
        System.out.println("Вы: " + message);
        chatHistory.saveMessage("Вы: " + message);
    }
}