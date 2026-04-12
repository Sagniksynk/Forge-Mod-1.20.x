package net.sagnik.newmod.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MagicWandItem extends Item {

    private static final int COOLDOWN_TICKS = 40;
    private static final double DASH_STRENGTH = 2.5;

    public MagicWandItem(Properties properties) {
        super(properties);
    }
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity,
                              int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (isSelected && !level.isClientSide() && entity instanceof Player) {
            Player player = (Player) entity;
            ServerLevel serverLevel = (ServerLevel) level;

            serverLevel.sendParticles(
                    ParticleTypes.ENCHANT,
                    player.getX(),
                    player.getY() + 1.0,
                    player.getZ(),
                    3,
                    0.3, 0.3, 0.3,
                    0.05
            );
        }
    }
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide()) { // Run only on server side
            // Get the direction the player is looking (horizontal only)
            Vec3 lookAngle = player.getLookAngle();
            Vec3 dashVector = new Vec3(
                    lookAngle.x * DASH_STRENGTH,
                    0.5, // small upward boost
                    lookAngle.z * DASH_STRENGTH
            );

            // Apply the velocity
            player.setDeltaMovement(dashVector);
            player.hurtMarked = true; // Force client to update movement

            // Apply cooldown
            player.getCooldowns().addCooldown(this, COOLDOWN_TICKS);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}