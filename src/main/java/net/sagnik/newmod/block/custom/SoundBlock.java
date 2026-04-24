package net.sagnik.newmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class SoundBlock extends Block {

    public static final BooleanProperty GLOWING = BooleanProperty.create("glowing");

    public SoundBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(GLOWING, false));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {

            pLevel.setBlock(pPos, pState.setValue(GLOWING, true), 3);


            pLevel.playSound(null, pPos,
                    SoundEvents.NOTE_BLOCK_DIDGERIDOO.get(),
                    SoundSource.BLOCKS, 1f, 1f);


            pLevel.scheduleTick(pPos, this, 20);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        // Switch back to normal after the scheduled tick fires
        pLevel.setBlock(pPos, pState.setValue(GLOWING, false), 3);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GLOWING);
    }
}