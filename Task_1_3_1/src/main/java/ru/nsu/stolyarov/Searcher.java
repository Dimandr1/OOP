package ru.nsu.stolyarov;

import java.io.*;
import java.util.ArrayList;

/**
 * Класс для поиска всех вхождений строки в файле.
 */
public class Searcher {
    /**
     * Функция для упрощения работы с getSubstrings.
     * Находит ошибки в аргументах, сама открывает переданный фаил, возвращает в виде строки.
     *
     * @param filePath  путь до обрабатываемого файла
     * @param substring искомая подстрока
     * @return строка, описывающая индексы всех вхождений подстроки в файл
     * @throws FileNotFoundException    если файл не удалось найти или открыть
     * @throws IllegalArgumentException если передана пустая подстрока
     * @throws IOException              ошибка при считывании из файла
     */
    public static String find(String filePath, String substring) throws FileNotFoundException,
            IllegalArgumentException, IOException {
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new FileNotFoundException("File does not exist or can't be read");
        }
        if (substring.length() == 0) {
            throw new IllegalArgumentException("substring can't be empty");
        }
        ArrayList<Long> ansList = getSubstrings(file, substring);

        StringBuilder ans = new StringBuilder("[");
        for (int i = 0; i < ansList.size(); i++) {
            ans.append(ansList.get(i).toString());
            if (i != ansList.size() - 1) {
                ans.append(", ");
            }
        }
        ans.append("]");

        return ans.toString();
    }

    /**
     * Ищем индексы всех вхождений подстроки в файл.
     *
     * @param file           обрабатываемый фаил
     * @param enterSubstring искомая подстрока
     * @return список индексов вхождений
     * @throws IOException ошибка при считывании из файла
     */
    public static ArrayList<Long> getSubstrings(File file, String enterSubstring)
            throws IOException {
        ArrayList<Long> ans = new ArrayList<>();
        ArrayList<Integer> substring = new ArrayList<>();
        for (int i = 0; i < enterSubstring.length(); i++) {
            substring.add((int) enterSubstring.charAt(i));
        }
        if (file.length() >= substring.size()) {
            int next = -2;
            Long cur = 0L;
            ArrayList<Integer> curSubstr = new ArrayList<>();
            try (InputStreamReader inp = new InputStreamReader(new FileInputStream(file),
                    "UTF-8")) {
                for (int i = 0; i < enterSubstring.length(); i++) {
                    curSubstr.add(inp.read());
                }
                do {
                    if (next != -2) {
                        curSubstr.remove(0);
                        curSubstr.add(next);
                    }
                    if (curSubstr.equals(substring)) {
                        ans.add(cur);
                    }
                    next = inp.read();
                    cur++;
                } while (next != -1);
            }
        }
        return ans;
    }
}
