package com.knockturnmc.example.spells.immobulus;

import com.knockturnmc.spellapi.spell.info.targets.UnlinkedTargetfinder;
import com.knockturnmc.spellapi.statuseffect.annotation.IdentifiableEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.types.LocationStatusEffect;
import com.knockturnmc.spellapi.utils.bukkit.ParticleUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import java.util.UUID;

@SingleInstanceEffect(casterUnique = true)
@IdentifiableEffect(Immobulus.class)
public class ImmobulusEffect extends LocationStatusEffect {

    private static final Vector ZERO = new Vector(0, 0, 0);
    private final UnlinkedTargetfinder targetFinder;

    public ImmobulusEffect(UUID caster, int turns, Location location, UnlinkedTargetfinder targetFinder) {
        super(caster, turns, location);
        this.targetFinder = targetFinder;
    }

    /**
     * Abstract methode called per ticks
     */
    @Override
    public void effect() {
        targetFinder.getEntitiesHit(LivingEntity.class).forEach(l -> {
            l.setVelocity(ZERO);
        });

        ParticleUtil.flair(Particle.CRIT, targetFinder.getTargetedLocation(), targetFinder.getRadius(), 30);
    }

    /**
     * Abstract method called when the effect starts
     */
    @Override
    public void start() {

    }

    /**
     * Abstract method called when the effect ends
     */
    @Override
    public void end() {

    }
}
