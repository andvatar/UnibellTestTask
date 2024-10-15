package ru.unibell.tarasov.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import ru.unibell.tarasov.DTO.ClientDTO;
import ru.unibell.tarasov.DTO.ContactInfoDTO;
import ru.unibell.tarasov.entity.Client;
import ru.unibell.tarasov.entity.ContactInfo;
import ru.unibell.tarasov.entity.ContactType;
import ru.unibell.tarasov.service.ClientService;

import java.util.List;

@RestController()
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public void createClient(@RequestBody @Valid ClientDTO client) {
        clientService.addClient(client);
    }

    @PostMapping("/contact/create/{clientId}")
    public void createContact(@RequestBody @Valid ContactInfoDTO contactInfo, @PathVariable("clientId") int clientId) {
        clientService.addContactInfo(contactInfo, clientId);
    }

    @GetMapping("/getAll")
    public List<ClientDTO> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/get/{id}")
    public ClientDTO getClientById(@PathVariable("id") int id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/contact/get/{clientId}")
    public List<ContactInfoDTO> getAllContacts(@PathVariable("clientId") int clientId) {
        return clientService.getAllContactInfo(clientId);
    }

    @GetMapping("/contact/get/{clientId}/{type}")
    public List<ContactInfoDTO> getContactsByType(@PathVariable("clientId") int clientId, @PathVariable("type") ContactType type) {
        return clientService.getContactInfoByType(clientId, type);
    }
}
