package cz.kb.openbanking.adaa.example.springboot.web.controller;

import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import cz.kb.openbanking.adaa.client.api.model.Account;
import cz.kb.openbanking.adaa.client.api.model.PageSlice;
import cz.kb.openbanking.adaa.client.model.generated.AccountBalance;
import cz.kb.openbanking.adaa.client.model.generated.AccountTransaction;
import cz.kb.openbanking.adaa.example.springboot.core.configuration.AdaaProperties;
import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.mapper.AccountMapper;
import cz.kb.openbanking.adaa.example.springboot.web.model.TransactionModel;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.iban4j.Iban;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Resource that returns a transactions history by the KB ADAA API.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Controller
@RequestMapping(EndpointUris.TRANSACTIONS)
@AllArgsConstructor
public class TransactionHistoryController {

    private final AccountApi accountApi;

    private final AccountMapper mapper;

    private final AdaaProperties adaaProperties;

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * Endpoint that serves for getting client's transactions history.
     *
     * @return HTML page with client's transactions history
     */
    @GetMapping
    public ModelAndView transactions() {
        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        Account account = new Account(Iban.valueOf(adaaProperties.getIban()), Currency.getInstance(adaaProperties.getCurrency()));
        // calling ADAA Client API SDK for getting transaction history
        PageSlice<AccountTransaction> pageSlice =
                accountApi.transactions(account, oAuth2FlowProvider.getAccessToken()).page(0).find();
        List<TransactionModel> transactions = pageSlice.getContent().stream()
                                                       .map(mapper::toTransactionModel).collect(Collectors.toList());

        List<AccountBalance> accountBalances =
                accountApi.balances(account, oAuth2FlowProvider.getAccessToken()).find();

        Map<String, Object> model = new HashMap<>();
        model.put("iban", adaaProperties.getIban());
        model.put("transactions", transactions);
        // for the example purposes only the first one account's balance was used
        model.put("balance", mapper.toAccountBalanceModel(accountBalances.get(0)));

        return new ModelAndView("transactions", model);
    }
}
