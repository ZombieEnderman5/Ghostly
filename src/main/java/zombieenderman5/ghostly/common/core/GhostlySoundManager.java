package zombieenderman5.ghostly.common.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zombieenderman5.ghostly.GhostlyReference;

public class GhostlySoundManager {

	public static final List<SoundEvent> SOUNDS = new ArrayList<>();

	public static final SoundEvent SHADE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.ambient"));
	public static final SoundEvent SHADE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.hurt"));
	public static final SoundEvent SHADE_HURT_ALTERNATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.hurt_alternate"));
	public static final SoundEvent SHADE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.death"));
	public static final SoundEvent SHADE_DEATH_ALTERNATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.death_alternate"));
	public static final SoundEvent SHADE_DISSIPATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.dissipate"));
	public static final SoundEvent SHADE_INFEST_ENDERMAN = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.infest_enderman"));
	public static final SoundEvent SHADE_DESTROY_ENTITY = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.destroy_entity"));
	public static final SoundEvent SHADE_POSSESS_ENTITY = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.shade.possess_entity"));
	public static final SoundEvent POSSESSED_SWORD_SWOOP = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_sword.swoop"));
	public static final SoundEvent POSSESSED_ZOMBIE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_zombie.ambient"));
	public static final SoundEvent POSSESSED_ZOMBIE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_zombie.hurt"));
	public static final SoundEvent POSSESSED_ZOMBIE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_zombie.death"));
	public static final SoundEvent POSSESSED_SKELETON_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_skeleton.ambient"));
	public static final SoundEvent POSSESSED_SKELETON_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_skeleton.hurt"));
	public static final SoundEvent POSSESSED_SKELETON_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_skeleton.death"));
	public static final SoundEvent POSSESSED_HUSK_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_husk.ambient"));
	public static final SoundEvent POSSESSED_HUSK_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_husk.hurt"));
	public static final SoundEvent POSSESSED_HUSK_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_husk.death"));
	public static final SoundEvent POSSESSED_WITHER_SKELETON_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_wither_skeleton.ambient"));
	public static final SoundEvent POSSESSED_WITHER_SKELETON_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_wither_skeleton.hurt"));
	public static final SoundEvent POSSESSED_WITHER_SKELETON_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_wither_skeleton.death"));
	public static final SoundEvent POSSESSED_STRAY_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_stray.ambient"));
	public static final SoundEvent POSSESSED_STRAY_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_stray.hurt"));
	public static final SoundEvent POSSESSED_STRAY_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_stray.death"));
	public static final SoundEvent INFESTED_ENDERMEN_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.infested_endermen.ambient"));
	public static final SoundEvent INFESTED_ENDERMEN_SCREAM = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.infested_endermen.scream"));
	public static final SoundEvent INFESTED_ENDERMEN_INFEST_ENDERMAN = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.infested_endermen.infest_enderman"));
	public static final SoundEvent POSSESSED_BOXER_ZOMBIE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_zombie.ambient"));
	public static final SoundEvent POSSESSED_BOXER_ZOMBIE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_zombie.hurt"));
	public static final SoundEvent POSSESSED_BOXER_ZOMBIE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_zombie.death"));
	public static final SoundEvent POSSESSED_BOXER_SKELETON_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_skeleton.ambient"));
	public static final SoundEvent POSSESSED_BOXER_SKELETON_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_skeleton.hurt"));
	public static final SoundEvent POSSESSED_BOXER_SKELETON_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_skeleton.death"));
	public static final SoundEvent POSSESSED_BOXER_HUSK_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_husk.ambient"));
	public static final SoundEvent POSSESSED_BOXER_HUSK_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_husk.hurt"));
	public static final SoundEvent POSSESSED_BOXER_HUSK_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_husk.death"));
	public static final SoundEvent POSSESSED_BOXER_WITHER_SKELETON_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_wither_skeleton.ambient"));
	public static final SoundEvent POSSESSED_BOXER_WITHER_SKELETON_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_wither_skeleton.hurt"));
	public static final SoundEvent POSSESSED_BOXER_WITHER_SKELETON_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_wither_skeleton.death"));
	public static final SoundEvent POSSESSED_BOXER_STRAY_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_stray.ambient"));
	public static final SoundEvent POSSESSED_BOXER_STRAY_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_stray.hurt"));
	public static final SoundEvent POSSESSED_BOXER_STRAY_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_boxer_stray.death"));
	public static final SoundEvent POSSESSED_HUNCHBONE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_hunchbone.ambient"));
	public static final SoundEvent POSSESSED_HUNCHBONE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_hunchbone.hurt"));
	public static final SoundEvent POSSESSED_HUNCHBONE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_hunchbone.death"));
	public static final SoundEvent POSSESSED_WITHER_HUNCHBONE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_wither_hunchbone.ambient"));
	public static final SoundEvent POSSESSED_WITHER_HUNCHBONE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_wither_hunchbone.hurt"));
	public static final SoundEvent POSSESSED_WITHER_HUNCHBONE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_wither_hunchbone.death"));
	public static final SoundEvent POSSESSED_WITHERED_ZOMBIE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_withered_zombie.ambient"));
	public static final SoundEvent POSSESSED_WITHERED_ZOMBIE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_withered_zombie.hurt"));
	public static final SoundEvent POSSESSED_WITHERED_ZOMBIE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.possessed_withered_zombie.death"));
	public static final SoundEvent DARKNESS_MAGE_AMBIENT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.ambient"));
	public static final SoundEvent DARKNESS_MAGE_HURT = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.hurt"));
	public static final SoundEvent DARKNESS_MAGE_HURT_ALTERNATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.hurt_alternate"));
	public static final SoundEvent DARKNESS_MAGE_DEATH = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.death"));
	public static final SoundEvent DARKNESS_MAGE_DISSIPATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.dissipate"));
	public static final SoundEvent DARKNESS_MAGE_CAST_SPELL = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.cast_spell"));
	public static final SoundEvent DARKNESS_MAGE_CAST_SPELL_ALTERNATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.cast_spell_alternate"));
	public static final SoundEvent DARKNESS_MAGE_PREPATE_BLINDNESS = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.prepare_blindness"));
	public static final SoundEvent DARKNESS_MAGE_PREPARE_BLINDNESS_ALTERNATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.prepare_blindness_alternate"));
	public static final SoundEvent DARKNESS_MAGE_PREPARE_WITHER = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.prepare_wither"));
	public static final SoundEvent DARKNESS_MAGE_PREPARE_WITHER_ALTERNATE = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.prepare_wither_alternate"));
	public static final SoundEvent DARKNESS_MAGE_PREPARE_ORB = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.prepare_orb"));
	public static final SoundEvent DARKNESS_MAGE_PREPARE_SUMMON = register(new ResourceLocation(GhostlyReference.MOD_ID, "entity.darkness_mage.prepare_summon"));
	public static final SoundEvent CORPOREALITY_TOOL_HIT = register(new ResourceLocation(GhostlyReference.MOD_ID, "item.tool_of_corporeality.hit"));

	public static SoundEvent register(ResourceLocation name) {
        
        SoundEvent soundevent = new SoundEvent(name);
        soundevent.setRegistryName(name);
        SOUNDS.add(soundevent);
        
        return soundevent;
        
    }
    
    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        
        SoundEvent[] array = new SoundEvent[0];
        array = SOUNDS.toArray(array);
        
        event.getRegistry().registerAll(array);
        
    }

}
