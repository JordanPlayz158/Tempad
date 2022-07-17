package me.codexadrian.tempad.fabric;

import me.codexadrian.tempad.Constants;
import me.codexadrian.tempad.Tempad;
import me.codexadrian.tempad.TempadType;
import me.codexadrian.tempad.entity.TimedoorEntity;
import me.codexadrian.tempad.items.TempadItem;
import me.codexadrian.tempad.network.NetworkHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class FabricTempad implements ModInitializer {
    public static final EntityType<TimedoorEntity> TIMEDOOR_ENTITY_ENTITY_TYPE = FabricEntityTypeBuilder.create(MobCategory.MISC, TimedoorEntity::new).dimensions(EntityDimensions.scalable(.4F, 2.3F)).disableSaving().build();
    public static final TempadItem TEMPAD = new TempadItem(TempadType.NORMAL, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.EPIC));
    public static final TempadItem CREATIVE_TEMPAD = new TempadItem(TempadType.HE_WHO_REMAINS, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).rarity(Rarity.EPIC));

    @Override
    public void onInitialize() {
        Tempad.init();
        Registry.register(Registry.ENTITY_TYPE, new ResourceLocation(Constants.MODID, "timedoor"), TIMEDOOR_ENTITY_ENTITY_TYPE);
        Registry.register(Registry.ITEM, new ResourceLocation(Constants.MODID, "tempad"), TEMPAD);
        Registry.register(Registry.ITEM, new ResourceLocation(Constants.MODID, "he_who_remains_tempad"), CREATIVE_TEMPAD);
        NetworkHandler.register();
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplierBuilder, setter) -> {
            if (id.equals(BuiltInLootTables.END_CITY_TREASURE)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.005F))
                        .add(LootItem.lootTableItem(CREATIVE_TEMPAD))
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)));
                supplierBuilder.withPool(poolBuilder.build());
            }
        });
    }
}
