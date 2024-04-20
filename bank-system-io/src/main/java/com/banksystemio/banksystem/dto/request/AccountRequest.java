package com.banksystemio.banksystem.dto.request;

import com.banksystemio.banksystem.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class AccountRequest {


    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "O nome não pode conter caracteres especiais")
    private String name;

    @Email(message = "O email deve ser válido")
    private String email;

    @NotNull(message = "O número da conta não pode estar vazio")
    @PositiveOrZero
    private Integer accountNumber;

    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    private String password;

}
