package com.gohkenytp.primitivemobsr.entities;

import com.gohkenytp.primitivemobsr.init.PMREntityTypes;
import com.gohkenytp.primitivemobsr.init.PMRItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class DodoEggEntity extends ThrowableItemProjectile {
    public DodoEggEntity(EntityType<? extends DodoEggEntity> p_37391_, Level p_37392_) {
        super(p_37391_, p_37392_);
    }

    public DodoEggEntity(Level p_37399_, LivingEntity p_37400_) {
        super(PMREntityTypes.DODO_EGG.get(), p_37400_, p_37399_);
    }

    public DodoEggEntity(Level p_37394_, double p_37395_, double p_37396_, double p_37397_) {
        super(PMREntityTypes.DODO_EGG.get(), p_37395_, p_37396_, p_37397_, p_37394_);
    }

    protected Item getDefaultItem() {
        return PMRItems.DODO_EGG.get();
    }

    private ParticleOptions getParticle() {
        return new ItemParticleOption(ParticleTypes.ITEM, PMRItems.DODO_EGG.get().getDefaultInstance());
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            double d0 = 0.08D;

            for (int i = 0; i < 8; ++i) {
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), ((double) this.random.nextFloat() - 0.5D) * 0.08D, ((double) this.random.nextFloat() - 0.5D) * 0.08D, ((double) this.random.nextFloat() - 0.5D) * 0.08D);
            }
        }

    }


    protected void onHit(HitResult p_37488_) {
        super.onHit(p_37488_);
        if (!this.level.isClientSide) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }

                for (int j = 0; j < i; ++j) {
                    DodoEntity chicken = PMREntityTypes.DODO.get().create(this.level);
                    chicken.setAge(-24000);
                    chicken.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), 0.0F);
                    this.level.addFreshEntity(chicken);
                }
            } else if (this.random.nextInt(3) == 0) {
                this.playSound(SoundEvents.TURTLE_EGG_CRACK, 1.0F, 1.0F);
                ItemEntity chicken = new ItemEntity(this.level, this.getX(), this.getY(), this.getZ(), new ItemStack(Items.FEATHER));
                this.level.addFreshEntity(chicken);
            }

            this.level.broadcastEntityEvent(this, (byte) 3);
            this.discard();
        }

    }

}