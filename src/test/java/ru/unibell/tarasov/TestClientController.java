package ru.unibell.tarasov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.unibell.tarasov.DTO.ClientDTO;
import ru.unibell.tarasov.DTO.ContactInfoDTO;
import ru.unibell.tarasov.entity.ContactType;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "classpath:data.sql")
public class TestClientController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getAllClients() throws Exception {

        List<ClientDTO> clients = List.of(new ClientDTO(10, "TestLastName", "TestFirstName", "TestPatronymic"));

        final String expectedJson = mapper.writeValueAsString(clients);

        mockMvc.perform(MockMvcRequestBuilders
                            .get("/client/getAll")
                            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void getClientById() throws Exception {

        ClientDTO client = new ClientDTO(10, "TestLastName", "TestFirstName", "TestPatronymic");

        final String expectedJson = mapper.writeValueAsString(client);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/get/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void getClientByBadId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/get/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getContactInfo() throws Exception {
        List<ContactInfoDTO> contacts = List.of(new ContactInfoDTO(10, ContactType.PHONE, "+7 (111) 111-11-11"),
                new ContactInfoDTO(11, ContactType.EMAIL, "test@test.test"));

        final String expectedJson = mapper.writeValueAsString(contacts);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/contact/get/10")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void getContactInfoByBadId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/contact/get/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getContactInfoEmail() throws Exception {
        List<ContactInfoDTO> contacts = List.of(new ContactInfoDTO(11, ContactType.EMAIL, "test@test.test"));

        final String expectedJson = mapper.writeValueAsString(contacts);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/contact/get/10/EMAIL")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void getContactInfoPhone() throws Exception {
        List<ContactInfoDTO> contacts = List.of(new ContactInfoDTO(10, ContactType.PHONE, "+7 (111) 111-11-11"));

        final String expectedJson = mapper.writeValueAsString(contacts);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/client/contact/get/10/PHONE")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void createClient() throws Exception {
        ClientDTO client = new ClientDTO(1, "TestLastName", "TestFirstName", "TestPatronymic");

        final String jsonToPost = mapper.writeValueAsString(client);

        mockMvc.perform(MockMvcRequestBuilders
                            .post("/client/create")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(jsonToPost))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createContact() throws Exception {
        ContactInfoDTO contact = new ContactInfoDTO(1, ContactType.PHONE, "+7 (222) 222-22-22");

        final String jsonToPost = mapper.writeValueAsString(contact);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/contact/create/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToPost))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createContactToBadClient() throws Exception {
        ContactInfoDTO contact = new ContactInfoDTO(1, ContactType.PHONE, "+7 (222) 222-22-22");

        final String jsonToPost = mapper.writeValueAsString(contact);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/client/contact/create/11")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToPost))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
