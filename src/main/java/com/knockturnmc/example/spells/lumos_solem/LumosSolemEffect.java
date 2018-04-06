package com.knockturnmc.example.spells.lumos_solem;

import com.knockturnmc.spellapi.statuseffect.annotation.IdentifiableEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.types.BlockStatusEffect;
import com.knockturnmc.spellapi.utils.bukkit.BlockFinder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.material.MaterialData;

import java.util.HashSet;
import java.util.UUID;

@IdentifiableEffect(LumosSolem.class)
@SingleInstanceEffect
public class LumosSolemEffect extends BlockStatusEffect {

    private static final BlockFinder BLOCK_FINDER = new BlockFinder(l -> {
        Material type = l.getBlock().getType();
        return type == Material.LEAVES || type == Material.LEAVES_2;
    }, true);

    private final double radius;
    private final HashSet<Location> blocks = new HashSet<>();

    public LumosSolemEffect(UUID caster, int turns, Location location, double radius) {
        super(caster, turns, location);
        this.radius = radius;
    }

    /**
     * Abstract method called per ticks
     */
    @Override
    public void effect() {

    }

    /**
     * Abstract method called when the effect starts
     */
    @Override
    public void start() {
        BLOCK_FINDER.find(getLocation(), radius).stream()
                .map(Block::getLocation)
                .filter(l -> !isTemporaryBlock(l))
                .forEach(loc -> {
                    blocks.add(loc);
                    addTemporaryBlock(loc);

                    MaterialData leaveData = loc.getBlock().getState().getData();

                    loc.getBlock().setType(Material.AIR);
                    loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 20, leaveData);
                });
    }

    /**
     * Abstract method called when the effect ends
     */
    @Override
    public void end() {
        blocks.forEach(this::removeTemporaryBlock);
        blocks.clear();
    }
}
