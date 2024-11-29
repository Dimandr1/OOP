package ru.nsu.stolyarov;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Класс, описывающий зачетку студента.
 */
public class Zachetka {
    private ArrayList<DifZachet> difZachets;
    private ArrayList<Zachet> zachets;
    private boolean studyFree;

    /**
     * Инициализация списков и задание бюджетности ученика.
     *
     * @param studyFree учится ли студент на бюджете
     */
    public Zachetka(boolean studyFree) {
        this.studyFree = studyFree;
        difZachets = new ArrayList<>();
        zachets = new ArrayList<>();
    }

    /**
     * Добавление новой дисциплины в зачетку (с вариантами оценки от 1 до 5).
     *
     * @param name     название дисциплины
     * @param type     тип аттестации
     * @param semester номер семестра
     */
    public void addDifZachet(String name, String type, int semester) {
        difZachets.add(new DifZachet(name, type, semester));
    }

    public void addDifZachet(String name, String type, int semester, boolean mand) {
        difZachets.add(new DifZachet(name, type, semester, mand));
    }

    /**
     * Добавление новой дисциплины в зачетку (с вариантами оценки зачёт-незачёт).
     *
     * @param name     название дисциплины
     * @param type     тип аттестации
     * @param semester номер семестра
     */
    public void addZachet(String name, String type, int semester) {
        zachets.add(new Zachet(name, type, semester));
    }

    public void addZachet(String name, String type, int semester, boolean mand) {
        zachets.add(new Zachet(name, type, semester, mand));
    }

    /**
     * Установка оценки по предмету за семестр.
     *
     * @param name название предмета
     * @param sem  номер семестра
     * @param mark оценка за семестр
     */
    public void setMark(String name, int sem, int mark) {
        for (DifZachet sub : difZachets) {
            if (Objects.equals(sub.name, name) && sub.semester == sem) {
                sub.changeMark(mark);
            }
        }
    }

    /**
     * Установка оценки (зачёт-незачёт) по предмету за семестр.
     *
     * @param name название предмета
     * @param sem  номер семестра
     * @param pass зачет или незачет
     */
    public void setPass(String name, int sem, boolean pass) {
        for (Zachet sub : zachets) {
            if (Objects.equals(sub.name, name) && sub.semester == sem) {
                sub.changePassing(pass);
            }
        }
    }

    /**
     * @return средняя оценка зачётки
     */
    public float getAverageMark() {
        float ans = 0;
        int cnt = 0;
        for (DifZachet sub : difZachets) {
            if (sub.mark > 0) {
                cnt++;
                ans += sub.mark;
            }
        }
        return ans / cnt;
    }

    /**
     * Возвращает оценку по предмету в дипломе.
     *
     * @param name       название предмета
     * @param fillMissed если истина, то непроставленные оценки будут восприняты как возможные
     *                   пятёрки, иначе они не учитываются
     * @return итоговая оценка в диплом по предмету
     */
    private int getSubjDipMark(String name, boolean fillMissed) {
        float total = 0;
        int cnt = 0;
        for (DifZachet sub : difZachets) {
            if (sub.name.equals(name)) {
                if (sub.mark == 0 && fillMissed) {
                    total += 5;
                    cnt++;
                } else if (sub.mark > 0) {
                    total += sub.mark;
                    cnt++;
                }
            }
        }
        return Math.round(total / cnt);
    }

    /**
     * @return средняя оценка диплома на основании уже проставленных в зачётку
     */
    public float getAverageDipMark() {
        HashSet<String> mandSubjs = new HashSet<>();
        for (DifZachet sub : difZachets) {
            if (sub.isMandatory && sub.mark > 0 && !mandSubjs.contains(sub.name)) {
                mandSubjs.add(sub.name);
            }
        }
        float totalMarks = 0;
        for (String name : mandSubjs) {
            totalMarks += getSubjDipMark(name, false);
        }
        return totalMarks / mandSubjs.size();
    }

    /**
     * @return средняя оценка диплома, если все незаполненные поля будут "5"
     */
    private float getAverageDipMarkWithFilling() {
        HashSet<String> mandSubjs = new HashSet<>();
        for (DifZachet sub : difZachets) {
            if (sub.isMandatory && !mandSubjs.contains(sub.name)) {
                mandSubjs.add(sub.name);
            }
        }
        float totalMarks = 0;
        for (String name : mandSubjs) {
            totalMarks += getSubjDipMark(name, true);
        }
        return totalMarks / mandSubjs.size();
    }

