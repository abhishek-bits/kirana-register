package com.changejar.service;

import com.changejar.dto.CustomerAccountDTO;
import com.changejar.entity.CustomerAccount;
import com.changejar.entity.KiranaStore;
import com.changejar.entity.User;
import com.changejar.exception.ResourceNotFoundException;
import com.changejar.kiranaregister.KiranaRegisterApplication;
import com.changejar.repository.CustomerAccountRepository;
import com.changejar.service.impl.CustomerAccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {KiranaRegisterApplication.class})
public class CustomerAccountServiceTest {

    @InjectMocks
    private CustomerAccountServiceImpl customerAccountService;

    @Mock
    private CustomerAccountRepository customerAccountRepository;

    @Mock
    private UserService userService;

    @Mock
    private KiranaStoreService kiranaStoreService;

    @Test
    void onAddCustomerAccount_whenUserDoesNotExist_thenShouldThrowException() {

        CustomerAccountDTO fakeCustomerAccountDTO = new CustomerAccountDTO(
                5L,
                2L,
                150.0,
                null,
                null);

        when(userService.getById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> customerAccountService.save(fakeCustomerAccountDTO)
        );

        verify(customerAccountRepository, times(0)).save(any(CustomerAccount.class));
    }

    @Test
    void onAddCustomerAccount_whenKiranaStoreDoesNotExist_thenShouldThrowExceptionTest() {

        CustomerAccountDTO fakeCustomerAccountDTO = new CustomerAccountDTO(
                5L,
                2L,
                150.0,
                null,
                null);

        User fakeUser = new User(5L, null, null, null);

        when(userService.getById(any(Long.class))).thenReturn(Optional.of(fakeUser));

        when(kiranaStoreService.getById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> customerAccountService.save(fakeCustomerAccountDTO)
        );

        verify(customerAccountRepository, times(0)).save(any(CustomerAccount.class));
    }

    @Test
    void onAddCustomerAccount_whenInputValid_thenShouldSaveTest() {

        CustomerAccountDTO fakeCustomerAccountDTO = new CustomerAccountDTO(
                5L,
                2L,
                150.0,
                null,
                null);

        User fakeUser = new User(5L, null, null, null);

        KiranaStore fakeKiranaStore = new KiranaStore(2L, null, null, null, null, null);

        when(userService.getById(any(Long.class))).thenReturn(Optional.of(fakeUser));

        when(kiranaStoreService.getById(any(Long.class))).thenReturn(Optional.of(fakeKiranaStore));

        customerAccountService.save(fakeCustomerAccountDTO);

        verify(customerAccountRepository, times(1)).save(any(CustomerAccount.class));
    }
}
