package com.devteria.identity_service.dto.response;

import com.devteria.identity_service.entity.Employee;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    String token;
    boolean authenticated;
    EmployeeResponse employee;
}
