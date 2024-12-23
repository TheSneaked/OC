package oc.item;


import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;

import java.util.function.Supplier;

public enum ModToolMaterial implements ToolMaterial {
    TRENCH_BLADE(0,1000,0,5,20,() -> Ingredient.ofItems(ModItems.TRENCH_BLADE)),
    ABYSSAL_MOOR(5,1000,5,6,20,() -> Ingredient.ofItems(ModItems.ABYSSAL_MOOR)),
    LEVI_SWORD(0,1000,0,5,20,() -> Ingredient.ofItems(ModItems.LEVI_SWORD)),
    WIND_BAG(0,100,0,0,0,() -> Ingredient.ofItems(ModItems.WIND_BAG)),
    SCHLORP_GLORP(0,1000,0,5,20,() -> Ingredient.ofItems(ModItems.SCHLORP_GLORP)),

    LEVI_AXE(5,1000,5,7,20,() -> Ingredient.ofItems(ModItems.LEVI_AXE));




    private final int miningLevel;
    private final int ItemDurability;
    private final int miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterial(int miningLevel, int itemDurability, int miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        ItemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }


    @Override
    public int getDurability() {
        return this.ItemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public TagKey<Block> getInverseTag() {
        return null;
    }


    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}



