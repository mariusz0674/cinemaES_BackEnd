package com.example.cinemaES.dto;
import com.example.cinemaES.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    @NotNull(message = "UserId is mandatory")
    private Integer id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Role role;

}
