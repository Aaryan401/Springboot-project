package com.example.BlogBYMay.Repository;

import com.example.BlogBYMay.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    public Optional<Profile> findByUserUserId(Long userId);

}
