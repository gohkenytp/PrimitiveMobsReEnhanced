package com.gohkenytp.primitivemobsr.entities;

import com.gohkenytp.primitivemobsr.init.PMRItems;
import com.gohkenytp.primitivemobsr.init.PMRSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Position;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GroveSpriteEntity extends PathfinderMob
{
	private float LeavesR = 0f;
	private float LeavesG = 0f;
	private float LeavesB = 0f;
	private float LogR = 0f;
	private float LogG = 0f;
	private float LogB = 0f;
	private float LogTopR = 0f;
	private float LogTopG = 0f;
	private float LogTopB = 0f;
	private boolean changedColor = false;

	private static final EntityDataAccessor<Boolean> IS_CINDER = SynchedEntityData.defineId(GroveSpriteEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> SAPLING_AMOUNT = SynchedEntityData.defineId(GroveSpriteEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> LEAVES_POS = SynchedEntityData.defineId(GroveSpriteEntity.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Boolean> IS_BEGGING = SynchedEntityData.defineId(GroveSpriteEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> SAPLING_TIMER = SynchedEntityData.defineId(GroveSpriteEntity.class, EntityDataSerializers.INT);

	public GroveSpriteEntity(EntityType<GroveSpriteEntity> type, Level world) {
        super(type, world);
        xpReward = 0;
        setNoAi(false);
		this.handDropChances[0] = 1f;
		this.handDropChances[1] = 1f;
	}

    @Override
	protected void registerGoals()
    {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		 this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, Ingredient.of(Items.GREEN_DYE), false));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, Ingredient.of(Items.OAK_SAPLING), false));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(6, new AvoidEntityGoal<>(this, Llama.class, 24.0F, 1.5D, 1.5D));
		this.targetSelector.addGoal(7, new HurtByTargetGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor p_32146_, @NotNull DifficultyInstance p_32147_, @NotNull MobSpawnType p_32148_, @Nullable SpawnGroupData p_32149_, @Nullable CompoundTag p_32150_)
	{
    	if(!getCommandSenderWorld().isClientSide)
    	{

			ItemStack sapling = new ItemStack(this.getBlockStateOn().getBlock().asItem());
			if(this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && !sapling.isEmpty())
			{
				this.setItemSlot(EquipmentSlot.MAINHAND, sapling);
			}

			this.setItemSlot(EquipmentSlot.OFFHAND, sapling);

			this.setSaplingAmount(1 + random.nextInt(4));
			this.setSaplingTimer(random.nextInt(1000) + 1000);
    	}
		p_32149_ = super.finalizeSpawn(p_32146_, p_32147_, p_32148_, p_32149_, p_32150_);
		return p_32149_;
	}

    public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.FOLLOW_RANGE,40);
		builder = builder.add(Attributes.MOVEMENT_SPEED,0.22);
        builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE,3);
        return builder;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        if(this.isCinderSprite())
        {
            super.dropCustomDeathLoot(source, looting, recentlyHitIn);
            this.spawnAtLocation(new ItemStack(Items.COAL));
        }
    }

    public static boolean checkGroveSpriteSpawnRules(EntityType<GroveSpriteEntity> p_28949_, LevelAccessor p_28950_, MobSpawnType p_28951_, BlockPos p_28952_, RandomSource p_28953_) {
        return p_28950_.getBlockState(p_28952_.below()).is(Blocks.GRASS) && p_28950_.getRawBrightness(p_28952_, 0) > 8;
    }

    public float getWalkTargetValue(BlockPos p_28933_, LevelReader p_28934_) {
        return p_28934_.getBlockState(p_28933_.below()).is(Blocks.GRASS) ? 10.0F : p_28934_.getPathfindingCostFromLightLevels(p_28933_) - 0.5F;
    }

    protected void defineSynchedData()
    {
        this.getEntityData().define(IS_CINDER, Boolean.FALSE);
        this.getEntityData().define(SAPLING_AMOUNT, 0);
        this.getEntityData().define(LEAVES_POS, new BlockPos(0,0,0));
        this.getEntityData().define(IS_BEGGING, Boolean.FALSE);
        this.getEntityData().define(SAPLING_TIMER, 0);
        super.defineSynchedData();
    }

   	public void tick()
   	{
   		if(this.getSharedFlag(0))
   		{
   			this.setCinderSprite(true);
   			changedColor = false;
   		}

   		if(this.isCinderSprite())
   		{
   			level.addParticle(ParticleTypes.FLAME, this.getX(), this.getY() + this.getBbHeight() + 0.2F, this.getZ(), 0, 0, 0);
   		}
   		else
   		{
   			this.setSaplingTimer(this.getSaplingTimer() - 1);

   			if(this.getSaplingTimer() <= 0)
   			{
   				this.setSaplingAmount(this.getSaplingAmount() + 1);
   	            for (int i = 0; i < 8; i++)
   	            {
					level.addParticle(ParticleTypes.HAPPY_VILLAGER, getX() + (random.nextFloat() - random.nextFloat()), getY() + random.nextFloat() + 1D, getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
   	            }
   	            this.setSaplingTimer(random.nextInt(1000) + 1000);
   			}
   		}

   		if(getCommandSenderWorld().isClientSide && !changedColor)
   		{
   			changedColor = true;
   			if(!this.isCinderSprite())
   			{
                setLeavesRGB(new int[]{86, 188, 83});
                setLogRGB(new int[]{188, 171, 132});
                setLogTopRGB(new int[]{102, 98, 94});
               }
   			else
   			{
   				setLeavesRGB(new int[]{177, 100, 0});
   				setLogRGB(new int[]{90, 86, 80});
   				setLogTopRGB(new int[]{102, 98, 94});
				this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
   				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Blocks.TORCH));
   			}
   		}

   		super.tick();
   	}

    public boolean hurt(DamageSource source, float amount)
    {
    	if((source == DamageSource.ON_FIRE || source == DamageSource.LAVA || source == DamageSource.IN_WALL)  && this.isCinderSprite())
    	{
    		return false;
    	}
        return super.hurt(source, amount);
    }

    public boolean doHurtTarget(Entity entityIn)
    {
        float f = (float)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue();
        int i = 0;

        if (entityIn instanceof Entity)
        {
            f += EnchantmentHelper.getDamageBonus(this.getMainHandItem(), getMobType());
            i += EnchantmentHelper.getKnockbackBonus(this);
        }

        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f);

        if (flag)
        {
            if (i > 0 && entityIn instanceof Entity)
            {
                ((LivingEntity)entityIn).knockback((float)i * 0.5F, (double) Mth.sin(this.yRotO * 0.017453292F), (double)(-Mth.cos(this.yRotO * 0.017453292F)));
                this.xo *= 0.6D;
                this.zo *= 0.6D;
            }

            if (this.isCinderSprite())
            {
                entityIn.setRemainingFireTicks(8);
            }

            if (entityIn instanceof Player)
            {
                Player entityplayer = (Player)entityIn;
                ItemStack itemstack = this.getMainHandItem();
                ItemStack itemstack1 = entityplayer.isUsingItem() ? entityplayer.getUseItem() : ItemStack.EMPTY;

                if (!itemstack.isEmpty() && !itemstack1.isEmpty() && itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this) && itemstack1.getItem().isDamageable(itemstack1))
                {
                    float f1 = 0.25F + (float)EnchantmentHelper.getBlockEfficiency(this) * 0.05F;

                    if (this.random.nextFloat() < f1)
                    {
                        entityplayer.getCooldowns().addCooldown(itemstack1.getItem(), 100);
                        this.level.broadcastEntityEvent(entityplayer, (byte)30);
                    }
                }
            }

            this.doEnchantDamageEffects(this, entityIn);
        }

        return true;
    }

    public Object checkAndHandleImportantInteractions(Player player, InteractionHand hand)
    {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean flag = itemstack.getItem() == this.getMainHandItem().getItem() && itemstack.getTag() == this.getMainHandItem().getTag();

        if(this.isCinderSprite()) {return false;}

        if (flag)
        {
        	this.consumeItemFromStack(player, itemstack);
        	this.setSaplingAmount(this.getSaplingAmount() + 1);
            for (int i = 0; i < 8; i++)
            {
                level.addParticle(ParticleTypes.HAPPY_VILLAGER, getX() + (random.nextFloat() - random.nextFloat()), getY() + random.nextFloat() + 1D, getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
            }
            this.playSound(PMRSoundEvents.GROVESPRITE_THANKS.get(), 1, 1);
            return true;
        }
        else if(itemstack.getItem() == Items.WHEAT_SEEDS)
        {
        	this.consumeItemFromStack(player, itemstack);
            for (int i = 0; i < 8; i++)
            {
                level.addParticle(ParticleTypes.HEART, getX() + (random.nextFloat() - random.nextFloat()), getY() + random.nextFloat() + 1D, getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
            }
            this.playSound(PMRSoundEvents.GROVESPRITE_THANKS.get(), 1, 1);
        	this.setHealth(this.getMaxHealth());
        	return true;
        }
        else if(itemstack.getItem() == Items.GREEN_DYE && this.getSaplingAmount() > 0)
        {
        	if (Blocks.OAK_LOG != null)
            {
        		this.consumeItemFromStack(player, itemstack);
                for (int i = 0; i < 8; i++)
                {
                    level.addParticle(ParticleTypes.CRIT, getX() + (random.nextFloat() - random.nextFloat()), getY() + random.nextFloat() + 1D, getZ() + (random.nextFloat() - random.nextFloat()), 0, 0, 0);
                }
                this.setSaplingAmount(this.getSaplingAmount() - 1);
                this.playSound(PMRSoundEvents.GROVESPRITE_THANKS.get(), 1, 1);
                if(!this.level.isClientSide)
                {
                	ItemStack sap = this.getOffhandItem().copy();
                	sap.setCount(random.nextInt(4) + 1);
            		ItemEntity item = this.spawnAtLocation(sap , 1);
            		item.setDefaultPickUpDelay();
                }
            }
        	return true;
        }
        else
        {
            return super.interact(player, hand);
        }
    }

    /**
     * Decreases ItemStack size by one
     */
    protected void consumeItemFromStack(Player player, ItemStack stack)
    {
        if (!player.getAbilities().instabuild)
        {
            stack.shrink(1);
        }
    }

    public float getVoicePitch()
    {
        return this.isCinderSprite() ? (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.5F : (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
    }

    public void setCinderSprite(boolean cinder)
    {
        this.getEntityData().set(IS_CINDER, cinder);
    }

    public boolean isCinderSprite()
    {
        return this.getEntityData().get(IS_CINDER);
    }

    public void setSaplingAmount(int saplings)
    {
        this.getEntityData().set(SAPLING_AMOUNT, saplings);
    }

    public int getSaplingAmount()
    {
        return this.getEntityData().get(SAPLING_AMOUNT);
    }

    public void setSaplingTimer(int timer)
    {
        this.getEntityData().set(SAPLING_TIMER, timer);
    }

    public int getSaplingTimer()
    {
        return this.getEntityData().get(SAPLING_TIMER);
    }

    protected SoundEvent getAmbientSound()
    {
        return PMRSoundEvents.GROVESPRITE_IDLE.get();
    }

    protected float getSoundVolume()
    {
        return 0.4F;
    }

    public float[] getLeavesRGB() {
        return new float[] { this.LeavesR, this.LeavesG, this.LeavesB };
    }

    public void setLeavesRGB(int[] RGB) {
        this.LeavesR = RGB[0];
        this.LeavesG = RGB[1];
        this.LeavesB = RGB[2];
    }

    public float[] getLogRGB() {
        return new float[] { this.LogR, this.LogG, this.LogB };
    }

    public void setLogRGB(int[] RGB) {
        this.LogR = RGB[0];
        this.LogG = RGB[1];
        this.LogB = RGB[2];
    }

    public float[] getLogTopRGB() {
        return new float[] { this.LogTopR, this.LogTopG, this.LogTopB };
    }

    public void setLogTopRGB(int[] RGB) {
        this.LogTopR = RGB[0];
        this.LogTopG = RGB[1];
        this.LogTopB = RGB[2];
    }
}
