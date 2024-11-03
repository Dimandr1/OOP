package ru.nsu.stolyarov;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализация итерируемой хеш-таблицы (словаря).
 *
 * @param <K> - тип ключа
 * @param <V> - тип значения
 */
public class HashTable<K, V> implements Iterable<Pair<K, V>> {
    ArrayList<ArrayList<Pair<K, V>>> table;
    private int totalElements;
    private int curSize;
    private ArrayList<TableIterator> validIterators;

    /**
     * Инициализация таблицы.
     */
    public HashTable() {
        curSize = 8;
        table = new ArrayList<>(curSize);
        for (int i = 0; i < curSize; i++) {
            table.add(new ArrayList<>());
        }
        totalElements = 0;
        validIterators = new ArrayList<>();
    }

    /**
     * Генерация хеша для ключа.
     *
     * @param key - ключ, по которому генерировать
     * @return - целочисленный хеш
     */
    private int hashFunc(K key) {
        int h = key.hashCode();
        return (h % curSize + curSize) % curSize;
    }

    /**
     * Добавить новую пару "ключ-значение" в таблицу.
     *
     * @param key   - ключ
     * @param value - значение
     * @throws IllegalArgumentException - выьрасывается при наличии элемента
     *                                  с данным ключом
     */
    public void put(K key, V value) throws IllegalArgumentException {
        for (TableIterator iter : validIterators) {
            iter.broken = true;
        }
        validIterators = new ArrayList<>();
        if (totalElements * 2 >= curSize) {
            ArrayList<ArrayList<Pair<K, V>>> newTable = new ArrayList<>(curSize * 2);
            for (int i = 0; i < curSize * 2; i++) {
                newTable.add(new ArrayList<>());
            }
            for (Pair<K, V> pair : this) {
                ArrayList<Pair<K, V>> cur = newTable.get(hashFunc(pair.first));
                cur.add(pair);
            }
            curSize *= 2;
            table = newTable;
        }

        if (exists(key)) {
            throw new IllegalArgumentException("Element with " + key + " key is already in table");
        }
        totalElements++;
        ArrayList<Pair<K, V>> cur = table.get(hashFunc(key));
        cur.add(new Pair<>(key, value));
    }

    /**
     * Удалить элемент по заданному ключу.
     *
     * @param key - ключ
     * @throws NoSuchElementException - при отсутствии элемента с заданным ключом
     */
    public void del(K key) throws NoSuchElementException, ConcurrentModificationException {
        for (TableIterator iter : validIterators) {
            iter.broken = true;
        }
        validIterators = new ArrayList<>();
        ArrayList<Pair<K, V>> cur = table.get((hashFunc(key)));
        boolean b = true;
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i).first == key) {
                cur.remove(i);
                totalElements--;
                b = false;
                break;
            }
        }
        if (b) {
            throw new NoSuchElementException("No element for " + key + " key");
        }
    }

    /**
     * Получить значение по заданному ключу.
     *
     * @param key - ключ
     * @return - значение
     * @throws NoSuchElementException - при отсутствии элемента с заданным ключом
     */
    public V get(K key) throws NoSuchElementException {
        ArrayList<Pair<K, V>> cur = table.get((hashFunc(key)));
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i).first == key) {
                return cur.get(i).second;
            }
        }
        throw new NoSuchElementException("No element for " + key + " key");
    }

    /**
     * Проверяет наличие элемента с заданным ключом в таблице.
     *
     * @param key - ключ
     * @return - истина при наличии элемента, ложь иначе
     */
    public boolean exists(K key) {
        ArrayList<Pair<K, V>> cur = table.get((hashFunc(key)));
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i).first == key) {
                return true;
            }
        }
        return false;
    }

    /**
     * Обновляет значение элемента с заданным ключом.
     *
     * @param key   - ключ
     * @param value - новое значение
     * @throws NoSuchElementException - при отсутствии элемента с заданным ключом
     */
    public void update(K key, V value) throws NoSuchElementException {
        ArrayList<Pair<K, V>> cur = table.get((hashFunc(key)));
        boolean b = true;
        for (int i = 0; i < cur.size(); i++) {
            if (cur.get(i).first == key) {
                cur.get(i).second = value;
                b = false;
            }
        }
        if (b) {
            throw new NoSuchElementException("No element for " + key + " key");
        }
    }

    /**
     * Создание итератора.
     *
     * @return - итератор
     */
    public TableIterator<K, V> iterator() {
        TableIterator<K, V> ret = new TableIterator<>();
        validIterators.add(ret);
        return ret;
    }

    /**
     * Специальный итератор для текущей хеш0таблицы.
     *
     * @param <K> - тип ключа
     * @param <V> - тип значения
     */
    public class TableIterator<K, V> implements Iterator<Pair<K, V>> {
        private int itered;
        private int curHash;
        private int curCollision;
        private boolean broken;
        private boolean ableToRemove;

        private TableIterator() {
            itered = 0;
            curHash = 0;
            curCollision = 0;
            broken = false;
            ableToRemove = false;
        }

        /**
         * Проверка наличия следующего элемента.
         *
         * @return - истина, если есть следующий элемент, ложь иначе
         */
        public boolean hasNext() {
            return itered < totalElements;
        }

        /**
         * Получение следующей пары ключ-значение.
         *
         * @return - следующая пара
         */
        public Pair<K, V> next() throws ConcurrentModificationException {
            if (broken) {
                throw new ConcurrentModificationException("The table was modified");
            }
            if (table.get(curHash).size() <= curCollision) {
                for (int i = curHash + 1; i < curSize; i++) {
                    if (table.get(i).size() > 0) {
                        curHash = i;
                        curCollision = 0;
                        break;
                    }
                }
            }
            Pair<K, V> ret = (Pair<K, V>) table.get(curHash).get(curCollision);
            curCollision++;
            itered++;
            ableToRemove = true;
            return ret;
        }

        /**
         * Удаление последнего взятого итератором элемента
         *
         * @throws ConcurrentModificationException при попытке удаления во время итерирования
         * @throws NoSuchElementException          при попытке удаления,
         *                                         пока элемент не обработан
         */
        public void remove() throws ConcurrentModificationException, NoSuchElementException {
            if (broken) {
                throw new ConcurrentModificationException("The table was modified");
            }
            if (!ableToRemove) {
                throw new NoSuchElementException("No passed element");
            }
            del(table.get(curHash).get(curCollision - 1).first);
            validIterators.add(this);
            broken = false;
            ableToRemove = false;
        }
    }

    /**
     * Проверка на равенство с другой таблицей.
     *
     * @param anotherTable - другая хеш-таблица
     * @return - истина, если равны, ложь иначе
     */
    public boolean tableEquals(HashTable anotherTable) {
        if (totalElements != anotherTable.totalElements) {
            return false;
        }
        try {
            for (Pair<K, V> pair : this) {
                if (anotherTable.get(pair.first) != pair.second) {
                    return false;
                }
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Преборазование таблицы в строку для последующего вывода.
     *
     * @return - таблица в виде строки
     */
    public String toStr() {
        String ret = "";
        for (Pair<K, V> pair : this) {
            ret += pair.first.toString() + " = " + pair.second.toString() + "\n";
        }
        return ret;
    }

    /**
     * Функция для получения количества элементов.
     *
     * @return количество элементов в таблице
     */
    public int getTotalElements() {
        return totalElements;
    }

}
