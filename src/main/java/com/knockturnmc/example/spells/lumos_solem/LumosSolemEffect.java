package com.knockturnmc.example.spells.lumos_solem;

import com.knockturnmc.spellapi.statuseffect.StatusEffect;
import com.knockturnmc.spellapi.statuseffect.annotation.SingleInstanceEffect;
import com.knockturnmc.spellapi.statuseffect.factory.StatusEffectFactory;
import com.knockturnmc.spellapi.statuseffect.types.block.BlockStatusEffectBase;
import com.knockturnmc.spellapi.utils.bukkit.BlockFinder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;

import java.util.HashSet;
import java.util.UUID;

@SingleInstanceEffect
public class LumosSolemEffect extends StatusEffect<BlockStatusEffectBase> {

    private static final BlockFinder BLOCK_FINDER = new BlockFinder(l -> Tag.LEAVES.isTagged( l.getBlock().getType()), true);

    private final double radius;
    private final HashSet<Location> blocks = new HashSet<>();

    public LumosSolemEffect(StatusEffectFactory factory, int ticks, UUID caster, Location location, double radius) {
        super(factory.blockStatusEffectBase(ticks, caster, location.getBlock()));
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
        BLOCK_FINDER.find(getBase().getLocation(), radius).stream()
                .map(Block::getLocation)
                .filter(l -> !getBase().isTemporaryBlock(l))
                .forEach(loc -> {
                    blocks.add(loc);
                    getBase().addTemporaryBlock(loc);

                    BlockData leaveData = loc.getBlock().getBlockData();

                    loc.getBlock().setType(Material.AIR);
                    loc.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 20, leaveData);
                });
    }

    /**
     * Abstract method called when the effect ends
     */
    @Override
    public void end() {
        blocks.forEach(getBase()::removeTemporaryBlock);
        blocks.clear();
    }
}
