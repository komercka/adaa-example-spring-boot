package cz.kb.openbanking.adaa.example.springboot.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import cz.kb.openbanking.adaa.example.springboot.web.common.EndpointUris;
import cz.kb.openbanking.adaa.example.springboot.web.mapper.AccountMapper;
import cz.kb.openbanking.adaa.example.springboot.web.model.AccountModel;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Resource to get accounts of the KB ADAA API.
 *
 * @since 1.2
 */
@Controller
@RequestMapping(EndpointUris.ACCOUNTS)
@AllArgsConstructor
public class AccountController {

    private final AccountApi accountApi;

    private final AccountMapper mapper;

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * Endpoint to get client's accounts.
     *
     * @return HTML page with client's accounts
     */
    @GetMapping
    public ModelAndView accounts() {
        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        // calling ADAA Client API SDK to get list of client's accounts
        List<AccountModel> accounts = accountApi.accounts(oAuth2FlowProvider.getAccessToken())
                                                .find()
                                                .stream()
                                                .map(mapper::toAccountModel)
                                                .collect(Collectors.toList());

        return new ModelAndView("accounts", Collections.singletonMap("accounts", accounts));
    }

}