    /**
     * Возвращает номер текущего семестра. Текущим считается последний,
     * где была проставлена оценка по какому-либо предмету.
     *
     * @return номер текущего семестра
     */
    private int getCurSem() {
        int m = 0;
        for (DifZachet sub : difZachets) {
            if (sub.mark > 0 && sub.semester > m) {
                m = sub.semester;
            }
        }
        for (Zachet sub : zachets) {
            if (sub.active && sub.semester > m) {
                m = sub.semester;
            }
        }
        return m;
    }

    /**
     * @return возвращает истину, если студент уже обучается на бюджете
     * или его можно перевести на бюджет, ложь иначе.
     */
    public boolean mayStudyFree() {
        if (studyFree) {
            return true;
        }
        int curSem = getCurSem();
        if (curSem == 1) {
            return false;
        }
        for (Zachet sub : zachets) {
            if (sub.isMandatory && sub.active
                    && ((sub.semester == curSem || sub.semester == curSem - 1)
                    && !sub.passed)) {
                return false;
            }
        }
        for (DifZachet sub : difZachets) {
            if (sub.isMandatory && ((sub.semester == curSem || sub.semester == curSem - 1)
                    && ((sub.type.equals("Экзамен") && sub.mark < 4)
                    || sub.mark < 3))) {
                return false;
            }
        }
        studyFree = true;
        return true;
    }

    /**
     * @return возвращает истину, если студент может по итогам
     * текущего семестра получить стипендию.
     */
    public boolean mayRecieveGrant() {
        int curSem = getCurSem();
        for (Zachet sub : zachets) {
            if (sub.active && sub.semester == curSem && !sub.passed && sub.isMandatory) {
                return false;
            }
        }
        for (DifZachet sub : difZachets) {
            if ((sub.semester == curSem && sub.mark < 4 && sub.mark > 0 && sub.isMandatory)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return возвращает истину, если у студента есть возможность получить красный диплом.
     */
    public boolean mayGetRed() {
        for (DifZachet sub : difZachets) {
            if (sub.isMandatory && ((sub.mark < 4 && sub.mark > 0)
                    || (sub.type.equals("Защита ВКР") && sub.mark != 5 && sub.mark != 0))) {
                return false;
            }
        }
        if (getAverageDipMarkWithFilling() < 4.75) {
            return false;
        }
        return true;
    }

    /**
     * Объединяющий родительский класс для всех видов семестровых курсов в зачетке.
     */
    private class Subject {
        protected String name;
        protected boolean isMandatory;
        protected int semester;
        protected String type;

        /**
         * Инициализация нового обязательного курса.
         *
         * @param name     название предмета
         * @param type     тип аттестации
         * @param semester номер семестра
         */
        private Subject(String name, String type, int semester) {
            this.name = name;
            this.type = type;
            isMandatory = true;
            this.semester = semester;
        }

        /**
         * Инициализация нового курса, которому можно указать обязательность.
         *
         * @param name     название предмета
         * @param type     тип аттестации
         * @param semester номер семестра
         * @param mand     обязателен ли курс
         */
        private Subject(String name, String type, int semester, boolean mand) {
            this.name = name;
            this.type = type;
            isMandatory = mand;
            this.semester = semester;
        }
    }

    /**
     * Вид семестрового курса с проставлением оценки от 1 до 5.
     */
    private class DifZachet extends Subject {
        private int mark;

        private DifZachet(String name, String type, int semester) {
            super(name, type, semester);
            mark = 0;
        }

        private DifZachet(String name, String type, int semester, boolean mand) {
            super(name, type, semester, mand);
            mark = 0;
        }

        /**
         * Установка оценки за семестровый курс.
         *
         * @param newMark новая оценка
         */
        public void changeMark(int newMark) {
            mark = newMark;
        }
    }

    /**
     * Вид семестрового курса с проставлением оценки в формате зачёт-незачёт.
     */
    private class Zachet extends Subject {
        private boolean passed;
        private boolean active;

        private Zachet(String name, String type, int semester) {
            super(name, type, semester);
            passed = false;
            active = false;
        }

        private Zachet(String name, String type, int semester, boolean mand) {
            super(name, type, semester, mand);
            passed = false;
            active = false;
        }

        /**
         * Установить оценку за семестровый курс.
         *
         * @param newPass что установить (зачёт-незачёт)
         */
        public void changePassing(boolean newPass) {
            passed = newPass;
            active = true;
        }

    }
}
