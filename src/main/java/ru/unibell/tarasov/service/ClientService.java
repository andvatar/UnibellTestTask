package ru.unibell.tarasov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.unibell.tarasov.DTO.ClientDTO;
import ru.unibell.tarasov.DTO.ClientMapper;
import ru.unibell.tarasov.DTO.ContactInfoDTO;
import ru.unibell.tarasov.DTO.ContactInfoMapper;
import ru.unibell.tarasov.entity.Client;
import ru.unibell.tarasov.entity.ContactType;
import ru.unibell.tarasov.repository.ClientRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ContactInfoMapper contactInfoMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, ContactInfoMapper contactInfoMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.contactInfoMapper = contactInfoMapper;
    }

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper::clientToClientDTO).toList();
    }

    public ClientDTO getClientById(int id) {
        return clientMapper.clientToClientDTO(clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException(String.format("Client not found by id = %d", id))));
    }

    public void addClient(ClientDTO client) {
        clientRepository.save(clientMapper.clientDTOToClient(client));
    }

    public List<ContactInfoDTO> getAllContactInfo(int id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Client not found by id = %d", id)))
                .getContactInfo()
                .stream()
                .map(contactInfoMapper::contactInfoToContactInfoDTO)
                .toList();
    }

    public List<ContactInfoDTO> getContactInfoByType(int id, ContactType type) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Client not found by id = %d", id)))
                .getContactInfo()
                .stream()
                .filter(c -> c.getType().equals(type))
                .map(contactInfoMapper::contactInfoToContactInfoDTO)
                .toList();
    }

    public void addContactInfo(ContactInfoDTO contactInfo, int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new NoSuchElementException(String.format("Client not found by id = %d", clientId)));
        client.getContactInfo().add(contactInfoMapper.contactInfoDTOToContactInfo(contactInfo));
        clientRepository.save(client);
    }
}
