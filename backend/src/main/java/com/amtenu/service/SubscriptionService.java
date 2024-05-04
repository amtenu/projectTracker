package com.amtenu.service;

import com.amtenu.models.PlanType;
import com.amtenu.models.Subscription;
import com.amtenu.models.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upGradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);

    
}
