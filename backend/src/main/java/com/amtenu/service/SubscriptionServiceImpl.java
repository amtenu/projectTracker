package com.amtenu.service;

import com.amtenu.models.PlanType;
import com.amtenu.models.Subscription;
import com.amtenu.models.User;
import com.amtenu.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private UserService userService;


    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @Override
    public Subscription createSubscription(User user) {

    //let's create free subscription plan
     Subscription subscription=new Subscription();

     subscription.setUser(user);
     subscription.setSubscriptionStartDate(LocalDate.now());
     subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
     subscription.setValid(true);
     subscription.setPlanType(PlanType.FREE);

     return subscriptionRepository.save(subscription);



    }

    @Override
    public Subscription getUserSubscription(Long userId) throws Exception {
        return null;
    }

    @Override
    public Subscription upGradeSubscription(Long userId, PlanType planType) {
        return null;
    }

    @Override
    public boolean isValid(Subscription subscription) {
        return false;
    }
}
