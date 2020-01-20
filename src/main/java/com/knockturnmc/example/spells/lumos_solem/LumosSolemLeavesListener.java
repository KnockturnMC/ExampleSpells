package com.knockturnmc.example.spells.lumos_solem;

import com.knockturnmc.spellapi.Spellbook;
import com.knockturnmc.spellapi.statuseffect.container.WorldStatusEffectContainer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class LumosSolemLeavesListener implements Listener {

    public LumosSolemLeavesListener() {
        Spellbook.getInstance().registerListener(this);
    }

    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent event) {
        Spellbook.getInstance().getStatusEffectManager().getWorldContainer().getEffects(LumosSolemEffect.class).stream()
                .map(l -> l.getBase().getLocation().distanceSquared(event.getBlock().getLocation()))
                .filter(d -> d < LumosSolem.LEAVE_DECAY_PREVENT_RADIUS_SQUARED)
                .findAny()
                .ifPresent($ -> {
                    event.setCancelled(true);
                });
    }

}
