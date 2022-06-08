package me.crashcringle.cringlebosses.other;

import java.util.ArrayList;
import java.util.List;

import me.crashcringle.cringlebosses.CringleBoss;
import org.bukkit.inventory.ItemStack;

public class SummoningAltarRecipe {

    private final ItemStack catalyst;
    private final List<ItemStack> input;
    private final CringleBoss mob;

    public SummoningAltarRecipe(List<ItemStack> input, ItemStack mobItem) {
        this.catalyst = input.get(4);
        this.input = new ArrayList<>();

        this.input.add(input.get(0));
        this.input.add(input.get(1));
        this.input.add(input.get(2));
        this.input.add(input.get(5));

        this.input.add(input.get(8));
        this.input.add(input.get(7));
        this.input.add(input.get(6));
        this.input.add(input.get(3));
        this.mob = (CringleBoss) CringleBoss.getByItem(mobItem);
    }

    public ItemStack getCatalyst() {
        return this.catalyst;
    }

    public CringleBoss getMob() {
        return this.mob;
    }

    public List<ItemStack> getInput() {
        return this.input;
    }

}