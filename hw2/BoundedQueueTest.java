import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundedQueueTest {

    BoundedQueue BQ = new BoundedQueue(3);
    Object firstObject = new Object();

    @BeforeEach
    public void init(){
        BQ.enQueue(firstObject);
    }

    // test BoundedQueue
    // Test BoundedQueue C1, C2
    // BoundedQueue Base : F(arg >= 0)
    @Test
    void TestBoundedQueue_base() {
        //Code does not throw any exception
        try {
            new BoundedQueue(2);
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    // BoundedQueue2 : T(arg < 0)
    @Test
    void TestBoundedQueue_2() {
        // Should throw exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new BoundedQueue(-2);
        });
    }


    // Test Enqueue C1, C2, C3, C4, C7
    // enqueue base: FTFF(when queue is not full, enqueue an object)
    // no exception
    @Test
    void TestEnqueue_base(){
        try {
            BQ.enQueue(new Object()); // add second object
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    // enqueue 2. FTFT (queue is full, enqueue )
    // throw illegalStateException
    @Test
    void TestEnqueue_2() {
        BQ.enQueue(new Object());
        BQ.enQueue(new Object());
        // add new element when Queue is full
        Assertions.assertThrows(IllegalStateException.class, () -> {
            BQ.enQueue(new Object());
        });
    }
    // enqueue 3. FTTF (queue not full, enqueue null object)
    @Test
    void TestEnqueue_3() {
        // add new element when Queue is full
        Assertions.assertThrows(NullPointerException.class, () -> {
            BQ.enQueue(null);
        });
    }

    // enqueue 4. FTTT (queue is full, enqueue null object)
    @Test
    void TestEnqueue_4() {
        // add new element when Queue is full
        BQ.enQueue(new Object());
        BQ.enQueue(new Object());
        Assertions.assertThrows(NullPointerException.class, () -> {
            BQ.enQueue(null);
        });
    }

    // Dequeue
    // Test Dequeue C1, C2, C5, C6
    // base: FTF (not empty, dequeue) no exception
    @Test
    void TestDequeue_base(){
        try {
            BQ.enQueue(new Object());
            BQ.deQueue();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    // dequeue 2: FTT (queue is empty, dequeue)
    @Test
    void TestDequeue_2(){
        BQ.deQueue(); //BQ empty
        Assertions.assertThrows(IllegalStateException.class, ()->{
            BQ.deQueue();
        });
    }

    // Test isEmpty
    // Test isEmpty C1, C6, C7
    // base FF(new a queue, not empty)
    @Test
    void Test_isEmpty_base(){
        assertFalse(BQ.isEmpty());
    }

    // 2. FT (new a queue, is empty)
    @Test
    void Test_isEmpty_2(){
        BQ.deQueue();
        assertTrue(BQ.isEmpty());
    }


    // Test isFull
    // Test isFull C1, C2, C7
    // base FF (new a queue, not full)
    @Test
    void Test_Full_Base(){
        assertFalse(BQ.isFull());
    }

    // 2. FT (new a queue, is full)
    @Test
    void Test_Full_2(){
        BQ.enQueue(new Object());
        BQ.enQueue(new Object());
        assertTrue(BQ.isFull());
    }
}