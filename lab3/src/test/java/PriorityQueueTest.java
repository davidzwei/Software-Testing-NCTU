import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.PriorityQueue;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        //pass
        return Stream.of(
                Arguments.of(new int[] {2, 1, 3}, new int[] {1, 2, 3}),
                Arguments.of(new int[] {3, 7, 5}, new int[] {3, 5, 7}),
                Arguments.of(new int[] {0, 2, -2, 9}, new int[] {-2, 0, 2, 9}),
                Arguments.of(new int[] {8, 3, -5, -7}, new int[] {-7, -5, 3, 8}),
                Arguments.of(new int[] {2, 4, 6, -4, -6}, new int[] {-6, -4, 2, 4, 6})
        );
        //wrong
//        return Stream.of(
//                Arguments.of(new int[] {2, 1, 3}, new int[] {2, 1, 3}),
//                Arguments.of(new int[] {3, 7, 5}, new int[] {7, 5, 3}),
//                Arguments.of(new int[] {0, 2, -2, 9}, new int[] {0, -2, 2, 9}),
//                Arguments.of(new int[] {8, 3, -5, -7}, new int[] {-7, 3, -5, 8}),
//                Arguments.of(new int[] {2, 4, 6, -4, -6}, new int[] {-6, 4, 2, -4, 6})
//        );
    }
    @ParameterizedTest(name = "#{index} - Test with Argument={0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test =new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        // Random array add to priority queue
        for (int i = 0;i < random_array.length;i++) {
            test.add(random_array[i]);
        }
        // get priority result
        for (int i = 0;i < random_array.length;i++) {
            result[i] = test.poll();
        }
        assertArrayEquals(correct_array, result);
    }

    // 3 unique exception

    @Test
    public void whenExceptionThrown_initialCapacityTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new PriorityQueue(-1, null);
        });
    }

    @Test
    public void whenExceptionThrown_addNullTest() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new PriorityQueue().add(null);
        });
    }

    @Test
    public void whenExceptionThrown_toArrayNull(){
        Exception exception = assertThrows(NullPointerException.class, ()->{
           new PriorityQueue().toArray((Object[]) null);
        });
    }
}
