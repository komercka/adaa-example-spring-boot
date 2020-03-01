package cz.kb.openbanking.adaa.example.springboot.web.model;

import javax.annotation.Nullable;

/**
 * Contains details bout transaction counterparty.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public class TransactionCounterpartyModel {
    private String iban;
    private String name;
    private String accountNo;
    private String bankBic;
    private String bankCode;
    private String bankName;

    /**
     * Gets IBAN code.
     *
     * @return IBAN code
     */
    @Nullable
    public String getIban() {
        return iban;
    }

    /**
     * Sets IBAN code.
     *
     * @param iban IBAN code
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Gets name of the counter party.
     *
     * @return name of the counter party
     */
    @Nullable
    public String getName() {
        return name;
    }

    /**
     * Sets name of the counter party.
     *
     * @param name name of the counter party
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets account number.
     *
     * @return  account number
     */
    @Nullable
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * Sets account number.
     *
     * @param accountNo account number
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * Gets bank BIC code.
     *
     * @return bank BIC code
     */
    @Nullable
    public String getBankBic() {
        return bankBic;
    }

    /**
     * Sets bank BIC code.
     *
     * @param bankBic bank BIC code
     */
    public void setBankBic(String bankBic) {
        this.bankBic = bankBic;
    }

    /**
     * Gets bank code.
     *
     * @return bank code
     */
    @Nullable
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Sets bank code.
     *
     * @param bankCode bank code
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Gets name of the bank.
     *
     * @return name of the bank
     */
    @Nullable
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets name of the bank.
     *
     * @param bankName name of the bank
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
