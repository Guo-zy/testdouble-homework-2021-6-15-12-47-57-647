package com.tw.banking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PrinterTest {

    private final List<Transaction> transactions = new ArrayList<>();
    private Transaction transaction;
    private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
    private static final String SEPARATOR = " | ";

    @BeforeEach
    private void setUp(){
        final String date = "2021/6/24";
        final int amount = 100;
        transaction = new Transaction(date, amount);
        transactions.add(transaction);
    }

    @Test
    void should_console_call_printLine_successful_when_printer_call_print(){
        //given
        Console console = mock(Console.class);
        Printer printer = new Printer(console);

        //when
        printer.print(transactions);

        //then
        verify(console,times(1)).printLine(STATEMENT_HEADER);
    }

    @Test
    void should_return_statement_of_date_amount_balance_when_call_statmentLine_given_transaction_and_runningBalance(){
        //given
        final int runningBalance  = 100;
        Console console = new Console();
        Printer printer = new Printer(console);

        //when
        String actualRes = printer.statementLine(transaction,runningBalance);

        //then
        assertEquals(transaction.date()
                + SEPARATOR
                + transaction.amount()
                + SEPARATOR
                + runningBalance,actualRes);
    }

}