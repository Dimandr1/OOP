package ru.nsu.stolyarov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;


class TaskListTest {
    @Test
    void test() throws InvalidAttributeValueException {
        TaskList t3 = new TaskList();
        String t = t3.toString();
        assertEquals("", t);
        TaskList.Builder t2 = new TaskList.Builder();
        t = t2.build().toString();
        assertEquals("", t);
        t = t2.addElement("123", "147", "678").build().toString();
        assertEquals("\t- [ ] 123\n\t- [ ] 147\n\t- [ ] 678\n", t);
        MdList.Builder t1 = new MdList.Builder();
        t = t1.addElement(new Link.Builder().setUrl("sda").build(),
                        new Text.Builder().setText("sad").setBold(true).build())
                .addElement(1, t2.build()).build().toString();
        assertEquals("\t- <sda>\n\t- \n\t\t- [ ] 123\n" +
                "\t\t- [ ] 147\n\t\t- [ ] 678\n\t- **sad**\n", t);
        t = t2.addElement(2, new Link.Builder().setUrl("ds").build()).build().toString();
        assertEquals("\t- [ ] 123\n\t- [ ] 147\n\t- [ ] <ds>\n\t- [ ] 678\n", t);
        t = t2.addElement(new Text("1")).build().toString();
        assertEquals("\t- [ ] 123\n\t- [ ] 147\n\t- [ ] <ds>\n" +
                "\t- [ ] 678\n\t- [ ] 1\n", t);
        t = t2.setTask(0, "23", true).setTask(1, "sad")
                .setTask(2, new Link(), true).setTask(3, new Image())
                .setTask(4, true).build().toString();
        assertEquals("\t- [x] 23\n\t- [ ] sad\n\t- [x] <>\n" +
                "\t- [ ] ![]()\n\t- [x] 1\n", t);
        t = t2.addElement(1, "232").build().toString();
        assertEquals("\t- [x] 23\n\t- [ ] 232\n\t- [ ] sad\n\t- [x] <>\n" +
                "\t- [ ] ![]()\n\t- [x] 1\n", t);

    }
}