package cz.kb.openbanking.adaa.example.springboot.web.controller;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cz.kb.openbanking.adaa.client.api.AccountApi;
import cz.kb.openbanking.adaa.example.springboot.web.mapper.AccountMapper;
import cz.kb.openbanking.adaa.example.springboot.web.model.StatementModel;
import cz.kb.openbanking.adaa.example.springboot.web.oauth2.OAuth2FlowProvider;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
 * Resource to get account statements of the KB ADAA API.
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

    private final OAuth2FlowProvider oAuth2FlowProvider;

    /**
     * Endpoint that serves for downloading PDF account statement.
     *
     * @param accountId id of account
     * @return account statement as PDF file
     */
    @GetMapping("/list")
    public ModelAndView getAccountStatements(@RequestParam String accountId) {
        Assert.hasText(accountId, "accountId must not be blank");

        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        // get statements for last 30 days
        OffsetDateTime fromDateTime = OffsetDateTime.now(ZoneId.systemDefault()).minusDays(31).truncatedTo(ChronoUnit.DAYS);
        List<StatementModel> statements = accountApi.statements(accountId, oAuth2FlowProvider.getAccessToken(), fromDateTime)
                                                    .find().stream()
                                                    .map(accountMapper::toStatementModel)
                                                    .collect(Collectors.toList());

        Map<String, Object> model = new HashMap<>();
        model.put("statements", statements);
        model.put("accountId", accountId);

        return new ModelAndView("statements", model);
    }

    /**
     * Endpoint that serves for downloading PDF account statement.
     *
     * @param statementId id of statement
     * @param accountId   id of account
     * @return account statement as PDF file
     */
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> getPdfStatement(@RequestParam("id") Long statementId, @RequestParam String accountId) {
        Assert.notNull(statementId, "statementId must not be null");
        Assert.hasText(accountId, "accountId must not be blank");

        // check access token
        if (StringUtils.isBlank(oAuth2FlowProvider.getAccessToken())) {
            oAuth2FlowProvider.authorizationRedirect();
        }

        byte[] pdfStatement = accountApi.statementPdf(accountId, oAuth2FlowProvider.getAccessToken(), statementId).find();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(statementId + PDF_EXTENSION).build());

        return ResponseEntity.ok().headers(headers).body(pdfStatement);
    }
}
