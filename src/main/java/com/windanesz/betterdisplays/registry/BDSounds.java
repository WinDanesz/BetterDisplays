package com.windanesz.betterdisplays.registry;

import com.windanesz.betterdisplays.BetterDisplays;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(BetterDisplays.MODID)
@Mod.EventBusSubscriber(modid = BetterDisplays.MODID)
public class BDSounds {
	private BDSounds() {}

//	public static final SoundEvent ENTITY_HEALING_HEATH_HEALS = createSound("entity.healing_heath_heals");


	public static SoundEvent createSound(String name) {
		return createSound(BetterDisplays.MODID, name);
	}

	/**
	 * Creates a sound with the given name, to be read from {@code assets/[modID]/sounds.json}.
	 */
	public static SoundEvent createSound(String modID, String name) {
		// All the setRegistryName methods delegate to this one, it doesn't matter which you use.
		return new SoundEvent(new ResourceLocation(modID, name)).setRegistryName(name);
	}

	@SubscribeEvent
	public static void register(RegistryEvent.Register<SoundEvent> event) {
//		event.getRegistry().register(ENTITY_HEALING_HEATH_HEALS);

	}
}