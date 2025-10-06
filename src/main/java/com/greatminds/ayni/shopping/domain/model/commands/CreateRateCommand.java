package com.greatminds.ayni.shopping.domain.model.commands;

public record CreateRateCommand(Long rate, String date, Long productId, Long userId) {
}
