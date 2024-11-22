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
     * Получение количества байт в текущем символе юниокда по первому байту.
     *
     * @param b - первый байт символа юникода
     * @return количество байт в символе
     */
    private static int checkLen(byte b) {
        if ((b & 0b10000000) == 0) {
            return 1;
        } else if ((b & 0b11100000) == 0b11000000) {
            return 2;
        } else if ((b & 0b11110000) == 0b11100000) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * Ищем индексы всех вхождений подстроки в файл.
     *
     * @param file           корректный обрабатываемый фаил в кодировке UTF-8
     * @param enterSubstring искомая подстрока
     * @return список индексов вхождений
     * @throws IOException ошибка при считывании из файла
     */
    public static ArrayList<Long> getSubstrings(File file, String enterSubstring)
            throws IOException {
        ArrayList<Long> ans = new ArrayList<>();
        ArrayList<Long> rightString = new ArrayList<>();
        ArrayList<Long> substring = new ArrayList<>();
        byte[] bytes = enterSubstring.getBytes("UTF-8");
        for (int i = 0; i < bytes.length; i++) {
            byte curByte = bytes[i];
            Long tl = 0L;
            tl += curByte + (curByte < 0 ? 256 : 0);
            int l = checkLen(curByte);
            for (int j = 1; j < l; j++) {
                i++;
                tl *= 8;
                tl += bytes[i] + (bytes[i] < 0 ? 256 : 0);
            }
            rightString.add(tl);
        }
        try (FileInputStream inp = new FileInputStream(file)) {
            int curByte = inp.read();
            Long cnt = -((long) rightString.size()) + 1;
            while (curByte != -1) {
                Long tl = 0L;
                tl += curByte;
                int l = checkLen((byte) curByte);
                for (int j = 1; j < l; j++) {
                    tl *= 8;
                    curByte = inp.read();
                    tl += curByte;
                }
                substring.add(tl);
                if (substring.size() > rightString.size()) {
                    substring.remove(0);
                }
                if (substring.size() == rightString.size() && substring.equals(rightString)) {
                    ans.add(cnt);
                }
                curByte = inp.read();
                cnt++;
            }
        }
        return ans;
    }
}
