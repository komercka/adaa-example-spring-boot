package cz.kb.openbanking.adaa.example.springboot.web.mapper;

import javax.annotation.Nullable;

import cz.kb.openbanking.adaa.client.model.generated.Account;
import cz.kb.openbanking.adaa.client.model.generated.AccountBalance;
import cz.kb.openbanking.adaa.client.model.generated.AccountTransaction;
import cz.kb.openbanking.adaa.client.model.generated.Statement;
import cz.kb.openbanking.adaa.example.springboot.web.model.AccountBalanceModel;
import cz.kb.openbanking.adaa.example.springboot.web.model.AccountModel;
import cz.kb.openbanking.adaa.example.springboot.web.model.StatementModel;
import cz.kb.openbanking.adaa.example.springboot.web.model.TransactionModel;
import org.mapstruct.Mapper;

/**
 * Mapper interface.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {


    /**
     * Maps {@link Account} to the {@link AccountModel}.
     *
     * @param account {@link Account}
     * @return {@link AccountModel}, {@code NULL} when input is {@code NULL}
     */
    @Nullable
    AccountModel toAccountModel(@Nullable Account account);

    /**
     * Maps {@link AccountTransaction} to the {@link TransactionModel}.
     *
     * @param transaction {@link AccountTransaction}
     * @return {@link TransactionModel}, {@code NULL} when input is {@code NULL}
     */
    @Nullable
    TransactionModel toTransactionModel(@Nullable AccountTransaction transaction);

    /**
     * Maps {@link AccountBalance} to the {@link AccountBalanceModel}.
     *
     * @param accountBalance {@link AccountBalance}
     * @return {@link AccountBalanceModel}, {@code NULL} when input is {@code NULL}
     */
    @Nullable
    AccountBalanceModel toAccountBalanceModel(@Nullable AccountBalance accountBalance);

    /**
     * Maps {@link Statement} to the {@link StatementModel}.
     *
     * @param statement {@link Statement}
     * @return {@code null} if input {@link Statement} is {@code null}, otherwise - {@link StatementModel}
     */
    @Nullable
    StatementModel toStatementModel(@Nullable Statement statement);
}
