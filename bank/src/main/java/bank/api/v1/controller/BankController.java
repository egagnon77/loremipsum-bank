package bank.api.v1.controller;

import bank.domain.model.Client;
import bank.domain.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class BankController {

    private BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping(value = "/client/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable("clientId") String clientId) {
        Client client = bankService.getClient(HtmlUtils.htmlEscape(clientId));

        if (client == null) {
            return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
        } else {
          return new ResponseEntity<>(client, HttpStatus.OK);
        }

    }
}
