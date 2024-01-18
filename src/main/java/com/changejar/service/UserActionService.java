package com.changejar.service;

import com.changejar.dto.UserActionDTO;
import com.changejar.entity.UserAction;
import com.changejar.enums.CurrencyType;
import com.changejar.enums.TransactionType;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface UserActionService {

    UserAction save(UserActionDTO userActionDTO);

    UserAction save(UserAction userAction);

    Collection<UserActionDTO> getUserActionsByDateRangeAndCurrencyTypeAndKiranaStoreId(
            LocalDateTime fromDateTime,
            @Nullable LocalDateTime toDateTime,
            CurrencyType currencyType,
            Long kiranaStoreId);

    Optional<UserAction> getByKiranaStoreIdAndUserId(Long kiranaStoreId, Long customerId);
}
