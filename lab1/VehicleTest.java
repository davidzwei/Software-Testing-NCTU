import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();
        vehicle2 = new Vehicle();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        if(Vehicle.totalVehicle() > 0) {
            vehicle1.finalize();
            vehicle2.finalize();
        }
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        vehicle1.finalize();
        vehicle2.finalize();
        assertEquals(0, Vehicle.totalVehicle());
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        int new_speed = 10;
        vehicle2.setSpeed(new_speed);
        assertEquals(new_speed, vehicle2.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setDir() {
        String new_dir = "south";
        vehicle2.setDir(new_dir);
        assertEquals(new_dir, vehicle2.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        int speed = 0;
        assertEquals(speed, vehicle1.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        String dir = "north";
        assertEquals(dir, vehicle1.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        assertEquals(2, Vehicle.totalVehicle());
    }
}