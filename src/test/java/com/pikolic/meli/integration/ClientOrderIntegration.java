package com.pikolic.meli.integration;

import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ClientOrderIntegration {

    @Autowired
    private MockMvc mockMvc;

    private final List<Integer> clientIds = new ArrayList<>();
    private final List<Integer> itemIds = new ArrayList<>();
    private final List<Integer> orderIds = new ArrayList<>();

    @BeforeEach
    public void setup() throws Exception {
        List<String> clientsJson = List.of(
                """
                {"name":"Angel Lomelí","age":24,"email":"alomelibravo@gmail.com","address":"Avenida los venados #408"}
                """,
                """
                {"name":"Maria Perez","age":30,"email":"mperez@gmail.com","address":"Calle Falsa 123"}
                """
        );

        clientIds.clear();
        for (String clientJson : clientsJson) {
            MvcResult result = mockMvc.perform(post("/api/v1/clients/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(clientJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            Integer clientId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
            clientIds.add(clientId);
        }

        List<String> itemsJson = List.of(
                """
                {"name":"Coca-Cola","description":"Coca-cola 600ml","price":25.5}
                """,
                """
                {"name":"Pepsi","description":"Pepsi 600ml","price":24.5}
                """
        );

        itemIds.clear();
        for (String itemJson : itemsJson) {
            MvcResult result = mockMvc.perform(post("/api/v1/items/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(itemJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            Integer itemId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
            itemIds.add(itemId);
        }

        orderIds.clear();
        List<String> ordersJson = List.of(
                """
                {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
                """.formatted(clientIds.get(0), itemIds.get(0)),
                """
                {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-22", "total": 24.5}
                """.formatted(clientIds.get(1), itemIds.get(1))
        );

        for (String orderJson : ordersJson) {
            MvcResult result = mockMvc.perform(post("/api/v1/orders/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(orderJson))
                    .andExpect(status().isCreated())
                    .andReturn();

            Integer orderId = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
            orderIds.add(orderId);
        }
    }

    @Test
    @DisplayName("Create Order for Client correctly")
    void shouldCreateOrderforClient() throws Exception{
        String orderForCLientJson = """
                {"itemId": %d}
                """.formatted(itemIds.get(0));

        mockMvc.perform(post("/api/v1/clients/" + clientIds.get(0) + "/orders/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderForCLientJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)))
                .andExpect(jsonPath("$.total").value(25.5));
    }

    @Test
    @DisplayName("Get all Orders of a Client")
    void shouldGetAllOrdersOfClient() throws Exception{
        mockMvc.perform(get("/api/v1/clients/" + clientIds.get(0) + "/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$[0].item_id").value(itemIds.get(0)))
                .andExpect(jsonPath("$[0].total").value(25.5));
    }

    @Test
    @DisplayName("Get specific Order of a Client")
    void shouldGetSpecificOrderOfClient() throws Exception{
        mockMvc.perform(get("/api/v1/clients/" + clientIds.get(0) + "/orders/" + orderIds.get(0)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)))
                .andExpect(jsonPath("$.total").value(25.5));
    }

    @Test
    @DisplayName("Update specific order of a Client")
    void shouldUpdateSpecificOrderOfClient() throws Exception{
        String orderUpdateJson = """
                {"itemId": %d}
                """.formatted(itemIds.get(1));

        mockMvc.perform(put("/api/v1/clients/" +  clientIds.get(0) + "/orders/" + orderIds.get(0))
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderUpdateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(1)))
                .andExpect(jsonPath("$.total").value(24.5));
    }

    @Test
    @DisplayName("Delete specific order of a Client")
    void shouldDeleteSpecificOrderOfClient() throws Exception{
        mockMvc.perform(delete("/api/v1/clients/" + clientIds.get(0) + "/orders/" + orderIds.get(0)))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/clients/" + clientIds.get(0) + "/orders/" +  orderIds.get(0)))
                .andExpect(status().isNotFound());
    }

}
