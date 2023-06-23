package sky.pro.socks_warehouse.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sky.pro.socks_warehouse.dto.SocksCount;
import sky.pro.socks_warehouse.dto.SocksCreate;
import sky.pro.socks_warehouse.exception_nandler.ResourceNotFoundException;
import sky.pro.socks_warehouse.model.Socks;
import sky.pro.socks_warehouse.repository.SocksRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = SocksServiceConfiguration.class)
class SocksServiceContainersTest extends IntegrationSuite {

    @Autowired
    private SocksService socksServiceTest;

    @Autowired
    private SocksRepository socksRepository;


    @BeforeEach
    void beforeEach() {
        socksRepository.deleteAll();
    }


    @Test
    public void testCreateSocksStatus200() {
        String color = "red";
        int cottonPart = 50;
        int quantity = 10;
        SocksCreate socks = givenSocksCreate(color, cottonPart, quantity);
        Socks.SocksId id = givenSocksId(color, cottonPart);

        socksServiceTest.createSocks(socks);
        Socks actualSocks = socksRepository.findById(id).orElseThrow();

        assertEquals(1, socksRepository.count());
        assertEquals(socks.getQuantity(), actualSocks.getQuantity());
        assertEquals(id, actualSocks.getId());
    }


    @Test
    public void testCreateSocksPartStatus200() {
        List<SocksCreate> socksCreates = List.of(
                givenSocksCreate("red", 50, 10),
                givenSocksCreate("red", 50, 40),
                givenSocksCreate("red", 60, 10)
        );
        Socks.SocksId id = givenSocksId("red", 50);

        socksCreates.forEach(socksCreate -> socksServiceTest.createSocks(socksCreate));
        Socks actualSocks = socksRepository.findById(id).orElseThrow();
        assertEquals(2, socksRepository.count());
        assertEquals(50, actualSocks.getQuantity());
    }

    @Test
    public void testReduceSocksPartStatus200() {
        List<SocksCreate> socksCreates = List.of(
                givenSocksCreate("red", 50, 10),
                givenSocksCreate("black", 50, 50),
                givenSocksCreate("black", 60, 10)
        );
        SocksCreate socksBlack = givenSocksCreate("black", 50, 40);
        Socks.SocksId id = givenSocksId("black", 50);

        socksCreates.forEach(socksCreate -> socksServiceTest.createSocks(socksCreate));
        socksServiceTest.reduceQuantitySocks(socksBlack);
        Socks actualSocks = socksRepository.findById(id).orElseThrow();

        assertEquals(3, socksRepository.count());
        assertEquals(10, actualSocks.getQuantity());
    }

    @Test
    public void testReduceSocksPartStatus400() {
        List<SocksCreate> socksCreates = List.of(
                givenSocksCreate("red", 50, 10),
                givenSocksCreate("black", 50, 50),
                givenSocksCreate("black", 60, 10)
        );
        SocksCreate socksYellow = givenSocksCreate("yellow", 50, 50);
        SocksCreate socksYBlack = givenSocksCreate("black", 50, 80);

        socksCreates.forEach(socksCreate -> socksServiceTest.createSocks(socksCreate));

        assertEquals(3, socksRepository.count());
        assertThrows(ResourceNotFoundException.class,
                () -> socksServiceTest.reduceQuantitySocks(socksYellow));
        assertThrows(ResourceNotFoundException.class,
                () -> socksServiceTest.reduceQuantitySocks(socksYBlack));
    }

    @Test
    public void testGetQuantitySocks() {
        SocksCount socksRed = givenSocksCount("red", "lessThan", 30);
        SocksCount socksCBlack = givenSocksCount("black", "moreThan", 40);
        SocksCount socksYellow = givenSocksCount("yellow", "equal", 80);
        SocksCount socksZero = givenSocksCount("yellow", "equal", 100);
        List<SocksCreate> socksCreates = List.of(
                givenSocksCreate("red", 20, 20),
                givenSocksCreate("red", 10, 10),
                givenSocksCreate("black", 60, 50),
                givenSocksCreate("black", 50, 50),
                givenSocksCreate("yellow", 80, 60)
        );

        socksCreates.forEach(socksCreate -> socksServiceTest.createSocks(socksCreate));
        Long actualRedCount = socksServiceTest.getQuantitySocks(socksRed);
        Long actualBlackCount = socksServiceTest.getQuantitySocks(socksCBlack);
        Long actualYellowCount = socksServiceTest.getQuantitySocks(socksYellow);
        Long actualZeroCount = socksServiceTest.getQuantitySocks(socksZero);

        assertEquals(30, actualRedCount);
        assertEquals(100, actualBlackCount);
        assertEquals(60, actualYellowCount);
        assertEquals(0, actualZeroCount);

    }

    private static SocksCount givenSocksCount(String color, String operation, int cottonPart) {
        return new SocksCount(color, operation, cottonPart);
    }

    private static SocksCreate givenSocksCreate(String color, int cottonPart, int quantity) {
        return new SocksCreate(color, cottonPart, quantity);
    }

    private static Socks.SocksId givenSocksId(String color, int cottonPart) {
        return new Socks.SocksId(color, cottonPart);
    }
}