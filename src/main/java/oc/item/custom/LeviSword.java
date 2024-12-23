package oc.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class LeviSword extends SwordItem {
    public LeviSword(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (
                world instanceof ServerWorld serverWorld
        )
            if (!((PlayerEntity)user).getItemCooldownManager().isCoolingDown(this)) {
            serverWorld.setWeather(0, 12000, true, true);
                ((PlayerEntity) user).getItemCooldownManager().set(this,36000);


            }


      return   super.use(world, user, hand);



    }
}
//kill me