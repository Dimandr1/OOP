package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


class TableTest {
    @Test
    void test() {
        Table t3 = new Table();
        String t = t3.toString();
        assertEquals("", t);
        Table.Builder t1 = new Table.Builder();
        t = t1.build().toString();
        assertEquals("", t);
        t = t1.changeAllignments(Table.ALLIGN_LEFT, Table.ALLIGN_RIGHT, Table.ALLIGN_EMPTY)
                .changeName("123", "456", "1", "6").build().toString();
        assertEquals("|123|456|1|6|\n|:-|-:|-|-|\n", t);
        t = t1.changeAllignment(3, Table.ALLIGN_MID)
                .changeName(2, new Link.Builder().setUrl("sad")
                        .build(), new Text("ds")).build().toString();
        assertEquals("|123|456|<sad>|ds|\n|:-|-:|-|:-:|\n", t);
        t = t1.changeName("2").build().toString();
        assertEquals("|2|456|<sad>|ds|\n|:-|-:|-|:-:|\n", t);
        t = t1.changeName(new Link.Builder().setUrl("dsa").build()).build().toString();
        assertEquals("|<dsa>|456|<sad>|ds|\n|:-|-:|-|:-:|\n", t);
        t = t1.addCol("next", Table.ALLIGN_EMPTY, "1").build().toString();
        assertEquals("|<dsa>|456|<sad>|ds|next|\n|:-|-:|-|:-:|-|\n|||||1|\n", t);
        t = t1.addCol(new Text("last"), Table.ALLIGN_RIGHT,
                new Link.Builder().setUrl("dsad").build()).build().toString();
        assertEquals("|<dsa>|456|<sad>|ds|next|last|\n"
                + "|:-|-:|-|:-:|-|-:|\n|||||1|<dsad>|\n", t);
        t = t1.addRow("asd", "ds", "fp").build().toString();
        assertEquals("|<dsa>|456|<sad>|ds|next|last|\n"
                + "|:-|-:|-|:-:|-|-:|\n|||||1|<dsad>|\n|asd|ds|fp||||\n", t);
        t = t1.addRow(new Text("asd"), new Link.Builder().setUrl("sd").build())
                .build().toString();
        assertEquals("|<dsa>|456|<sad>|ds|next|last|\n"
                + "|:-|-:|-|:-:|-|-:|\n|||||1|<dsad>|\n|asd|ds|fp||||\n|asd|<sd>|||||\n", t);

    }
}