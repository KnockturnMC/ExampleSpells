package com.knockturnmc.example.spells.respiratio;

import com.knockturnmc.spellapi.animation.Animation;
import com.knockturnmc.spellapi.utils.MathUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

/**
 * Created by LynxPlay on 06.01.2017.
 */
public class RespiratioAnimation extends Animation<LivingEntity> {

    private final int ticksAlive;
    private Particle particle;
    private final double radius;
    private final double xRotation;
    private double phiAdd;
    private double phi;

    public RespiratioAnimation(Particle particle, double radius, double xRotation, double phiAdd, double phiStart, int ticksAlive) {
        this.ticksAlive = ticksAlive;
        this.particle = particle;
        this.radius = radius;
        this.xRotation = xRotation;
        this.phiAdd = phiAdd;
        this.phi = phiStart;
    }

    @Override
    public void play(LivingEntity target) {
        if(target == null) return;

        Location playerLoc = target.getLocation().clone().add(0, 1, 0);
        phi += phiAdd;

        double x = radius * Math.cos(phi);
        double z = radius * Math.sin(phi);

        Vector offset = new Vector(x, 0, z);
        MathUtils.rotateAboutZ(offset, xRotation);
        MathUtils.rotateAboutY(offset, -Math.toRadians(MathUtils.convertToRealDegrees(target.getLocation().getYaw())));

        playerLoc.add(offset);
        playerLoc.getWorld().spawnParticle(particle, playerLoc, 0);
        playerLoc.subtract(offset);
    }

    @Override
    public void configure() {
        setSpeed(1);
        setDuration(ticksAlive);
    }
}
