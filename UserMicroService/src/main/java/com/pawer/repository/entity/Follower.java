package com.pawer.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Follower extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId; // benim ıd'm
    private Long followerId; //beni takip eden insanların/ takipçi idsi
    private int statee; // 0 takipleşmiyor 1_ istek attı bekliyor 2_ takip ediyor.



}