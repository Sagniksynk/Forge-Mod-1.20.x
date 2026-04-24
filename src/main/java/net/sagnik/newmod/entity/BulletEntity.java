package net.sagnik.newmod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sagnik.newmod.NewMod;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BulletEntity extends Projectile implements ItemSupplier {

    private float damage = 5.0f;

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NewMod.MOD_ID);

    public static final RegistryObject<EntityType<BulletEntity>> BULLET =
            ENTITY_TYPES.register("bullet", () ->
                    EntityType.Builder.<BulletEntity>of(BulletEntity::new,
                                    net.minecraft.world.entity.MobCategory.MISC)
                            .sized(0.1f, 0.1f)
                            .clientTrackingRange(64)
                            .build("bullet"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    public BulletEntity(EntityType<? extends BulletEntity> type, Level level) {
        super(type, level);
    }

    public BulletEntity(Level level, LivingEntity shooter, float damage) {
        super(BULLET.get(), level);
        this.damage = damage;
        this.setOwner(shooter);
        // Start position at eye level
        this.setPos(
                shooter.getX(),
                shooter.getEyeY() - 0.1,
                shooter.getZ()
        );
    }
    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.IRON_NUGGET);
    }
    @Override
    protected void defineSynchedData() {
        // No custom synched data needed
    }

    @Override
    public void tick() {
        super.tick();

        // Apply gravity
        Vec3 velocity = this.getDeltaMovement();
        this.setDeltaMovement(velocity.x, velocity.y - 0.03, velocity.z);

        // Move the bullet
        Vec3 pos = this.position();
        Vec3 nextPos = pos.add(this.getDeltaMovement());

        // Check for hits
        HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(
                this, this::canHitEntity);

        if (hitResult.getType() != HitResult.Type.MISS) {
            this.onHit(hitResult);
        }

        this.updateRotation();
        this.setPos(nextPos.x, nextPos.y, nextPos.z);

        // Remove after 5 seconds
        if (this.tickCount > 100) {
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!this.level().isClientSide() && result.getEntity() instanceof LivingEntity target) {
            target.hurt(this.damageSources().thrown(this, this.getOwner()), damage);
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.discard();
    }

    public void setDamage(float damage) { this.damage = damage; }
}