package bank.api.v1.controller;

import bank.api.v1.dto.CreateClient;
import bank.domain.model.Client;
import bank.domain.model.Product;
import bank.api.v1.service.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController
@RequestMapping("/v1")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> get(@PathVariable("id") String id) {
        return new ResponseEntity<>(clientService.get(HtmlUtils.htmlEscape(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<CreateClient> save(@RequestBody CreateClient createClient) {
        return new ResponseEntity<>(clientService.save(createClient), HttpStatus.OK);
    }

    @PatchMapping(value = "/client/{id}/status/upgrade")
    public ResponseEntity<Client> upgradeStatus(@PathVariable("id") String id) {
        return new ResponseEntity<>(clientService.upgradeStatus(HtmlUtils.htmlEscape(id)), HttpStatus.OK);
    }

    @PatchMapping(value = "/client/{id}/status/downgrade")
    public ResponseEntity<Client> downgradeStatus(@PathVariable("id") String id) {
        return new ResponseEntity<>(clientService.downgradeStatus(HtmlUtils.htmlEscape(id)), HttpStatus.OK);
    }

    @GetMapping(value = "/client/{id}/products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable("id") String id) {
        return new ResponseEntity<>(clientService.getProducts(HtmlUtils.htmlEscape(id)), HttpStatus.OK);
    }
}