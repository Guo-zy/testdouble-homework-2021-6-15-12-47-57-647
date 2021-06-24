package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTest {
    @Test
    void should_account_call_deposit_succussful_and_use_correct_amount_when_account_call_deposit_given_amount(){
        //give
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        ArgumentCaptor<Integer> amountCaptor = ArgumentCaptor.forClass(Integer.class);
        Printer printer = new Printer(new Console());
        Account account = new Account(transactionRepository,printer);
        final Integer amount = 100;

        //when
        account.deposit(amount);

        //then
        assertAll(
                ()->verify(transactionRepository,times(1)).addDeposit(amountCaptor.capture()),
                ()->assertEquals(amount, amountCaptor.getValue())
        );
    }
}