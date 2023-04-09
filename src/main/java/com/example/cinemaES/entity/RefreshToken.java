package com.example.cinemaES.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @PrimaryKeyJoinColumn
    public User user;

    @Column(nullable = false)
    public String token;

    public boolean revoked;


}