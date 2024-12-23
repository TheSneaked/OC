package oc.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.BundleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import oc.component.ModDataComponentTypes;

public class WindBag extends BundleItem {
    public WindBag(Settings settings) {
        super(settings);
    }
    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.4F, 0.4F, 1.0F);

    @Override

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!((PlayerEntity)user).getItemCooldownManager().isCoolingDown(this)) {
            ItemStack stack = user.getStackInHand(hand);

            for (int i = 0; i < 10; i++) {
                spawnWindCharge(world, user);
            }
            user.addVelocity(user.getRotationVector().multiply(-0.5));
            user.playSound(SoundEvents.ENTITY_BREEZE_SHOOT, 1f, 1f);
            if (!world.isClient && getWindCount(stack) - 1 <= 0) {

                ((ServerWorld) world).setWeather(0, 12000, true, true);
            }
            if (!user.isCreative()) {
                setWindCount(stack, getWindCount(stack) - 1);
                if (getWindCount(stack) <= 0) {
                    stack.decrement(1);

                    ItemStack bundleStack = Items.BUNDLE.getDefaultStack();
                    user.setStackInHand(hand, bundleStack);


                    return super.use(world, user, hand);
                }
                ((PlayerEntity) user).getItemCooldownManager().set(this, 50);
            }
        }

        return super.use(world, user, hand);
    }

    public void spawnWindCharge(World world, PlayerEntity user) {
        WindChargeEntity windCharge = new WindChargeEntity(EntityType.WIND_CHARGE, world);
        windCharge.setPos(user.getEyePos().x,user.getEyePos().y,user.getEyePos().z);

        double randomX = (world.random.nextDouble() - 0.5)*20;
        double randomY = (world.random.nextDouble() - 0.5)*20;
        Vec3d velocity = user.getRotationVector()
                .multiply(4)
                .rotateX((float) Math.toRadians(randomX))
                .rotateY((float) Math.toRadians(randomY));

        windCharge.setVelocity(velocity);
        world.spawnEntity(windCharge);
    }

    public int getWindCount(ItemStack stack) { return stack.get(ModDataComponentTypes.WIND_COUNT);}
    public void setWindCount(ItemStack stack, int value) { stack.set(ModDataComponentTypes.WIND_COUNT, Math.clamp(value, 0,13));}

    public boolean isItemBarVisible(ItemStack stack) {
    return getWindCount(stack) > 0;
    }

    public int getItemBarStep(ItemStack stack) {

        return getWindCount(stack);

    }

    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0x57a09d ));
    }
}
