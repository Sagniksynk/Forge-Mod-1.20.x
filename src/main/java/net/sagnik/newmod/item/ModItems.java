package net.sagnik.newmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sagnik.newmod.NewMod;
import net.sagnik.newmod.item.custom.MetalDetectorItem;
import net.sagnik.newmod.item.custom.CorruptedSoulStewItem;
import net.sagnik.newmod.item.custom.SoulCookbookItem;
import net.sagnik.newmod.item.custom.armor.CorruptedArmorItem;
import net.sagnik.newmod.item.custom.armor.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.sagnik.newmod.item.custom.guns.*;

public class ModItems
{
    public  static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NewMod.MOD_ID);

    public  static  final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",() -> new Item(new Item.Properties()));
    public  static  final RegistryObject<Item> DIAMOND = ITEMS.register("diamond",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGICWAND = ITEMS.register("magicwand",
            () -> new MagicWandItem(new Item.Properties()));
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> RAW_SAPPHIRE = ITEMS.register("raw_sapphire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SAPPHIRE_BERRY = ITEMS.register("sapphire_berry",
            () -> new Item(new Item.Properties().food(ModFoods.SAPPHIRE_BERRY)));
    public static final RegistryObject<Item> CORRUPTED_SOUL_STEW = ITEMS.register("corrupted_soul_stew",
            () -> new CorruptedSoulStewItem());
    public static final RegistryObject<Item> MYSTERIOUS_FLESH = ITEMS.register("mysterious_flesh",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(2)
                            .saturationMod(0.1f)
                            .effect(() -> new MobEffectInstance(MobEffects.POISON, 100, 0), 0.8f)
                            .build()
                    )
            ));
    public static final RegistryObject<Item> SOUL_COOKBOOK = ITEMS.register("soul_cookbook",
            () -> new SoulCookbookItem());

    public static final RegistryObject<Item> CORRUPTED_HELMET = ITEMS.register("corrupted_helmet",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.HELMET,
                    new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_CHESTPLATE = ITEMS.register("corrupted_chestplate",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_LEGGINGS = ITEMS.register("corrupted_leggings",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.LEGGINGS,
                    new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_BOOTS = ITEMS.register("corrupted_boots",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.BOOTS,
                    new Item.Properties()));
    public static final RegistryObject<Item> PISTOL = ITEMS.register("pistol",
            () -> new PistolItem());
    public static final RegistryObject<Item> RIFLE = ITEMS.register("rifle",
            () -> new RifleItem());
    public static final RegistryObject<Item> SHOTGUN = ITEMS.register("shotgun",
            () -> new ShotgunItem());

    public  static  void  register(IEventBus eventbus)
    {
        ITEMS.register(eventbus);
    }
}
