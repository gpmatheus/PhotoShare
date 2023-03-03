package com.sonego.PhotoShare.business.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole roleName) {
        this.name = roleName;
        this.id = UUID.randomUUID();
    }
    @Override
    public String getAuthority() {
        return name.toString();
    }
}
