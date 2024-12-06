package ru.nsu.stolyarov;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, описывающий списки задач.
 */
public class TaskList extends MdList {
    private ArrayList<Boolean> done;

    /**
     * Инициализация пустого списка.
     */
    public TaskList() {
        super();
        done = new ArrayList<>();
    }

    @Override
    public String toString(StringBuilder str, int indent) {
        for (int i = 0; i < elements.size(); i++) {
            for (int j = 0; j < indent; j++) {
                str.append("\t");
            }
            str.append("- [");
            if (done.get(i)) {
                str.append("x");
            } else {
                str.append(" ");
            }
            str.append("] ");
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
        private TaskList building;

        public TaskList build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new TaskList();
        }

        /**
         * Добавить новый (новые) элемент(ы) в конец списка.
         *
         * @param els объекты элементов для добавления
         * @return self
         */
        public TaskList.Builder addElement(Listable... els) {
            for (Listable el : els) {
                building.elements.add(el);
                building.done.add(false);
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
        public TaskList.Builder addElement(int ind, Listable... els) {
            for (int i = 0; i < els.length; i++) {
                building.elements.add(ind + i, els[i]);
                building.done.add(ind + i, false);
            }
            return this;
        }

        /**
         * Добавить новый (новые) элемент(ы) в конец списка.
         *
         * @param els строковые элементы для добавления
         * @return self
         */
        public TaskList.Builder addElement(String... els) {
            for (String el : els) {
                building.elements.add(new Text(el));
                building.done.add(false);
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
        public TaskList.Builder addElement(int ind, String... els) {
            for (int i = 0; i < els.length; i++) {
                building.elements.add(ind + i, new Text(els[i]));
                building.done.add(ind + i, false);
            }
            return this;
        }

        /**
         * Удалить элемент по индексу
         *
         * @param ind индекс удаляемого
         * @return self
         */
        public TaskList.Builder removeElement(int ind) {
            building.elements.remove(ind);
            building.done.remove(ind);
            return this;
        }

        /**
         * Удалить последний элемент списка
         *
         * @return self
         */
        public TaskList.Builder removeElement() {
            building.elements.remove(building.elements.size() - 1);
            building.done.remove(building.done.size() - 1);
            return this;
        }

        /**
         * Задать новое значение элементу списка (полное).
         *
         * @param ind  индекс элемента
         * @param text новый текст задачи
         * @param val  новый статус задачи
         * @return self
         */
        public TaskList.Builder setTask(int ind, String text, boolean val) {
            building.done.set(ind, val);
            building.elements.set(ind, new Text(text));
            return this;
        }

        /**
         * Задать новое значение элементу списка (полное).
         *
         * @param ind индекс элемента
         * @param el  новый объект задачи
         * @param val новый статус задачи
         * @return self
         */
        public TaskList.Builder setTask(int ind, Listable el, boolean val) {
            building.done.set(ind, val);
            building.elements.set(ind, el);
            return this;
        }

        /**
         * Задать новое значение элементу списка (только текст).
         *
         * @param ind  индекс элемента
         * @param text новый текст задачи
         * @return self
         */
        public TaskList.Builder setTask(int ind, String text) {
            building.elements.set(ind, new Text(text));
            return this;
        }

        /**
         * Задать новое значение элементу списка (только объект).
         *
         * @param ind индекс элемента
         * @param el  новый текст задачи
         * @return self
         */
        public TaskList.Builder setTask(int ind, Listable el) {
            building.elements.set(ind, el);
            return this;
        }

        /**
         * Задать новое значение элементу списка (только статус).
         *
         * @param ind индекс элемента
         * @param val новый статус задачи
         * @return self
         */
        public TaskList.Builder setTask(int ind, boolean val) {
            building.done.set(ind, val);
            return this;
        }

    }
}
