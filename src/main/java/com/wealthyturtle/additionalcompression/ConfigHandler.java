package com.wealthyturtle.additionalcompression;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

	public static Configuration config;

	// Categories
	public static String compressedBlocks = "Compressed Blocks";
	public static String existingBlocks = "Existing Compressed Blocks";
	public static String misc = "Misceleaneous";
	public static String compatibility = "Mod Compatibility";

	// Options
	//public static Boolean extraUtils = true;
	public static Boolean exCompressum = true;
	public static Integer maxSifting = 1;
	public static Integer maxHammering = 1;
	public static String[] existingBlocksList;
	private static String[] existingBlocksListDefaults = {
			"coal:minecraft:coal_block:0:1",
			"ingotIron:minecraft:iron_block:0:1",
			"ingotGold:minecraft:gold_block:0:1",
			"gemLapis:minecraft:lapis_block:0:1",
			"dustRedstone:minecraft:redstone_block:0:1",
			"gemDiamond:minecraft:diamond_block:0:1",
			"gemEmerald:minecraft:emerald_block:0:1",
			"cropMelon:minecraft:melon_block:0:1",
			"cropWheat:minecraft:hay_block:0:1",
			//extra utils
			"cobblestone:ExtraUtilities:cobblestone_compressed:0:1",
			"cobblestone:ExtraUtilities:cobblestone_compressed:1:2",
			"cobblestone:ExtraUtilities:cobblestone_compressed:2:3",
			"cobblestone:ExtraUtilities:cobblestone_compressed:3:4",
			"cobblestone:ExtraUtilities:cobblestone_compressed:4:5",
			"cobblestone:ExtraUtilities:cobblestone_compressed:5:6",
			"cobblestone:ExtraUtilities:cobblestone_compressed:6:7",
			"cobblestone:ExtraUtilities:cobblestone_compressed:7:8",
			"dirt:ExtraUtilities:cobblestone_compressed:8:1",
			"dirt:ExtraUtilities:cobblestone_compressed:9:2",
			"dirt:ExtraUtilities:cobblestone_compressed:10:3",
			"dirt:ExtraUtilities:cobblestone_compressed:11:4",
			"gravel:ExtraUtilities:cobblestone_compressed:12:1",
			"gravel:ExtraUtilities:cobblestone_compressed:13:2",
			"blockSand:ExtraUtilities:cobblestone_compressed:14:1",
			"blockSand:ExtraUtilities:cobblestone_compressed:15:2",
			//ex compressum
			"dust:excompressum:compressed_dust:0:1",
			"cobblestone:excompressum:compressed_dust:1:1",
			"gravel:excompressum:compressed_dust:2:1",
			"blockSand:excompressum:compressed_dust:3:1",
			"dirt:excompressum:compressed_dust:4:1",
			"flint:excompressum:compressed_dust:5:1",
			"stone:excompressum:compressed_dust:6:1",
			"netherrack:excompressum:compressed_dust:7:1"
	};
	public static String[] compressedBlocksWhitelist;
	private static String[] compressedBlocksWhitelistDefaults = {
			//vanilla
			"cobblestone:minecraft:cobblestone:0:10",
			"stone:minecraft:stone:0:10",
			"gravel:minecraft:gravel:0:10",
			"blockSand:minecraft:sand:0:10",
			"dirt:minecraft:dirt:0:10",
			"clay:minecraft:clay:0:10",
			"cobblestoneMossy:minecraft:mossy_cobblestone:0:10",
			//"flint:minecraft:flint:0:10",
			"netherrack:minecraft:netherrack:0:10",
			"soulsand:minecraft:soul_sand:0:10",
			"endstone:minecraft:end_stone:0:10",
			"cropWheat:minecraft:wheat:0:3",
			"cropPotato:minecraft:potato:0:3",
			"cropCarrot:minecraft:carrot:0:3",
			"cropMelon:minecraft:melon:0:3",
			"cropApple:minecraft:apple:0:3",
			"cropSugarcane:minecraft:reeds:0:5",
			"cropNetherwart:minecraft:nether_wart:0:3",
			"meatPorkchop:minecraft:porkchop:0:3",
			"meatBeef:minecraft:beef:0:3",
			"meatChicken:minecraft:chicken:0:3",
			"meatFish:minecraft:fish:0:3",
			"meatRotten:minecraft:rotten_flesh:0:5",
			"bone:minecraft:bone:0:3",
			"spidereye:minecraft:spider_eye:0:3",
			//"string:minecraft:string:0:1",
			"feather:minecraft:feather:0:3",
			"leather:minecraft:leather:0:3",
			"rodBlaze:minecraft:blaze_rod:0:3",
			"pearlEnder:minecraft:ender_pearl:0:3",
			"dustGunpowder:minecraft:gunpowder:0:3",
			"dustSugar:minecraft:sugar:0:3",
			"egg:minecraft:egg:0:5",
			"logWood:minecraft:log:0:3",
			//"coal:minecraft:coal:0:3",
			//"charcoal:minecraft:coal:1:3",
			"ingotIron:minecraft:iron_ingot:0:3",
			"ingotGold:minecraft:gold_ingot:0:3",
			"gemLapis:minecraft:dye:4:3",
			"dustRedstone:minecraft:redstone:0:3",
			"gemDiamond:minecraft:diamond:0:3",
			"gemEmerald:minecraft:emerald:0:3",
			"itemNetherStar:minecraft:nether_star:0:1",
			"cookie:minecraft:cookie:0:1",
			"bread:minecraft:bread:0:1",
			"bottleWater:minecraft:potion:0:1",
			"bucketLava:minecraft:lava_bucket:0:1",
			//ex nihilo
			"dust:exnihilo:dust:0:10",
			"gravelNether:exnihilo:exnihilo.gravel_nether:0:10",
			"gravelEnd:exnihilo:exnihilo.gravel_ender:0:10"
	};

	public static void init(File file) {
		config = new Configuration(file);
		syncConfig();
	}

	public static void syncConfig() {
		config.setCategoryComment(compressedBlocks, "This is where all the compressed blocks are registered, you can freely add your own blocks, remove the ones you dislike or modify existing ones.");

		compressedBlocksWhitelist = config.getStringList("compressedBlockWhitelist", compressedBlocks, compressedBlocksWhitelistDefaults,
				"The syntax is: OredictName:ModID:ItemID:ItemMetadata:MaxCompressionLevel"
						+ "\nOredictname: The ore dictionary name of the item/block (if it exists) you want to compress, also becomes the name of the compressed block."
						+ "\nModID: The mod id of the item/block you want to compress."
						+ "\nItemID: The id of the item/block you want to compress."
						+ "\nItemMetadata: The metadata value of the item/block you want to compress, most of the time this is 0."
						+ "\nMaxCompressionLevel: The maximum level you want the item/block to be compressed to, setting this below 1 will probably mess things up.");

		config.setCategoryComment(existingBlocks, "This is where you can tell the mod what compressed blocks have already been registered by other mods to prevent duplicates and conflicting recipes.");

		existingBlocksList = config.getStringList("existingBlockList", existingBlocks, existingBlocksListDefaults,
				"The syntax is: CompressedBlockName:ModID:ItemID:ItemMetadata:CompressionLevel"
						+ "\nCompressedBlockName: The material of the compressed block."
						+ "\nModID: The mod id of the compressed block."
						+ "\nItemID: The id of the compressed block."
						+ "\nItemMetadata: The metadata value of the compressed block."
						+ "\nCompressionLevel: The level of compression of the compressed block.");

		config.setCategoryComment(misc, "Some options that don't really fit into any other category.");

		maxSifting = config.getInt("maxSifting", misc, maxSifting, 0, 10, "Up to what level the compressed blocks can be sifted."
				+ "\nIf you set this too high, the mod will take very long to load."
				+ "\nSetting this above 3 is probably a bad idea.");
		maxHammering = config.getInt("maxHammering", misc, maxHammering, 0, 10, "Up to what level the compressed blocks can be hammered."
				+ "\nIf you set this too high, the mod will take very long to load."
				+ "\nSetting this above 3 is probably a bad idea.");

		config.setCategoryComment(compatibility, "This is where you can disable mod compatibility with specific mods, compatibility will never be loaded if the required mod isn't installed.");

		// extraUtils = config.getBoolean("extraUtils", compatibility, extraUtils, "Extra Utilities compatibility");
		exCompressum = config.getBoolean("exCompressum", compatibility, exCompressum, "Ex Compressum compatibility");

		if(config.hasChanged())
			config.save();
	}
}
