package com.example.cinemaES.controller;


import com.example.cinemaES.dto.UserDto;
import com.example.cinemaES.service.UserManagmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usersmenager")
public class UserManagmentController {
    private final UserManagmentService userManagmentService;

    @DeleteMapping(path = "/delete", params = "userId")
    public ResponseEntity<Boolean> deleteUserById(
            @Min(value = 1, message = "User ID must be greater than or equal to 1") @RequestParam Integer userId){
        return ResponseEntity.ok(userManagmentService.deleteUserById(userId));
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userManagmentService.getAllUsers());
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateUserRole(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userManagmentService.updateUserRole(userDto));
    }


}
