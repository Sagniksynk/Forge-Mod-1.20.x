package net.sagnik.newmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sagnik.newmod.NewMod;
import net.sagnik.newmod.block.ModBlocks;

public class ModCreativeModTabs
{
    public  static  final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NewMod.MOD_ID);

    public  static  final RegistryObject<CreativeModeTab> NEWMOD_TAB = CREATIVE_MODE_TABS.register("newmod_tab", () -> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.SAPPHIRE.get()))
            .title(Component.translatable("creativetab.newmod_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.SAPPHIRE.get());
                pOutput.accept(ModItems.DIAMOND.get());
                pOutput.accept(ModItems.MAGICWAND.get());
                pOutput.accept(ModBlocks.SAPPHIRE_BLOCK.get());
                pOutput.accept(ModBlocks.DIAMOND_BLOCK.get());
                pOutput.accept(ModBlocks.SAPPHIRE_ORE.get());
                pOutput.accept(ModItems.RAW_SAPPHIRE.get());
                pOutput.accept(ModItems.METAL_DETECTOR.get());
                pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                pOutput.accept(ModItems.SAPPHIRE_BERRY.get());
                pOutput.accept(ModItems.CORRUPTED_SOUL_STEW.get());
                pOutput.accept(ModItems.MYSTERIOUS_FLESH.get());
                pOutput.accept(ModItems.SOUL_COOKBOOK.get());
                pOutput.accept(ModItems.CORRUPTED_HELMET.get());
                pOutput.accept(ModItems.CORRUPTED_CHESTPLATE.get());
                pOutput.accept(ModItems.CORRUPTED_LEGGINGS.get());
                pOutput.accept(ModItems.CORRUPTED_BOOTS.get());
                pOutput.accept(ModItems.PISTOL.get());
                pOutput.accept(ModItems.RIFLE.get());
                pOutput.accept(ModItems.SHOTGUN.get());
            })
            .build());

    public  static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
