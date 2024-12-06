package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;


class MdListTest {
    @Test
    void test() throws InvalidAttributeValueException {
        MdList t3 = new MdList();
        String t = t3.toString();
        assertEquals("", t);
        MdList.Builder t2 = new MdList.Builder();
        t = t2.build().toString();
        assertEquals("", t);
        t = t2.addElement("123", "147", "678").build().toString();
        assertEquals("\t- 123\n\t- 147\n\t- 678\n", t);
        MdList.Builder t1 = new MdList.Builder();
        t1.addElement(new Link.Builder().setUrl("sda").build(), new Text.Builder()
                        .setText("sad").setBold(true).build()).addElement(1, t2.build())
                .build().toString();
        t = t1.setType(MdList.TYPE_NUMS).build().toString();
        assertEquals("\t1. <sda>\n\t2. \n\t\t- 123\n" +
                "\t\t- 147\n\t\t- 678\n\t3. **sad**\n", t);
        t = t1.setType(MdList.TYPE_POINTS).removeElement(0).removeElement()
                .build().toString();
        assertEquals("\t* \n\t\t- 123\n\t\t- 147\n\t\t- 678\n", t);
        t = t2.setElement(0, "23").setElement(2, new Image.Builder().setUrl("ds")
                .build()).build().toString();
        assertEquals("\t- 23\n\t- 147\n\t- ![](ds)\n", t);

    }
}