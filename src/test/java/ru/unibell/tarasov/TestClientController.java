package ru.unibell.tarasov;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.unibell.tarasov.DTO.ClientMapper;
import ru.unibell.tarasov.DTO.ContactInfoMapper;
import ru.unibell.tarasov.controller.ClientController;
import ru.unibell.tarasov.repository.ClientRepository;
import ru.unibell.tarasov.service.ClientService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClientController.class)
@ActiveProfiles("test")
@Sql(scripts = "classpath:data.sql")
public class TestClientRESTController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClientService clientService;
    @MockBean
    private ClientRepository repository;
    @MockBean
    private ClientMapper clientMapper;
    @MockBean
    private ContactInfoMapper contactInfoMapper;

    @Test
    public void getAllClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                            .get("/client/getAll")
                            .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
