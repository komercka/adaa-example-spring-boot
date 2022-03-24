package cz.kb.openbanking.adaa.example.springboot.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import cz.kb.openbanking.adaa.client.api.model.PageSlice;
import cz.kb.openbanking.adaa.client.model.generated.AccountBalance;
import cz.kb.openbanking.adaa.client.model.generated.AccountTransaction;
import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.mapper.AccountMapper;
import cz.kb.openbanking.adaa.example.springboot.web.model.TransactionModel;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Resource to get transactions history of the KB ADAA API.
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

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * Endpoint to get client's transactions history.
     *
     * @param accountId id of account
     * @return HTML page with client's transactions history
     */
    @GetMapping
    public ModelAndView transactions(@RequestParam String accountId) {
        Assert.hasText(accountId, "accountId must not be blank");

        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        // calling ADAA Client API SDK to get transaction history
        PageSlice<AccountTransaction> pageSlice =
            accountApi.transactions(accountId, oAuth2FlowProvider.getAccessToken()).page(0).find();
        List<TransactionModel> transactions = pageSlice.getContent().stream()
                                                       .map(mapper::toTransactionModel).collect(Collectors.toList());

        List<AccountBalance> accountBalances =
            accountApi.balances(accountId, oAuth2FlowProvider.getAccessToken()).find();

        Map<String, Object> model = new HashMap<>();
        model.put("transactions", transactions);
        // for the purpose of example only balance of the first account is used
        model.put("balance", mapper.toAccountBalanceModel(accountBalances.get(0)));
        model.put("accountId", accountId);

        return new ModelAndView("transactions", model);
    }
}
