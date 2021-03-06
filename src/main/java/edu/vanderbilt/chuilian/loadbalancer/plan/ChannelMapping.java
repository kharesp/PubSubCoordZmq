package edu.vanderbilt.chuilian.loadbalancer.plan;

import edu.vanderbilt.chuilian.loadbalancer.consistenthashing.ConsistentHashingMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Killian on 6/7/17.
 */
public class ChannelMapping {
    HashMap<String, ChannelPlan> map = new HashMap<>();

    public ChannelPlan getChannelPlan(String topic) {
        return map.get(topic);
    }

    public void registerNewChannel(String topic, ConsistentHashingMap consistentHashingMap) {
        map.put(topic, new ChannelPlan(topic, ChannelPlanGenerator.consistentHashing(consistentHashingMap, topic), Strategy.HASH));
    }

    public void replaceChannelPlans(ArrayList<ChannelPlan> channelPlans) {
        for (int i = 0; i < channelPlans.size(); i++) {
            ChannelPlan plan = channelPlans.get(i);
            // replace original plan
            map.put(plan.getTopic(), plan);
        }
    }

    public void migrateChannels(ArrayList<? extends SystemPlan> systemPlans) {
        for (int i = 0; i < systemPlans.size(); i++) {
            SystemPlan plan = systemPlans.get(i);
            // migrate channel
            map.get(plan.getChannel()).removeAvailableBroker(plan.getFrom());
            map.get(plan.getChannel()).addAvailableBroker(plan.getTo());
        }
    }

    public int size() {
        return map.size();
    }

    public Set<Map.Entry<String, ChannelPlan>> entrySet() {
        return map.entrySet();
    }

    public void addNewChannelPlan(ChannelPlan channelPlan) {
        map.put(channelPlan.getTopic(), channelPlan);
    }


}
