package com.pikolic.meli.integration;

import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ItemIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create Item Correctly")
    void shouldCreateItem() throws Exception {
        String json = """
                {"name":"Coca-Cola","description":"Coca-cola 600ml","price":25.5}
                """;

        mockMvc.perform(post("/api/v1/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coca-Cola"))
                .andExpect(jsonPath("$.price").value(25.5));
    }

    @Test
    @DisplayName("Get All Items correctly")
    void shouldGetAllItems() throws Exception {
        String json = """
                {"name":"Coca-Cola","description":"Coca-cola 600ml","price":25.5}
                """;

        mockMvc.perform(post("/api/v1/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coca-Cola"))
                .andExpect(jsonPath("$.price").value(25.5));

        mockMvc.perform(get("/api/v1/items"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Coca-Cola"))
                .andExpect(jsonPath("$[0].price").value(25.5));
    }

    @Test
    @DisplayName("Get Item by ID")
    void shouldGetItemById() throws Exception {
        String json = """
                {"name":"Coca-Cola","description":"Coca-cola 600ml","price":25.5}
                """;

        MvcResult result = mockMvc.perform(post("/api/v1/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coca-Cola"))
                .andReturn();

        String reponseBody = result.getResponse().getContentAsString();
        Integer itemId = JsonPath.read(reponseBody, "$.id");

        mockMvc.perform(get("/api/v1/items/" + itemId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Coca-Cola"))
                .andExpect(jsonPath("$.price").value(25.5));
    }

    @Test
    @DisplayName("Update Item by ID")
    void shouldUpdateItemById() throws Exception {
        String json = """
                {"name":"Coca-Cola","description":"Coca-cola 600ml","price":25.5}
                """;

        MvcResult result = mockMvc.perform(post("/api/v1/items/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coca-Cola"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Integer itemId = JsonPath.read(responseBody, "$.id");

        String updateJson = """
                {"name":"Fanta","description":"Fanta sabor Fresa 600ml","price":20.5}
                """;

        mockMvc.perform(put("/api/v1/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Fanta"))
                .andExpect(jsonPath("$.price").value(20.5));

    }

    @Test
    @DisplayName("Delete Item by ID")
    void shouldDeleteItemById() throws Exception {
        String json = """
                {"name":"Coca-Cola","description":"Coca-cola 600ml","price":25.5}
                """;

        MvcResult result = mockMvc.perform(post("/api/v1/items/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Coca-Cola"))
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Integer itemId = JsonPath.read(responseBody, "$.id");

        mockMvc.perform(delete("/api/v1/items/" + itemId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/items/" + itemId))
                .andExpect(status().isNotFound());
    }

}
