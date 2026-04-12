package net.sagnik.newmod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sagnik.newmod.NewMod;

public class ModItems
{
    public  static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NewMod.MOD_ID);

    public  static  final RegistryObject<Item> SAPPHIRE = ITEMS.register("sapphire",() -> new Item(new Item.Properties()));
    public  static  final RegistryObject<Item> DIAMOND = ITEMS.register("diamond",() -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MAGICWAND = ITEMS.register("magicwand",
            () -> new MagicWandItem(new Item.Properties()));

    public  static  void  register(IEventBus eventbus)
    {
        ITEMS.register(eventbus);
    }
}
