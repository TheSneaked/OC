package oc.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LeviAxe extends SwordItem  {


    public LeviAxe(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        if (
                world instanceof ServerWorld serverWorld
        )
            if (!((PlayerEntity)user).getItemCooldownManager().isCoolingDown(this)) {
                serverWorld.setWeather(12000, 0, false, false);
                ((PlayerEntity) user).getItemCooldownManager().set(this,36000);


            }


        return   super.use(world, user, hand);



    }


}
