package com.nass.infrastructure.entities;

import com.nass.contract.enums.RoleNameEnum;
import com.nass.infrastructure.entities.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity<Integer> {

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    RoleNameEnum roleName;

    @OneToMany(mappedBy = "role")
    List<UserEntity> users;

    @ManyToMany
    @JoinTable(name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<PermissionEntity> permissions;
}
