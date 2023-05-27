package me.paradis.factory.tools;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

import java.util.Arrays;
import java.util.Map;

public class ItemBuilder {
    ItemStack item;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
    }

    public ItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
    }

    public ItemBuilder name(String name) {
        ItemMeta meta = item.getItemMeta();
        name = ChatColor.translateAlternateColorCodes('&', name);
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder hidePotionEffects(){
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setAttributes(boolean displayAttributes) {
        ItemMeta meta = item.getItemMeta();
        if (displayAttributes) {
            meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        } else {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder sA(boolean displayAttributes) {
        ItemMeta meta = item.getItemMeta();
        if (displayAttributes) {
            meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        } else {
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setEnchantmentsVisible(boolean displayEnchantments) {
        ItemMeta meta = item.getItemMeta();
        if (displayEnchantments) {
            meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
        } else {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setPotionEffectsVisible(boolean displayPotionEffects) {
        ItemMeta meta = item.getItemMeta();
        if (displayPotionEffects) {
            meta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        } else {
            meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        }
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        ItemMeta meta = item.getItemMeta();
        // convert each value in lore to ChatColor.translate(lore)
        lore = Arrays.stream(lore).map(s -> ChatColor.translateAlternateColorCodes('&', s)).toArray(String[]::new);
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder sL(String... lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level) {
        if (enchantment == null)
            return this;
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder aE(Enchantment enchantment, int level) {
        if (enchantment == null)
            return this;
        item.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
        }
        return this;
    }

    // potion builder
    public ItemBuilder setPotionData(PotionData potionData) {
        if (item.getItemMeta() instanceof PotionMeta) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            potionMeta.setBasePotionData(potionData);
            item.setItemMeta(potionMeta);
        }
        return this;
    }

    // splash potion builder
    public ItemBuilder setSplashPotion(PotionType potionType, boolean extended, boolean upgraded) {
        if (item.getType() == Material.SPLASH_POTION) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            PotionData potionData = new PotionData(potionType, extended, upgraded);
            potionMeta.setBasePotionData(potionData);
            item.setItemMeta(potionMeta);
        }
        return this;
    }

    // lingering potion builder
    public ItemBuilder setLingeringPotion(PotionType potionType, boolean extended, boolean upgraded) {
        if (item.getType() == Material.LINGERING_POTION) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            PotionData potionData = new PotionData(potionType, extended, upgraded);
            potionMeta.setBasePotionData(potionData);
            item.setItemMeta(potionMeta);
        }
        return this;
    }

    // arrow builder
    public ItemBuilder setTippedArrow(PotionType potionType, boolean extended, boolean upgraded) {
        if (item.getType() == Material.TIPPED_ARROW) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            PotionData potionData = new PotionData(potionType, extended, upgraded);
            potionMeta.setBasePotionData(potionData);
            item.setItemMeta(potionMeta);
        }
        return this;
    }

    public ItemBuilder sE(Map<Enchantment, Integer> enchantments) {
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        item.setDurability((short) durability);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setArrowType(PotionType potionType) {
        if (potionType == null)
            return this;
        PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
        PotionData potionData = new PotionData(potionType);
        potionMeta.setBasePotionData(potionData);
        item.setItemMeta(potionMeta);
        return this;
    }

    public ItemStack build() {
        return item;
    }
}