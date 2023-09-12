package net.digitalpear.melp.common.blocks;

import net.digitalpear.melp.init.MBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class MelpNeckBlock extends AbstractPlantBlock {

    public static final BooleanProperty FLOWERING = BooleanProperty.of("flowering");
    public static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);

    public MelpNeckBlock(Settings settings) {
        super(settings, Direction.UP.UP, SHAPE, true);
        this.setDefaultState(this.getDefaultState().with(FLOWERING, false));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.getItem() instanceof ShearsItem && state.get(FLOWERING)){
            world.setBlockState(pos, state.with(FLOWERING, false));
            player.swingHand(hand);
            stack.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));

            ItemEntity itemEntity = EntityType.ITEM.create(world);
            itemEntity.setPos(pos.getX() + 0.5f, pos.getY() + 0.5f, pos.getZ() + 0.5f);
            itemEntity.setStack(Items.PINK_TULIP.getDefaultStack());
            world.playSound(player, pos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 0.5F);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(player, state.with(FLOWERING, false)));
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((world.random.nextFloat() - world.random.nextFloat()) * 0.1F, world.random.nextFloat() * 0.05F, (world.random.nextFloat() - world.random.nextFloat()) * 0.1F));
            }
            world.spawnEntity(itemEntity);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos().offset(this.growthDirection)).with(FLOWERING, ctx.getWorld().random.nextBoolean());
        return !blockState.isOf(this.getStem()) && !blockState.isOf(this.getPlant()) ? this.getRandomGrowthState(ctx.getWorld()) : this.getPlant().getDefaultState();
    }
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        if (!state.get(FLOWERING)){
            world.setBlockState(pos, state.with(FLOWERING, true));
        }
        else{
            super.grow(world, random, pos, state);
        }
    }
    protected boolean canAttachTo(BlockState state) {
        return ((MelpBlock) this.getStem()).canAttachTo(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLOWERING);
        super.appendProperties(builder);
    }
    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) MBlocks.MELP;
    }
}
