package com.changejar.service;

import com.changejar.dto.TransactionDTO;
import com.changejar.dto.external.CurrencyDTO;
import com.changejar.entity.CustomerAccount;
import com.changejar.entity.Transaction;
import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import com.changejar.kiranaregister.KiranaRegisterApplication;
import com.changejar.repository.CustomerAccountRepository;
import com.changejar.repository.TransactionRepository;
import com.changejar.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {KiranaRegisterApplication.class})
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private CustomerAccountService customerAccountService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CurrencyService currencyService;

    @Test
    void shouldReturnNull_whenTransactionDTONullTest() {
        assertNull(transactionService.save(null));
        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    void shouldThrowException_whenNoCustomerAccountExistsTest() {

        TransactionDTO fakeTransactionDTO = new TransactionDTO(
                111111L,
                222222L,
                null,
                null,
                null,
                0.0
        );

        when(customerAccountService.getByKiranaStoreIdAndUserId(any(Long.class), any(Long.class)))
                .thenReturn(Optional.empty());

        assertThrows(
                IllegalStateException.class,
                () -> transactionService.save(fakeTransactionDTO)
        );

        verify(transactionRepository, times(0)).save(any());
    }

    @Test
    void whenTransactionSave_thenShouldUpdateCustomerAccountTest() {

        Transaction fakeTransaction = new Transaction(
                null,
                1L,
                4L,
                null,
                TransactionType.DEBIT,
                10.0);

        TransactionDTO fakeTransactionDTO = new TransactionDTO(
                1L,
                4L,
                null,
                TransactionType.DEBIT,
                CurrencyType.USD,
                10.0);

        CustomerAccount fakecustomerAccount = new CustomerAccount(
                3333L,
                1L,
                4L,
                140.0,
                null,
                null
        );

        CurrencyDTO fakeCurrencyDTO = new CurrencyDTO(Map.of("USD", 1.0, "INR", 83.34534534));

        when(transactionRepository.save(any(Transaction.class))).thenReturn(fakeTransaction);

        when(customerAccountService.getByKiranaStoreIdAndUserId(any(Long.class), any(Long.class)))
                .thenReturn(Optional.of(fakecustomerAccount));

        when(currencyService.getCurrencyConversionRates()).thenReturn(fakeCurrencyDTO);

        transactionService.save(fakeTransactionDTO);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(customerAccountService, times(1)).save(any(CustomerAccount.class));
    }

    @Test
    void whenTransactionSave_thenCustomerAccountSave_verifyUpdatedAmount_forUSD_Test() {

        TransactionDTO fakeTransactionDTO = new TransactionDTO(
                1L,
                4L,
                null,
                TransactionType.DEBIT,
                CurrencyType.USD,
                10.0);

        CurrencyDTO fakeCurrencyDTO = new CurrencyDTO(Map.of("USD", 1.0, "INR", 83.34534534));

        Transaction fakeTransaction = new Transaction(
                null,
                1L,
                4L,
                null,
                TransactionType.DEBIT,
                10.0);

        CustomerAccount fakecustomerAccount = new CustomerAccount(
                3333L,
                1L,
                4L,
                140.0,
                null,
                null
        );

        when(transactionRepository.save(any(Transaction.class))).thenReturn(fakeTransaction);

        when(customerAccountService.getByKiranaStoreIdAndUserId(any(Long.class), any(Long.class)))
                .thenReturn(Optional.of(fakecustomerAccount));

        when(currencyService.getCurrencyConversionRates()).thenReturn(fakeCurrencyDTO);

        transactionService.save(fakeTransactionDTO);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(customerAccountService, times(1)).save(any(CustomerAccount.class));

        assertEquals(140.0 + 10.0, fakecustomerAccount.getAmountPending());
    }

    @Test
    void whenTransactionSave_thenCustomerAccountSave_verifyUpdatedAmount_forINR_Test() {

        TransactionDTO fakeTransactionDTO = new TransactionDTO(
                1L,
                4L,
                null,
                TransactionType.DEBIT,
                CurrencyType.INR,
                100.0);

        CurrencyDTO fakeCurrencyDTO = new CurrencyDTO(Map.of("USD", 1.0, "INR", 83.345));

        Transaction fakeTransaction = new Transaction(
                null,
                1L,
                4L,
                null,
                TransactionType.DEBIT,
                100.0 / fakeCurrencyDTO.getRates().get(fakeTransactionDTO.getCurrencyType().toString()));

        CustomerAccount fakecustomerAccount = new CustomerAccount(
                3333L,
                1L,
                4L,
                140.0,
                null,
                null
        );

        when(transactionRepository.save(any(Transaction.class))).thenReturn(fakeTransaction);

        when(customerAccountService.getByKiranaStoreIdAndUserId(any(Long.class), any(Long.class)))
                .thenReturn(Optional.of(fakecustomerAccount));

        when(currencyService.getCurrencyConversionRates()).thenReturn(fakeCurrencyDTO);

        transactionService.save(fakeTransactionDTO);

        verify(transactionRepository, times(1)).save(any(Transaction.class));
        verify(customerAccountService, times(1)).save(any(CustomerAccount.class));

        assertEquals(
                140.0 + 100.0 / fakeCurrencyDTO.getRates().get(fakeTransactionDTO.getCurrencyType().toString()),
                fakecustomerAccount.getAmountPending());
    }
}
