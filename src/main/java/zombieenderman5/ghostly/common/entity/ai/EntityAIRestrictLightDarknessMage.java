package zombieenderman5.ghostly.common.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zombieenderman5.ghostly.GhostlyConfig;

public class EntityAIRestrictLightDarknessMage extends EntityAIBase {

	private final EntityCreature entity;
	private final World world;

    public EntityAIRestrictLightDarknessMage(EntityCreature creature)
    {
        this.entity = creature;
        this.world = creature.world;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    @Override
    public boolean shouldExecute()
    {
        return GhostlyConfig.MOBS.darknessMageDissipationLightLevel != -0.1D && this.world.getLightBrightness(new BlockPos(entity)) > ((float)GhostlyConfig.MOBS.darknessMageDissipationLightLevel * 0.6);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting()
    {
        ((PathNavigateGround)this.entity.getNavigator()).setAvoidSun(true);
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    @Override
    public void resetTask()
    {
        ((PathNavigateGround)this.entity.getNavigator()).setAvoidSun(false);
    }

}
