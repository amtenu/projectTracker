package com.amtenu.repository;

import com.amtenu.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long > {
    Subscription findByUserId(Long userId);
}
