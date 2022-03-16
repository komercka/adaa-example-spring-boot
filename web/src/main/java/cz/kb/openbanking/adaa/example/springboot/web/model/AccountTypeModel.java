package cz.kb.openbanking.adaa.example.springboot.web.model;

import org.springframework.util.Assert;

/**
 * Contains all possible account types.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public enum AccountTypeModel {

    /**
     * Komercni Banka account.
     */
    KB("KB"),

    /**
     * Account from another bank.
     */
    AG("AG");

    private String accountType;

    /**
     * No instance.
     *
     * @param accountType account type
     */
    AccountTypeModel(String accountType) {
        Assert.hasText(accountType, "accountType must not be empty");

        this.accountType = accountType;
    }

    /**
     * Gets account type.
     *
     * @return account type
     */
    public String getAccountType() {
        return accountType;
    }
}
