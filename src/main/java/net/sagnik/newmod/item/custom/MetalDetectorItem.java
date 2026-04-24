package net.sagnik.newmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (!context.getLevel().isClientSide()) {
            Level level = context.getLevel();
            Player player = context.getPlayer();
            BlockPos clickedPos = context.getClickedPos();

            // Search in a 10 block radius downward
            for (int i = 0; i <= 64; i++) {
                BlockPos searchPos = clickedPos.below(i);
                BlockState state = level.getBlockState(searchPos);

                if (isValuableBlock(state)) {
                    player.sendSystemMessage(Component.literal(
                            "⚡ Found " + state.getBlock().getName().getString()
                                    + " at Y=" + searchPos.getY()
                    ));
                    return InteractionResult.SUCCESS;
                }
            }

            player.sendSystemMessage(Component.literal("No ores found nearby."));
        }

        context.getItemInHand().hurtAndBreak(1, context.getPlayer(),
                p -> p.broadcastBreakEvent(context.getHand()));

        return InteractionResult.SUCCESS;
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(BlockTags.COAL_ORES)
                || state.is(BlockTags.IRON_ORES)
                || state.is(BlockTags.GOLD_ORES)
                || state.is(BlockTags.DIAMOND_ORES)
                || state.is(BlockTags.EMERALD_ORES)
                || state.is(BlockTags.LAPIS_ORES)
                || state.is(BlockTags.REDSTONE_ORES)
                || state.is(BlockTags.COPPER_ORES)
                || state.is(Tags.Blocks.ORES);
    }
}