package cz.kb.openbanking.adaa.example.springboot.web.mapper;

import javax.annotation.Nullable;

import cz.kb.openbanking.adaa.client.model.generated.AccountBalance;
import cz.kb.openbanking.adaa.client.model.generated.AccountTransaction;
import cz.kb.openbanking.adaa.example.springboot.web.model.AccountBalanceModel;
import cz.kb.openbanking.adaa.example.springboot.web.model.TransactionModel;
import org.mapstruct.Mapper;

/**
 * Mapper interface.
 *
 * @author <a href="mailto:aleh_kuchynski@kb.cz">Aleh Kuchynski</a>
 * @since 1.0
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

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
}
