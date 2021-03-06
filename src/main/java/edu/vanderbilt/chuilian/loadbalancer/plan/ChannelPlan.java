package edu.vanderbilt.chuilian.loadbalancer.plan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Killian on 6/5/17.
 */

public class ChannelPlan {
    private boolean isNew = true;
    private String topic;
    private Set<String> availableBroker;
    private Strategy strategy;

    private static final Logger logger = LogManager.getLogger(ChannelPlan.class.getName());

    public ChannelPlan() {
        this.topic = null;
        this.strategy = Strategy.HASH;
        this.availableBroker = null;
    }

    public ChannelPlan(String topic, Set<String> availableBroker, Strategy strategy) {
        this.topic = topic;
        this.availableBroker = availableBroker;
        this.strategy = strategy;
    }

    public ChannelPlan(ChannelPlan that) {
        this.topic = that.topic;
        this.availableBroker = that.availableBroker;
        this.strategy = that.strategy;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void addAvailableBroker(String brokerID) {
        if (availableBroker == null) availableBroker = new HashSet<String>();
        availableBroker.add(brokerID);
    }

    public void removeAvailableBroker(String brokerID) {
        this.availableBroker.remove(brokerID);
    }

    public void setAvailableBroker(Set<String> availableBroker) {
        this.availableBroker = availableBroker;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public String getTopic() {
        return this.topic;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public Set<String> getAvailableBroker() {
        return this.availableBroker;
    }

    public int getNumBrokers() {
        return availableBroker.size();
    }


}