package net.sagnik.newmod.item.custom.armor;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.sagnik.newmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

public enum ModArmorMaterials implements ArmorMaterial {

    CORRUPTED("corrupted",
            new int[]{3, 6, 8, 3},   // defense per piece: boots, leggings, chest, helmet
            15,                        // enchantability (gold=25, diamond=10)
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            2.0f,                      // toughness (diamond=2, netherite=3)
            0.1f,                      // knockback resistance
            () -> Ingredient.of(ModItems.SAPPHIRE.get()));  // repair item

    private final String name;
    private final int[] defense;
    private final int enchantability;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final java.util.function.Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int[] defense, int enchantability,
                      SoundEvent equipSound, float toughness,
                      float knockbackResistance,
                      java.util.function.Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.defense = defense;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override public int getDurabilityForType(ArmorItem.Type type) {
        return new int[]{13, 15, 16, 11}[type.ordinal()] * 37;
    }
    @Override public int getDefenseForType(ArmorItem.Type type) { return defense[type.ordinal()]; }
    @Override public int getEnchantmentValue() { return enchantability; }
    @Override public @NotNull SoundEvent getEquipSound() { return equipSound; }
    @Override public @NotNull Ingredient getRepairIngredient() { return repairIngredient.get(); }
    @Override public @NotNull String getName() { return "newmod:corrupted"; }
    @Override public float getToughness() { return toughness; }
    @Override public float getKnockbackResistance() { return knockbackResistance; }

}