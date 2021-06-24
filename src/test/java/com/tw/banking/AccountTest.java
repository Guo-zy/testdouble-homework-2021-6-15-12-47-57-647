package com.tw.banking;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountTest {
    private final int amount = 100;

    @Test
    void should_transactionRepository_call_addDeposit_succussful_and_use_correct_amount_when_account_call_deposit_given_amount(){
        //give
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        ArgumentCaptor<Integer> amountCaptor = ArgumentCaptor.forClass(Integer.class);
        Printer printer = new Printer(new Console());
        Account account = new Account(transactionRepository,printer);

        //when
        account.deposit(amount);

        //then
        assertAll(
                ()->verify(transactionRepository,times(1)).addDeposit(amountCaptor.capture()),
                ()->assertEquals(amount, amountCaptor.getValue())
        );
    }

    @Test
    void should_transactionRepository_call_withdraw_succussful_and_use_correct_amount_when_account_call_withdraw_given_amount(){
        //give
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        ArgumentCaptor<Integer> amountCaptor = ArgumentCaptor.forClass(Integer.class);
        Printer printer = new Printer(new Console());
        Account account = new Account(transactionRepository,printer);

        //when
        account.withdraw(amount);

        //then
        assertAll(
                ()->verify(transactionRepository,times(1)).addWithdraw(amountCaptor.capture()),
                ()->assertEquals(amount, amountCaptor.getValue())
        );
    }

    @Test
    void should_printer_call_print_succussful_and_use_correct_Transactions_when_account_call_printStatement_given_Transactions(){
        //give
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        Printer printer = mock(Printer.class);
        ArgumentCaptor<List<Transaction>> listArgumentCaptor = ArgumentCaptor.forClass(List.class);
        Account account =  new Account(transactionRepository,printer);
        List<Transaction>  transactions = new ArrayList<>();
        when(transactionRepository.allTransactions()).thenReturn(transactions);

        //when
        account.printStatement();

        //then
        assertAll(
                ()->verify(printer,times(1)).print(listArgumentCaptor.capture()),
                ()->assertEquals(transactions, listArgumentCaptor.getValue())
        );
    }


}