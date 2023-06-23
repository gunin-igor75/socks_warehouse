package sky.pro.socks_warehouse.controller;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sky.pro.socks_warehouse.service.SocksService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SocksController.class)
@ActiveProfiles("test")
class SocksControllerValidationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SocksService socksService;

    @ParameterizedTest
    @CsvSource({
            "red, 0, 10",
            "black, 100, 100",
            "yellow, 50, 50"
    })
    public void testIncomeStatus200(String color,int cottonPart, int quantity) throws Exception {

        JSONObject socksCreate = getObject(color, cottonPart, quantity);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(socksCreate.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "red, -1, 10",
            "black, 101, 100",
            "blue, 50, 50"
    })
    public void testIncomeStatus400(String color,int cottonPart, int quantity) throws Exception {

        JSONObject socksCreate = getObject(color, cottonPart, quantity);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/income")
                        .content(socksCreate.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvSource({
            "red, 100, 100",
            "black, 0, 10",
            "yellow, 20, 50"
    })
    public void testOutcomeStatus200(String color,int cottonPart, int quantity) throws Exception {

        JSONObject socksCreate = getObject(color, cottonPart, quantity);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksCreate.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @CsvSource({
            "blue, 100, 100",
            "black, 1000, 10",
            "yellow, 20, -1",
            "red, 50, 0"
    })
    public void testOutcomeStatus400(String color,int cottonPart, int quantity) throws Exception {

        JSONObject socksCreate = getObject(color, cottonPart, quantity);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/socks/outcome")
                        .content(socksCreate.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @CsvSource({
            "red, moreThan, 90",
            "black, lessThan, 10",
            "yellow, equal, 50"
    })
    public void testGetSocksStatus200(String color, String operation, int cottonPart) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/socks")
                        .queryParam("color", color)
                        .queryParam("operation", operation)
                        .queryParam("cottonPart", String.valueOf(cottonPart)))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @ParameterizedTest
    @CsvSource({
            "red, more, 90",
            "blue, lessThan, 10",
            "yellow, equal, -1",
            "black, moreThan, 101",
    })
    public void testGetSocksStatus400(String color, String operation, int cottonPart) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/socks")
                        .queryParam("color", color)
                        .queryParam("operation", operation)
                        .queryParam("cottonPart", String.valueOf(cottonPart)))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @NotNull
    private static JSONObject getObject(String color, int cottonPart, int quantity) throws JSONException {
        JSONObject socksCreate = new JSONObject();
        socksCreate.put("color", color);
        socksCreate.put("cottonPart", cottonPart);
        socksCreate.put("quantity", quantity);
        return socksCreate;
    }
}