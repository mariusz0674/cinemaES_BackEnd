package com.example.cinemaES.controller;


import com.example.cinemaES.auth.dto.UserDto;
import com.example.cinemaES.entity.User;
import com.example.cinemaES.service.UserManagmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/usersmenager")
public class UserManagmentController {
    private final UserManagmentService userManagmentService;

    @DeleteMapping(path = "/delete", params = "userId")
    public ResponseEntity<Boolean> deleteUserById(@RequestParam Integer userId){
        return ResponseEntity.ok(userManagmentService.deleteUserById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userManagmentService.getAllUsers());
    }
    @GetMapping()
    public ResponseEntity<ArrayList<String>> sayHello() {
        ArrayList<String> demo = new ArrayList<>();
        demo.add("esloxddo");
        return ResponseEntity.ok(demo);
    }


}
