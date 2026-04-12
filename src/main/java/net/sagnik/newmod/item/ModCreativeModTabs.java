package net.sagnik.newmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sagnik.newmod.NewMod;

public class ModCreativeModTabs
{
    public  static  final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NewMod.MOD_ID);

    public  static  final RegistryObject<CreativeModeTab> NEWMOD_TAB = CREATIVE_MODE_TABS.register("newmod_tab", () -> CreativeModeTab.builder().icon(()->new ItemStack(ModItems.SAPPHIRE.get()))
            .title(Component.translatable("creativetab.newmod_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.SAPPHIRE.get());
                pOutput.accept(ModItems.DIAMOND.get());
                pOutput.accept(ModItems.MAGICWAND.get());
            })
            .build());

    public  static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
