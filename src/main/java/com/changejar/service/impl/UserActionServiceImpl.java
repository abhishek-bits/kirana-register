package com.changejar.service.impl;

import com.changejar.dto.UserActionDTO;
import com.changejar.entity.UserAction;
import com.changejar.enums.CurrencyType;
import com.changejar.repository.UserActionRepository;
import com.changejar.service.UserActionService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserActionServiceImpl implements UserActionService {

    @Autowired
    private UserActionRepository userActionRepository;

    @Override
    public UserAction save(UserActionDTO userActionDTO) {
        return save(new UserAction(
                null,
                userActionDTO.getKiranaStoreId(),
                userActionDTO.getCustomerId(),
                userActionDTO.getAmountPending(),
                System.currentTimeMillis(),
                System.currentTimeMillis()
        ));
    }

    @Override
    public UserAction save(UserAction userAction) {
        return userActionRepository.save(userAction);
    }

    @Override
    public Collection<UserActionDTO> getUserActionsByDateRangeAndCurrencyTypeAndKiranaStoreId(
            LocalDateTime fromDateTime,
            @Nullable LocalDateTime toDateTime,
            CurrencyType currencyType,
            Long kiranaStoreId) {
        return null;
    }

    @Override
    public Optional<UserAction> getByKiranaStoreIdAndUserId(Long kiranaStoreId, Long userId) {
        return userActionRepository.findByKiranaStoreIdAndUserId(kiranaStoreId, userId);
    }
}
