import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import org.mockito.Mockito.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {

    StrangeGame strangeGame = new StrangeGame();
    Player player = new Player();

    @Mock
    Hour mockHour;

    @Mock
    Prison mockPrison;

    @Spy
    Prison spyPrison;

    @Test
    public void test_a() throws InterruptedException {
        //  hour 0-11
        // mock hour, stub
        when(mockHour.getHour()).thenReturn(10);

        strangeGame.hour = mockHour;
        strangeGame.setPrison(mockPrison);
        assertEquals(10, mockHour.getHour());
        //  enter
        strangeGame.enterGame(player);
        verify(mockPrison, never()).imprisonment(player);
    }

    @Test
    public void test_b() throws InterruptedException {
        String expectedResult = "After a long period of punishment, the player can leave! :)";
        // hour 12-23
        // mock hour, stub
        when(mockHour.getHour()).thenReturn(22);
        strangeGame.hour = mockHour;
        // set spyPrison
        strangeGame.setPrison(spyPrison);
        // skip prison
        doNothing().when(spyPrison).imprisonment(any(Player.class));

        String result = strangeGame.enterGame(player);
        //enter
        strangeGame.enterGame(player);
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_c() throws InterruptedException {
        String[] id = {"97","98","99"};
        Player player1 = new Player(id[0], -1);
        Player player2 = new Player(id[1], -1);
        Player player3 = new Player(id[2], -1);

        //  hour 12-23
        when(mockHour.getHour()).thenReturn(22);
        strangeGame.hour = mockHour;
        //set spyPrison
        strangeGame.setPrison(spyPrison);
        // skip prison
        doNothing().when(spyPrison).imprisonment(any(Player.class));

        //enter game
        strangeGame.enterGame(player1);
        strangeGame.enterGame(player2);
        strangeGame.enterGame(player3);

        ArrayList prisonLog = spyPrison.getLog();
        System.out.println(prisonLog.get(0));
        System.out.println(prisonLog.get(1));
        System.out.println(prisonLog.get(2));
        //assert
        assertEquals(id[0], prisonLog.get(0));
        assertEquals(id[1], prisonLog.get(1));
        assertEquals(id[2], prisonLog.get(2));
    }

    @Test
    public void test_d(){
        String studentID = "310552060";
        int score = 100;
        //mock db, stub 100
        GAMEDb stubDb = mock(GAMEDb.class);
        when(stubDb.getScore(studentID)).thenReturn(score);
        strangeGame.db = stubDb;
        //assert
        assertEquals(score, strangeGame.getScore(studentID));
    }

    @Test
    public void test_e() {
        String expected_result = "Thank you";
        // fake
        fakePaypalService m_fakePaypalService = new fakePaypalService();
        assertEquals(expected_result, strangeGame.donate(m_fakePaypalService));
    }

    //fake PaypalService
    public class fakePaypalService implements paypalService{
        @Override
        public String doDonate() {
            return "Success";
        }
    }
}