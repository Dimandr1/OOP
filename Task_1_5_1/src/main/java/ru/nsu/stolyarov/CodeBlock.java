package ru.nsu.stolyarov;

/**
 * Класс, описывающий многострочные блоки кода.
 */
public class CodeBlock extends Element {
    private String code;

    /**
     * Инициализация пустого блока кода.
     */
    public CodeBlock() {
        code = "";
    }

    public String toString() {
        StringBuilder temp = new StringBuilder();
        temp.append("```");
        temp.append(code);
        temp.append("```");

        return temp.toString();
    }

    public static class Builder implements ElementBuilder {
        private CodeBlock building;

        public CodeBlock build() {
            return building;
        }

        /**
         * Инициализация.
         */
        public Builder() {
            building = new CodeBlock();
        }

        /**
         * Задать новый текст кода.
         *
         * @param code новый код
         * @return self
         */
        public CodeBlock.Builder setCode(String code) {
            building.code = code;
            return this;
        }
    }
}
