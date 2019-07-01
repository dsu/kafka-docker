package com.infosys.producer.repository;

import com.infosys.producer.model.Subscription;
import com.infosys.producer.model.SubscriptionInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SubstrictionRepository extends JpaRepository<Subscription, Long>{

    Set<Subscription> findByStatus(SubscriptionInterface.SubscriptionStatus notSend);
}
