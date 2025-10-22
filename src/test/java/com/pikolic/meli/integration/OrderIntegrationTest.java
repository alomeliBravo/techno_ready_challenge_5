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
public class OrderIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final List<Integer> clientIds = new ArrayList<>();
    private final List<Integer> itemIds = new ArrayList<>();

    @BeforeEach
    void setUp() throws Exception {
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
    }

    @Test
    @DisplayName("Create an Order Correctly")
    void shouldCreateOrder() throws Exception{
        String orderJson = """
            {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
        """.formatted(clientIds.get(0), itemIds.get(0));

        mockMvc.perform(post("/api/v1/orders/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(clientIds.get(0)));
    }

    @Test
    @DisplayName("Get All Orders correctly")
    void shouldGetAllOrders() throws Exception{
        String orderJson = """
            {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
        """.formatted(clientIds.get(0), itemIds.get(0));

        mockMvc.perform(post("/api/v1/orders/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)));

        mockMvc.perform(get("/api/v1/orders/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$[0].item_id").value(itemIds.get(0)));
    }

    @Test
    @DisplayName("Get Order by ID")
    void shouldGetOrderById() throws Exception{
        String orderJson = """
            {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
        """.formatted(clientIds.get(0), itemIds.get(0));

        MvcResult orderResult = mockMvc.perform(post("/api/v1/orders/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)))
                .andReturn();

        String orderResponse = orderResult.getResponse().getContentAsString();
        Integer orderId = JsonPath.read(orderResponse,"$.id");

        mockMvc.perform(get("/api/v1/orders/" + orderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)));
    }

    @Test
    @DisplayName("Update Order by ID")
    void shouldUpdateOrderById() throws Exception{
        String orderJson = """
            {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
        """.formatted(clientIds.get(0), itemIds.get(0));

        MvcResult orderResult = mockMvc.perform(post("/api/v1/orders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)))
                .andReturn();

        String orderResponse = orderResult.getResponse().getContentAsString();
        Integer orderId = JsonPath.read(orderResponse,"$.id");

        String updatedOrderJson = """
            {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
        """.formatted(clientIds.get(1), itemIds.get(1));

        mockMvc.perform(put("/api/v1/orders/" + orderId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedOrderJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.client_id").value(clientIds.get(1)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(1)));
    }

    @Test
    @DisplayName("Delete Order by ID")
    void shouldDeleteOrderById() throws Exception{
        String orderJson = """
            {"clientId": %d, "itemId": %d, "purchaseDate": "2025-10-21", "total": 25.5}
        """.formatted(clientIds.get(0), itemIds.get(0));

        MvcResult orderResult = mockMvc.perform(post("/api/v1/orders/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client_id").value(clientIds.get(0)))
                .andExpect(jsonPath("$.item_id").value(itemIds.get(0)))
                .andReturn();

        String orderResponse = orderResult.getResponse().getContentAsString();
        Integer orderId = JsonPath.read(orderResponse,"$.id");

        mockMvc.perform(delete("/api/v1/orders/" + orderId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/orders/" + orderId))
                .andExpect(status().isNotFound());
    }
}
