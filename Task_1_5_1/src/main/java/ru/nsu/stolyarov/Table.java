package ru.nsu.stolyarov;

import java.util.ArrayList;

/**
 * Класс, описывающий таблицы.
 */
public class Table extends Element {
    public static final Integer ALLIGN_EMPTY = 0;
    public static final Integer ALLIGN_LEFT = 1;
    public static final Integer ALLIGN_MID = 2;
    public static final Integer ALLIGN_RIGHT = 3;
    private ArrayList<Tableable> colNames;
    private ArrayList<Integer> allignments;
    private ArrayList<ArrayList<Tableable>> table;

    /**
     * Инициализация пустой таблицы.
     */
    public Table() {
        colNames = new ArrayList<>();
        allignments = new ArrayList<>();
        table = new ArrayList<>();
    }

    /**
     * Сериализация.
     *
     * @return таблица в виде строки
     */
    public String toString() {
        StringBuilder temp = new StringBuilder();
        for (Tableable el : colNames) {
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
        for (ArrayList<Tableable> row : table) {
            for (Tableable el : row) {
                temp.append("|");
                temp.append(el.toString());
            }
            temp.append("|\n");
        }
        return temp.toString();
    }

    /**
     * Builder.
     */
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
        public Table.Builder addCol(int ind, String name, Integer allignment, String... vals) {
            addCol(ind);
            changeCol(ind, vals);
            building.colNames.set(ind, new Text(name));
            building.allignments.set(ind, allignment);
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
        public Table.Builder addCol(int ind, Text name, Integer allignment, Tableable... vals) {
            addCol(ind);
            changeCol(ind, vals);
            building.colNames.set(ind, name);
            building.allignments.set(ind, allignment);
            return this;
        }

        /**
         * Добавить новую пустую колонку в конец таблицы.
         *
         * @return self
         */
        public Table.Builder addCol() {
            return addCol(building.colNames.size());
        }

        /**
         * Добавить новую пустую колонку на указанный индекс таблицы.
         *
         * @param ind место вставки пустового столбца
         * @return self
         */
        public Table.Builder addCol(int ind) {
            building.colNames.add(ind, new Text());
            building.allignments.add(ind, 0);
            for (int i = 0; i < building.table.size(); i++) {
                building.table.get(i).add(ind, new Text());
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
        public Table.Builder addCol(String name, Integer allignment, String... vals) {
            return addCol(building.colNames.size(), name, allignment, vals);
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
        public Table.Builder addCol(Text name, Integer allignment, Tableable... vals) {
            return addCol(building.colNames.size(), name, allignment, vals);
        }

        /**
         * Добавить новую строку по указанному индексу с заданными значениями элементов.
         *
         * @param ind  индекс вставки строки
         * @param vals значения элементов
         * @return self
         */
        public Table.Builder addRow(int ind, String... vals) {
            addRow(ind);
            changeRow(ind, vals);
            return this;
        }

        /**
         * Добавить новую строку по указанному индексу с заданными значениями элементов.
         *
         * @param ind  индекс вставки строки
         * @param vals объекты элементов
         * @return self
         */
        public Table.Builder addRow(int ind, Tableable... vals) {
            addRow(ind);
            changeRow(ind, vals);
            return this;
        }

        /**
         * Добавить новую строку в конец таблицы с заданными значениями элементов.
         *
         * @param vals значения элементов
         * @return self
         */
        public Table.Builder addRow(String... vals) {
            return addRow(building.table.size(), vals);
        }

        /**
         * Добавить новую строку в конец таблицы с заданными значениями элементов.
         *
         * @param vals объекты элементов
         * @return self
         */
        public Table.Builder addRow(Tableable... vals) {
            return addRow(building.table.size(), vals);
        }

        /**
         * Добавить новую пустую строку в конец таблицы.
         *
         * @return self
         */
        public Table.Builder addRow() {
            return addRow(building.table.size());
        }

        /**
         * Добавить новую пустую строку по указанному индексу таблицы.
         *
         * @param ind место вставки пустой стркои
         * @return self
         */
        public Table.Builder addRow(int ind) {
            ArrayList<Tableable> newList = new ArrayList<>();
            for (int i = 0; i < building.colNames.size(); i++) {
                newList.add(new Text());
            }
            building.table.add(ind, newList);
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
            return changeName(0, vals);
        }

        /**
         * Заменить названия колонок таблицы начиная с первой на заданные значения.
         *
         * @param vals множество новых объектов названий
         * @return self
         */
        public Table.Builder changeName(Text... vals) {
            return changeName(0, vals);
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
            while (ind >= building.table.size()) {
                addRow();
            }
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
        public Table.Builder changeRow(int ind, Tableable... vals) {
            while (ind >= building.table.size()) {
                addRow();
            }
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
            while (ind >= building.colNames.size()) {
                addCol();
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
        public Table.Builder changeCol(int ind, Tableable... vals) {
            while (vals.length > building.table.size()) {
                addRow();
            }
            while (ind >= building.colNames.size()) {
                addCol();
            }
            for (int i = 0; i < vals.length; i++) {
                building.table.get(i).set(ind, vals[i]);
            }
            return this;
        }

    }
}
