package ru.nsu.stolyarov;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Класс, описывающий таблицы.
 */
public class Table extends Element {
    public static final Integer ALLIGN_EMPTY = 0;
    public static final Integer ALLIGN_LEFT = 1;
    public static final Integer ALLIGN_MID = 2;
    public static final Integer ALLIGN_RIGHT = 3;
    private ArrayList<Text> colNames;
    private ArrayList<Integer> allignments;
    private ArrayList<ArrayList<Text>> table;

    /**
     * Инициализация пустой таблицы.
     */
    public Table() {
        colNames = new ArrayList<>();
        allignments = new ArrayList<>();
        table = new ArrayList<>();
    }


    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (Text el : colNames) {
            temp.append("|");
            temp.append(el.toString());
        }
        if (colNames.size() > 0) {
            temp.append("|\n");
        }
        for (Integer el : allignments) {
            temp.append("|");
            if (el == ALLIGN_EMPTY) {
                temp.append("-");
            } else if (el == ALLIGN_LEFT) {
                temp.append(":-");
            } else if (el == ALLIGN_MID) {
                temp.append(":-:");
            } else if (el == ALLIGN_RIGHT) {
                temp.append("-:");
            }
        }
        if (colNames.size() > 0) {
            temp.append("|\n");
        }
        for (ArrayList<Text> row : table) {
            for (Text el : row) {
                temp.append("|");
                temp.append(el.toString());
            }
            temp.append("|\n");
        }
        return temp.toString();
    }

    public static class Builder implements ElementBuilder {
        private Table building;

        public Table build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new Table();
        }

        /**
         * Заменить строящийся объект готовой таблицой.
         *
         * @param obj новый объект
         * @return self
         */
        public Table.Builder changeObj(Table obj) {
            building = obj;
            return this;
        }

        /**
         * Добавить новую колонку по указанному индексу с заданными названием,
         * выравниванием и значениями элементов.
         *
         * @param ind        индекс вставки
         * @param name       название колонки
         * @param allignment выравнивание
         * @param vals       строковые значения элементов колонки
         * @return self
         */
        public Table.Builder addCol(int ind, String name, int allignment, String... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            building.colNames.add(ind, new Text(name));
            building.allignments.add(ind, 0);
            for (int i = 0; i < building.table.size(); i++) {
                if (i < vals.length) {
                    building.table.get(i).add(ind, new Text(vals[i]));
                } else {
                    building.table.get(i).add(ind, new Text());
                }
            }
            return this;
        }

        /**
         * Добавить новую колонку по указанному индексу с заданными названием,
         * выравниванием и значениями элементов.
         *
         * @param ind        индекс вставки
         * @param name       название колонки
         * @param allignment выравнивание
         * @param vals       объекты элементов колонки
         * @return self
         */
        public Table.Builder addCol(int ind, Text name, int allignment, Text... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            building.colNames.add(ind, name);
            building.allignments.add(ind, 0);
            for (int i = 0; i < building.table.size(); i++) {
                if (i < vals.length) {
                    building.table.get(i).add(ind, vals[i]);
                } else {
                    building.table.get(i).add(ind, new Text());
                }
            }
            return this;
        }

        /**
         * Добавить новую пустую колонку в конец таблицы.
         *
         * @return self
         */
        public Table.Builder addCol() {
            building.colNames.add(new Text());
            building.allignments.add(0);
            for (int i = 0; i < building.table.size(); i++) {
                building.table.get(i).add(new Text());
            }
            return this;
        }

        /**
         * Добавить новую колонку в конец таблицы с заданными названием,
         * выравниванием и значениями элементов.
         *
         * @param name       название колонки
         * @param allignment выравнивание
         * @param vals       значения элементов колонки
         * @return self
         */
        public Table.Builder addCol(String name, int allignment, String... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            building.colNames.add(new Text(name));
            building.allignments.add(0);
            for (int i = 0; i < building.table.size(); i++) {
                if (i < vals.length) {
                    building.table.get(i).add(new Text(vals[i]));
                } else {
                    building.table.get(i).add(new Text());
                }
            }
            return this;
        }

        /**
         * Добавить новую колонку в конец таблицы с заданными названием,
         * выравниванием и значениями элементов.
         *
         * @param name       название колонки
         * @param allignment выравнивание
         * @param vals       объекты элементов колонки
         * @return self
         */
        public Table.Builder addCol(Text name, int allignment, Text... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            building.colNames.add(name);
            building.allignments.add(0);
            for (int i = 0; i < building.table.size(); i++) {
                if (i < vals.length) {
                    building.table.get(i).add(vals[i]);
                } else {
                    building.table.get(i).add(new Text());
                }
            }
            return this;
        }

        /**
         * Добавить новую строку по указанному индексу с заданными значениями элементов.
         *
         * @param ind  индекс вставки строки
         * @param vals значения элементов
         * @return self
         */
        public Table.Builder addRow(int ind, String... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for (int i = 0; i < building.colNames.size(); i++) {
                if (i < vals.length) {
                    newList.add(new Text(vals[i]));
                } else {
                    newList.add(new Text());
                }
            }
            building.table.add(ind, newList);
            return this;
        }

        /**
         * Добавить новую строку по указанному индексу с заданными значениями элементов.
         *
         * @param ind  индекс вставки строки
         * @param vals объекты элементов
         * @return self
         */
        public Table.Builder addRow(int ind, Text... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for (int i = 0; i < building.colNames.size(); i++) {
                if (i < vals.length) {
                    newList.add(vals[i]);
                } else {
                    newList.add(new Text());
                }
            }
            building.table.add(ind, newList);
            return this;
        }

        /**
         * Добавить новую строку в конец таблицы с заданными значениями элементов.
         *
         * @param vals значения элементов
         * @return self
         */
        public Table.Builder addRow(String... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for (int i = 0; i < building.colNames.size(); i++) {
                if (i < vals.length) {
                    newList.add(new Text(vals[i]));
                } else {
                    newList.add(new Text());
                }
            }
            building.table.add(newList);
            return this;
        }

        /**
         * Добавить новую строку в конец таблицы с заданными значениями элементов.
         *
         * @param vals объекты элементов
         * @return self
         */
        public Table.Builder addRow(Text... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            ArrayList<Text> newList = new ArrayList<>();
            for (int i = 0; i < building.colNames.size(); i++) {
                if (i < vals.length) {
                    newList.add(vals[i]);
                } else {
                    newList.add(new Text());
                }
            }
            building.table.add(newList);
            return this;
        }

        /**
         * Добавить новую пустую строку в конец таблицы.
         *
         * @return self
         */
        public Table.Builder addRow() {
            ArrayList<Text> newList = new ArrayList<>();
            for (int i = 0; i < building.colNames.size(); i++) {
                newList.add(new Text());
            }
            building.table.add(newList);
            return this;
        }

        /**
         * Заменить названия колонок таблицы начиная с указанного индекса
         * на заданные значения.
         *
         * @param ind  начальный индекс замены названий
         * @param vals множество новых значений названий
         * @return self
         */
        public Table.Builder changeName(int ind, String... vals) {
            while (vals.length + ind > building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.colNames.set(ind + i, new Text(vals[i]));
            }
            return this;
        }

        /**
         * Заменить названия колонок таблицы начиная с указанного индекса
         * на заданные значения.
         *
         * @param ind  начальный индекс замены названий
         * @param vals множество новых объектов названий
         * @return self
         */
        public Table.Builder changeName(int ind, Text... vals) {
            while (vals.length + ind > building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.colNames.set(ind + i, vals[i]);
            }
            return this;
        }

        /**
         * Заменить названия колонок таблицы начиная с первой на заданные значения.
         *
         * @param vals множество новых значений названий
         * @return self
         */
        public Table.Builder changeName(String... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.colNames.set(i, new Text(vals[i]));
            }
            return this;
        }

        /**
         * Заменить названия колонок таблицы начиная с первой на заданные значения.
         *
         * @param vals множество новых объектов названий
         * @return self
         */
        public Table.Builder changeName(Text... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.colNames.set(i, vals[i]);
            }
            return this;
        }

        /**
         * Задать новые значения выравниваний начиная с заданного индекса
         * на указанные значкения.
         *
         * @param ind индекс начала замены
         * @param val новые значения выравниваний
         * @return self
         * @throws IllegalArgumentException при неверном типе выравнивания
         */
        public Table.Builder changeAllignment(int ind, int val)
                throws IllegalArgumentException {
            if (val < 1 || val > 3) {
                throw new IllegalArgumentException("wrong allignment type");
            }
            while (ind >= building.allignments.size()) {
                addCol();
            }
            building.allignments.set(ind, val);
            return this;
        }

        /**
         * Задать новые значения выравниваний начиная с первого на указанные значкения.
         *
         * @param vals новые значения выравниваний
         * @return self
         * @throws IllegalArgumentException при неверном типе выравнивания
         */
        public Table.Builder changeAllignments(int... vals) throws IllegalArgumentException {
            for (int val : vals) {
                if (val < 0 || val > 3) {
                    throw new IllegalArgumentException("wrong allignment type");
                }
            }
            while (vals.length > building.allignments.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.allignments.set(i, vals[i]);
            }
            return this;
        }

        /**
         * Заменить значения в указанной строке.
         *
         * @param ind  номер изменяемой строки
         * @param vals новые строковые значения
         * @return self
         */
        public Table.Builder changeRow(int ind, String... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.table.get(ind).set(i, new Text(vals[i]));
            }
            return this;
        }

        /**
         * Заменить значения в указанной строке.
         *
         * @param ind  номер изменяемой строки
         * @param vals новые объектные значения
         * @return self
         */
        public Table.Builder changeRow(int ind, Text... vals) {
            while (vals.length > building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.table.get(ind).set(i, vals[i]);
            }
            return this;
        }

        /**
         * Заменить значения в указанной колонке.
         *
         * @param ind  номер колонки
         * @param vals новые строковые значения
         * @return self
         */
        public Table.Builder changeCol(int ind, String... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            for (int i = 0; i < vals.length; i++) {
                building.table.get(i).set(ind, new Text(vals[i]));
            }
            return this;
        }

        /**
         * Заменить значения в указанной колонке.
         *
         * @param ind  номер колонки
         * @param vals новые объектные значения
         * @return self
         */
        public Table.Builder changeCol(int ind, Text... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            for (int i = 0; i < vals.length; i++) {
                building.table.get(i).set(ind, vals[i]);
            }
            return this;
        }

    }
}
