package bank.api.v1.controller;

import bank.api.v1.dto.ClientDto;
import bank.api.v1.mapper.ClientMapper;
import bank.domain.model.Client;
import bank.domain.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/v1")
public class ClientController {

    private ClientMapper clientMapper;
    private ClientService clientService;

    @Autowired
    public ClientController(ClientMapper clientMapper,
                            ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> get(@PathVariable("id") String id) {
        Client client = clientService.get(HtmlUtils.htmlEscape(id));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<Client> save(@RequestBody ClientDto clientDto) {
        Client client = clientMapper.toClient(clientDto);
        return new ResponseEntity<>(clientService.save(client), HttpStatus.OK);
    }
}