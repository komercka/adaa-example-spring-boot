package cz.kb.openbanking.adaa.example.springboot.web.model;

import javax.annotation.Nullable;

/**
 * Contains details of transaction references.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
public class TransactionReferencesModel {
    private String variable;
    private String constant;
    private String specific;
    private String receiver;

    /**
     * Gets transaction variable code.
     *
     * @return transaction variable code
     */
    @Nullable
    public String getVariable() {
        return variable;
    }

    /**
     * Sets transaction variable code.
     *
     * @param variable transaction variable code
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }

    /**
     * Gets transaction constant code.
     *
     * @return transaction constant code
     */
    @Nullable
    public String getConstant() {
        return constant;
    }

    /**
     * Sets transaction constant code.
     *
     * @param constant transaction constant code
     */
    public void setConstant(String constant) {
        this.constant = constant;
    }

    /**
     * Gets transaction specific code.
     *
     * @return transaction specific code
     */
    @Nullable
    public String getSpecific() {
        return specific;
    }

    /**
     * Sets transaction specific code.
     *
     * @param specific transaction specific code
     */
    public void setSpecific(String specific) {
        this.specific = specific;
    }

    /**
     * Gets information about receiver.
     *
     * @return information about receiver
     */
    @Nullable
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets information about receiver.
     *
     * @param receiver information about receiver
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
