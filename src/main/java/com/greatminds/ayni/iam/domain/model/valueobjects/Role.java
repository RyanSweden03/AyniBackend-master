package com.greatminds.ayni.iam.domain.model.valueobjects;

import jakarta.persistence.*;
import lombok.Getter;

@Embeddable
public class Role {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    @Getter
    private ERole role;

    public Role(String role) {
        if(role.equals("farmer")) this.role = ERole.ROLE_FARMER;
        else this.role = ERole.ROLE_MERCHANT;
    }

    public Role() {}
}
