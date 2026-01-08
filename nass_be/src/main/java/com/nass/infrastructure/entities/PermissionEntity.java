package com.nass.infrastructure.entities;

import com.nass.contract.enums.ApiMethodEnum;
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
@Table(name = "permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionEntity extends BaseEntity<Integer> {
    @Column(name = "api_path")
    String apiPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "api_method")
    ApiMethodEnum apiMethod;

    @ManyToMany(mappedBy = "permissions")
    List<RoleEntity> roles;
}
