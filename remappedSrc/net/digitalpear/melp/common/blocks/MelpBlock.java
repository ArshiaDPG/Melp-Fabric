package net.digitalpear.melp.common.blocks;

import net.digitalpear.melp.init.MBlocks;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class MelpBlock extends AbstractPlantStemBlock {

    public static final BooleanProperty FLOWERING = MelpNeckBlock.FLOWERING;

    public static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 14.0, 13.0);
    private static final double GROW_PER_TICK_PROBABILITY = 0.14;
    public MelpBlock(Settings settings) {
        super(settings, Direction.UP, SHAPE, true, GROW_PER_TICK_PROBABILITY);
        setDefaultState(this.getDefaultState().with(FLOWERING, false));
    }

    @Override
    protected int getGrowthLength(Random random) {
        return 1;
    }


    @Override
    public boolean canAttachTo(BlockState state) {
        return state.isIn(BlockTags.DIRT) || state.isOf(Blocks.FARMLAND) || state.isOf(getPlant());
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return state.isAir();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLOWERING);
        super.appendProperties(builder);
    }

    @Override
    protected Block getPlant() {
        return MBlocks.MELP_NECK;
    }
}
