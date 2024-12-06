package ru.nsu.stolyarov;

import java.util.ArrayList;
import javax.management.InvalidAttributeValueException;

/**
 * Класс, описывающий списки.
 */
public class MdList extends Element implements Listable {
    private int listType;
    protected ArrayList<Listable> elements;
    public static final int TYPE_POINTS = 1;
    public static final int TYPE_DEFS = 2;
    public static final int TYPE_NUMS = 3;

    /**
     * Инициализация пустого списка с чёрточным нумератором.
     */
    public MdList() {
        listType = TYPE_DEFS;
        elements = new ArrayList<>();
    }

    /**
     * Вспомогательная функция для сериализации.
     * @return список в виде строки
     */
    public String toString() {
        StringBuilder temp = new StringBuilder();
        toString(temp, 1);
        return temp.toString();
    }

    /**
     * Вспомогателльная функция для сериализации списков, учитывающая необходимые
     * отступы при вложенных списках.
     *
     * @param str    Наращиваемая строка (Builder)
     * @param indent сколько отступов делать
     * @return строковый результат
     * @throws IllegalArgumentException при неверном (ненатуральном) отступе
     */
    public String toString(StringBuilder str, int indent) throws IllegalArgumentException {
        if (indent < 1) {
            throw new IllegalArgumentException("Indent can't be less than 1");
        }
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < indent; j++) {
                str.append("\t");
            }
            if (listType == TYPE_NUMS) {
                str.append(i + 1);
                str.append(". ");
            } else if (listType == TYPE_POINTS) {
                str.append("* ");
            } else {
                str.append("- ");
            }
            if (elements.get(i).getClass() == MdList.class
                    || elements.get(i).getClass() == TaskList.class) {
                str.append("\n");
                ((MdList) elements.get(i)).toString(str, indent + 1);
            } else {
                str.append(elements.get(i));
                str.append("\n");
            }
        }
        return str.toString();
    }

    /**
     * Builder.
     */
    public static class Builder implements ElementBuilder {
        private MdList building;

        public MdList build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new MdList();
        }

        /**
         * Задать тип нумератора (тире, звездочки, числа).
         *
         * @param type численный тип
         * @return self
         * @throws InvalidAttributeValueException при неверном значении нумератора
         */
        public MdList.Builder setType(int type) throws InvalidAttributeValueException {
            if (type < 1 || type > 3) {
                throw new InvalidAttributeValueException("Неверный тип списка");
            }
            building.listType = type;
            return this;
        }

        /**
         * Добавить новый (новые) элемент(ы) в конец списка.
         *
         * @param els объекты элементов для добавления
         * @return self
         */
        public MdList.Builder addElement(Listable... els) {
            for (Listable el : els) {
                building.elements.add(el);
            }
            return this;
        }

        /**
         * Добавить новый (новые) элемент(ы) в заданное место списка.
         *
         * @param ind индекс в списке, на котором будет стоять первый из новых элементов
         * @param els объекты элементов для добавления
         * @return self
         */
        public MdList.Builder addElement(int ind, Listable... els) {
            for (int i = 0; i < els.length; i++) {
                building.elements.add(ind + i, els[i]);
            }
            return this;
        }

        /**
         * Добавить новый (новые) элемент(ы) в конец списка.
         *
         * @param els строковые элементы для добавления
         * @return self
         */
        public MdList.Builder addElement(String... els) {
            for (String el : els) {
                building.elements.add(new Text(el));
            }
            return this;
        }

        /**
         * Добавить новый (новые) элемент(ы) в заданное место списка.
         *
         * @param ind индекс в списке, на котором будет стоять первый из новых элементов
         * @param els строковые элементы для добавления
         * @return self
         */
        public MdList.Builder addElement(int ind, String... els) {
            for (int i = 0; i < els.length; i++) {
                building.elements.add(ind + i, new Text(els[i]));
            }
            return this;
        }

        /**
         * Задать новое значение указанному элементу списка.
         *
         * @param ind индекс элемента списка
         * @param el  заменяющий объект
         * @return self
         */
        public MdList.Builder setElement(int ind, Listable el) {
            building.elements.set(ind, el);
            return this;
        }

        /**
         * Задать новое значение указанному элементу списка.
         *
         * @param ind индекс элемента списка
         * @param el  заменяющая строка (по ней сделается объект Text)
         * @return self
         */
        public MdList.Builder setElement(int ind, String el) {
            building.elements.set(ind, new Text(el));
            return this;
        }

        /**
         * Удалить элемент по индексу.
         *
         * @param ind индекс удаляемого
         * @return self
         */
        public MdList.Builder removeElement(int ind) {
            building.elements.remove(ind);
            return this;
        }

        /**
         * Удалить последний элемент списка.
         *
         * @return self
         */
        public MdList.Builder removeElement() {
            building.elements.remove(building.elements.size() - 1);
            return this;
        }
    }
}
