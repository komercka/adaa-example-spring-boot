package cz.kb.openbanking.adaa.example.springboot.web.controller;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import cz.kb.openbanking.adaa.client.api.model.Account;
import cz.kb.openbanking.adaa.example.springboot.core.configuration.AdaaProperties;
import cz.kb.openbanking.adaa.example.springboot.web.mapper.AccountMapper;
import cz.kb.openbanking.adaa.example.springboot.web.model.StatementModel;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.iban4j.Iban;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This resource serves for getting account statements.
 *
 * @author <a href="mailto:o.kuchynski@gmail.com">Aleh Kuchynski</a>
 * @since 1.1
 */
@Controller
@RequestMapping("/statements")
@AllArgsConstructor
public class AccountStatementController {

    private static final String PDF_EXTENSION = ".pdf";

    private final AccountMapper accountMapper;

    private final AccountApi accountApi;

    private final AdaaProperties adaaProperties;

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * Endpoint that serves for downloading PDF account statement.
     *
     * @return account statement as PDF file
     */
    @GetMapping("/list")
    public ModelAndView getAccountStatements() {
        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        Account account = new Account(Iban.valueOf(adaaProperties.getIban()), Currency.getInstance(adaaProperties.getCurrency()));

        // get statements for last 30 days
        OffsetDateTime fromDateTime = OffsetDateTime.now(ZoneId.systemDefault()).minusDays(31).truncatedTo(ChronoUnit.DAYS);
        List<StatementModel> statements = accountApi.statements(account, oAuth2FlowProvider.getAccessToken(), fromDateTime)
                                                    .find().stream()
                                                    .map(accountMapper::toStatementModel)
                                                    .collect(Collectors.toList());

        return new ModelAndView("statements", Collections.singletonMap("statements", statements));
    }

    /**
     * Endpoint that serves for downloading PDF account statement.
     *
     * @return account statement as PDF file
     */
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getPdfStatement(@RequestParam("id") Long statementId) {
        Assert.notNull(statementId, "statementId must not be null");

        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        Account account = new Account(Iban.valueOf(adaaProperties.getIban()), Currency.getInstance(adaaProperties.getCurrency()));
        byte[] pdfStatement = accountApi.statementPdf(account, oAuth2FlowProvider.getAccessToken(), statementId).find();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(statementId + PDF_EXTENSION).build());

        return ResponseEntity.ok().headers(headers).body(pdfStatement);
    }
}
