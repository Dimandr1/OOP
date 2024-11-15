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
     */
    public static String find(String filePath, String substring) throws FileNotFoundException,
            IllegalArgumentException {
        File file = new File(filePath);
        if (!file.canRead()) {
            throw new FileNotFoundException("File does not exist or can't be read");
        }
        if (substring.length() == 0) {
            throw new IllegalArgumentException("substring can't be empty");
        }
        ArrayList<Integer> ansList = getSubstrings(file, substring);

        String ans = "[";
        for (int i = 0; i < ansList.size(); i++) {
            ans += ansList.get(i).toString();
            if (i != ansList.size() - 1) {
                ans += ", ";
            }
        }
        ans += "]";

        return ans;
    }

    /**
     * Ищем индексы всех вхождений подстроки в файл.
     *
     * @param file      обрабатываемый фаил
     * @param substring искомая подстрока
     * @return список индексов вхождений
     */
    public static ArrayList<Integer> getSubstrings(File file, String substring) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (file.length() >= substring.length()) {
            int next = -2;
            int cur = 0;
            String curSubstr = "";
            try (InputStreamReader inp = new InputStreamReader(new FileInputStream(file),
                    "UTF-8")) {
                for (int i = 0; i < substring.length(); i++) {
                    try {
                        curSubstr += (char) inp.read();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                do {
                    if (next != -2) {
                        curSubstr = curSubstr.substring(1);
                        curSubstr += (char) next;
                    }
                    if (curSubstr.equals(substring)) {
                        ans.add(cur);
                    }
                    next = inp.read();
                    cur++;
                } while (next != -1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ans;
    }
}
