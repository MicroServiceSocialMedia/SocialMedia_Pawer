package com.pawer.repository;


import com.pawer.repository.entity.Follow;
import com.pawer.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFollowRepository extends JpaRepository<Follow,Long> {

    Optional<Follow> findOptionalByUserIdAndFollowId(Long userId, Long followId);
}