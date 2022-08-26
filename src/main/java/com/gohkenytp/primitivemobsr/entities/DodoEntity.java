
package com.gohkenytp.primitivemobsr.entities;

import com.gohkenytp.primitivemobsr.init.PMREntityTypes;
import com.gohkenytp.primitivemobsr.init.PMRItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber
public class DodoEntity extends Chicken {

    public int timeUntilNextShed;

    public DodoEntity(EntityType<DodoEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
        this.timeUntilNextShed = this.random.nextInt(6000) + 6000;
    }

    @Nullable
    @Override
    public ItemEntity spawnAtLocation(ItemLike p_19999_) {
        //override to dodo egg
        if (p_19999_.asItem() == Items.EGG) {
            p_19999_ = PMRItems.DODO_EGG.get();
        }

        return super.spawnAtLocation(p_19999_);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.of(Blocks.PUMPKIN), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    public float getVoicePitch() {
        return isBaby() ? ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.1F) : ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.6F);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.CHICKEN_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_28262_) {
        return SoundEvents.CHICKEN_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.CHICKEN_DEATH;
    }

    protected void playStepSound(BlockPos p_28254_, BlockState p_28255_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public DodoEntity getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
        DodoEntity retval = PMREntityTypes.DODO.get().create(serverWorld);
        retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
        return retval;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return List.of(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Blocks.PUMPKIN).contains(stack.getItem());
    }

    public static AttributeSupplier.Builder createAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
        builder = builder.add(Attributes.MAX_HEALTH, 10);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        return builder;
    }

    public static boolean checkDodoSpawnRules(EntityType<DodoEntity> p_28949_, LevelAccessor p_28950_, MobSpawnType p_28951_, BlockPos p_28952_, RandomSource p_28953_) {
        return p_28950_.getBlockState(p_28952_.below()).is(Blocks.MYCELIUM) && p_28950_.getRawBrightness(p_28952_, 0) > 8;
    }

    public float getWalkTargetValue(BlockPos p_28933_, LevelReader p_28934_) {
        return p_28934_.getBlockState(p_28933_.below()).is(Blocks.MYCELIUM) ? 10.0F : p_28934_.getPathfindingCostFromLightLevels(p_28933_) - 0.5F;
    }

    public void tick()
    {
        super.tick();

        if (!this.level.isClientSide && !this.isBaby() && !this.isChickenJockey() && --this.timeUntilNextShed <= 0)
        {
            this.playSound(SoundEvents.GRASS_BREAK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.setGrassToMycelium();
            this.timeUntilNextShed = this.random.nextInt(6000) + 6000;
        }
    }

    public void setGrassToMycelium()
    {
        BlockPos pos = new BlockPos(this.getX(), this.getBoundingBox().minY - 0.1D, this.getZ());
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        if(block instanceof GrassBlock && !level.isClientSide)
        {
            level.setBlock(pos, Blocks.MYCELIUM.defaultBlockState(), 3);
        }
    }
}