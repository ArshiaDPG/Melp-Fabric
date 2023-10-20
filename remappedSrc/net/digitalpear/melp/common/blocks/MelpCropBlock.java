package net.digitalpear.melp.common.blocks;

import net.digitalpear.melp.init.MBlocks;
import net.digitalpear.melp.init.MItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MelpCropBlock extends CropBlock {

    public static final int MAX_AGE = 2;
    public static final IntProperty AGE = Properties.AGE_2;
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 6.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0),
            Block.createCuboidShape(5.0, 0.0, 5.0, 11.0, 10.0, 11.0)
    };
    private static final int BONEMEAL_INCREASE = 1;
    public MelpCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected int getGrowthAmount(World world) {
        return BONEMEAL_INCREASE;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }


    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }



    @Override
    public BlockState withAge(int age) {
        if (age < 3){
            return super.withAge(age);
        }
        else {
            return MBlocks.MELP.getDefaultState();
        }
    }


    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return MItems.MELP_SEED.getDefaultStack();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

}
