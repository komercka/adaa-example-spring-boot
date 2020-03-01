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
import cz.kb.openbanking.adaa.example.springboot.web.mapper.TransactionMapper;
import cz.kb.openbanking.adaa.example.springboot.web.model.TransactionModel;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import org.iban4j.Iban;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Resource that returns a transactions history by the KB ADAA API.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Controller
@RequestMapping(EndpointUris.TRANSACTIONS)
public class TransactionHistoryController {

    private final AccountApi accountApi;

    private final TransactionMapper mapper;

    private final AdaaProperties adaaProperties;

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * New instance.
     *
     * @param accountApi         {@link AccountApi}
     * @param mapper             {@link TransactionMapper}
     * @param adaaProperties     {@link AdaaProperties}
     * @param oAuth2FlowProvider {@link OAuth2FlowProvider}
     */
    public TransactionHistoryController(AccountApi accountApi, TransactionMapper mapper,
                                        AdaaProperties adaaProperties, OAuth2FlowProvider oAuth2FlowProvider)
    {
        Assert.notNull(accountApi, "accountApi must not be null");
        Assert.notNull(mapper, "mapper must not be null");
        Assert.notNull(adaaProperties, "adaaProperties must not be null");
        Assert.notNull(oAuth2FlowProvider, "oAuth2FlowProvider must not be null");

        this.accountApi = accountApi;
        this.mapper = mapper;
        this.adaaProperties = adaaProperties;
        this.oAuth2FlowProvider = oAuth2FlowProvider;
    }

    /**
     * Endpoint that serves for getting client's transactions history.
     *
     * @return HTML page with client's transactions history
     */
    @GetMapping
    public ModelAndView transactions() {
        // check access token
        if (oAuth2FlowProvider.getAccessToken() == null) {
            String redirectUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                                            .path(EndpointUris.AUTHORIZATION)
                                                            .build().toUriString();
            oAuth2FlowProvider.authorizationRedirect(redirectUri);
        }

        Account account = new Account(Iban.valueOf(adaaProperties.getIban()),
                Currency.getInstance(adaaProperties.getCurrency()));

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
