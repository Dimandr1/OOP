package ru.nsu.stolyarov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.nsu.stolyarov.Main.Heapsort;

class MainTest {
    @Test
    void test() {
        assertArrayEquals(new int[]{1, 2, 3}, Heapsort(new int[]{2, 3, 1}));
        assertArrayEquals(new int[]{-99999, -102, 0, 542, 12345, 949494},
                Heapsort(new int[]{0, 542, 12345, -102, 949494, -99999}));
        assertArrayEquals(new int[]{-641030270, -595886274, -112917067, -55904073, 365077040,
                        401411166, 469596385, 799421849, 930026891, 986850665},
                Heapsort(new int[]{-112917067, 930026891, 365077040, 799421849, -55904073,
                        986850665, -595886274, 401411166, -641030270, 469596385}));
        /*assertArrayEquals(new int[], Heapsort(new int[] ));
        assertArrayEquals(new int[], Heapsort(new int[] ));
        assertArrayEquals(new int[], Heapsort(new int[] ));*/

    }
}