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
    int totalElements;
    int curSize;
    int iterators;

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
        iterators = 0;
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
     * @throws IllegalArgumentException        - выьрасывается при наличии элемента с данным ключом
     * @throws ConcurrentModificationException - выбрасывается при попытке изменеия таблицы во время итерации
     */
    public void put(K key, V value) throws IllegalArgumentException,
            ConcurrentModificationException {
        if (iterators != 0) {
            throw new ConcurrentModificationException("No edit while iterating!");
        }
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
            throw new IllegalArgumentException("Element with " + key
                    + " key is already in table");
        }
        totalElements++;
        ArrayList<Pair<K, V>> cur = table.get(hashFunc(key));
        cur.add(new Pair<>(key, value));
    }

    /**
     * Удалить элемент по заданному ключу.
     *
     * @param key - ключ
     * @throws NoSuchElementException          - при отсутствии элемента с заданным ключом
     * @throws ConcurrentModificationException - при попытке удалить во время итерации
     */
    public void del(K key) throws NoSuchElementException, ConcurrentModificationException {
        if (iterators != 0) {
            throw new ConcurrentModificationException("No edit while iterating!");
        }
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
     * @throws NoSuchElementException          - при отсутствии элемента с заданным ключом
     * @throws ConcurrentModificationException - при попытке модификации во время итерирования
     */
    public void update(K key, V value) throws NoSuchElementException,
            ConcurrentModificationException {
        if (iterators != 0) {
            throw new ConcurrentModificationException("No edit while iterating!");
        }
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
        iterators++;
        return ret;
    }

    /**
     * Специальный итератор для текущей хеш0таблицы.
     *
     * @param <K> - тип ключа
     * @param <V> - тип значения
     */
    public class TableIterator<K, V> implements Iterator<Pair<K, V>> {
        int itered;
        int curHash;
        int curCollision;
        boolean active;

        private TableIterator() {
            itered = 0;
            curHash = 0;
            curCollision = 0;
            active = true;
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
        public Pair<K, V> next() {
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
            if (active && !hasNext()) {
                iterators--;
                active = false;
            }
            return ret;
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
}
