package com.tw.banking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionRepositoryTest {
    @Test
    void should_addDeposit_successful_when_transactionRepository_call_addDeposit_given_date_and_amount(){
        //given
        final String date = "2021/6/24";
        final int amount = 100;
        Clock clock = mock(Clock.class);
        TransactionRepository transactionRepository = new TransactionRepository(clock);
        when(clock.todayAsString()).thenReturn(date);

        //when
        transactionRepository.addDeposit(amount);

        //then
        assertAll(
                ()-> assertEquals(1,transactionRepository.transactions.size()),
                ()-> assertEquals(amount,transactionRepository.transactions.get(0).amount()),
                ()-> assertEquals(date,transactionRepository.transactions.get(0).date())
        );
    }
}