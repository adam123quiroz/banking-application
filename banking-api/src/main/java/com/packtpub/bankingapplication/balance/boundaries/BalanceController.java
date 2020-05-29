package com.packtpub.bankingapplication.balance.boundaries;

import com.packtpub.bankingapplication.balance.domain.Balance;
import com.packtpub.bankingapplication.balance.domain.BalanceInformation;
import com.packtpub.bankingapplication.balance.service.BalanceService;
import com.packtpub.bankingapplication.security.domain.JwtUser;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class BalanceController {
    private final BalanceService balanceService;

    @Autowired
    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/secure/balance")
    public ResponseEntity<Balance> getBalance(HttpServletRequest request) {
        JwtUser user = (JwtUser) request.getAttribute("jwtUser");
        Optional<Balance> balance = balanceService.getCurrentBalance(user.getUsername());
        if (balance.isPresent()) {
            Balance balanceFound = balance.get();
            BalanceInformation information = new BalanceInformation();
            information.setBalance(balanceFound.getBalance());
            information.setCustomer(balanceFound.getCustomer().getUsername());
            return new ResponseEntity<Balance>(balanceFound, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
