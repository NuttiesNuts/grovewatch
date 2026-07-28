package uk.sigma_co.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

public class MossySwordItem extends Item{
    public MossySwordItem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public Component getName(ItemStack itemStack) {
        return Component.literal("<grad colors=67a538,bfd64f>" + super.getName(itemStack).getString());
    }
}
