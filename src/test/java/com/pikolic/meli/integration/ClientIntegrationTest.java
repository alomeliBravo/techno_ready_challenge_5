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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class ClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Create Client correctly")
    void shouldCreateClient() throws Exception{
        String json = """
                {"name":"Angel Lomelí","age":24,"email":"alomelibravo@gmail.com","address":"Avenida los venados #408"}
        """;
        mockMvc.perform(post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.age").value(24));

    }

    @Test
    @DisplayName("Get Clients correctly")
    void shouldGetClient() throws Exception{

        String json = """
                {"name":"Angel","age":24,"email":"abravo@gmail.com","address":"Avenida los venados #408"}
        """;
        mockMvc.perform(post("/api/v1/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                        .andExpect(status().isCreated());

        mockMvc.perform(get("/api/v1/clients/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Angel"));
    }

    @Test
    @DisplayName("Get Client by ID")
    void shouldGetClientById() throws Exception{
        String json = """
                {"name":"Angel","age":24,"email":"abravo@gmail.com","address":"Avenida los venados #408"}
        """;

        MvcResult result = mockMvc.perform(post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Integer clientId = JsonPath.read(responseBody, "$.id");

        mockMvc.perform(get("/api/v1/clients/" + clientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Angel"));
    }

    @Test
    @DisplayName("Update Client by ID")
    void shouldUpdateClientById() throws Exception{
        String json = """
                {"name":"Angel","age":24,"email":"abravo@gmail.com","address":"Avenida los venados #408"}
        """;

        MvcResult result = mockMvc.perform(post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Integer clientId = JsonPath.read(responseBody, "$.id");

        String updateJson = """
                {"name":"Angel Daniel","age":25,"email":"alomelibravo@gmail.com","address":"Avenida los venados #404"}
        """;

        mockMvc.perform(put("/api/v1/clients/" + clientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Angel Daniel"))
                .andExpect(jsonPath("$.age").value(25));
    }

    @Test
    @DisplayName("Delete Client by ID")
    void shouldDeleteClientById() throws Exception{
        String json = """
                {"name":"Angel","age":24,"email":"abravo@gmail.com","address":"Avenida los venados #408"}
        """;

        MvcResult result = mockMvc.perform(post("/api/v1/clients/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        Integer clientId = JsonPath.read(responseBody, "$.id");

        mockMvc.perform(delete("/api/v1/clients/" + clientId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/clients/" +  clientId))
                .andExpect(status().isNotFound());
    }
}
