package ru.nsu.stolyarov;

public class CodeBlock extends Element{
    private String code;

    public CodeBlock(){
        code = "";
    }
    public String toString(){
        StringBuilder temp = new StringBuilder();
        temp.append("``");
        temp.append(code);
        temp.append("``");

        return temp.toString();
    }

    public static class Builder implements ElementBuilder{
        private CodeBlock building;
        public CodeBlock build(){
            return building;
        }
        public Builder(){
            building = new CodeBlock();
        }
        public CodeBlock.Builder setCode(String code){
            building.code = code;
            return this;
        }
    }
}
