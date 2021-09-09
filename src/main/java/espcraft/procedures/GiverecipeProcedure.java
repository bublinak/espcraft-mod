package espcraft.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.function.Function;
import java.util.Map;
import java.util.Iterator;
import java.util.Comparator;

import espcraft.EspcraftMod;

public class GiverecipeProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				EspcraftMod.LOGGER.warn("Failed to load dependency x for procedure Giverecipe!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				EspcraftMod.LOGGER.warn("Failed to load dependency y for procedure Giverecipe!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				EspcraftMod.LOGGER.warn("Failed to load dependency z for procedure Giverecipe!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				EspcraftMod.LOGGER.warn("Failed to load dependency world for procedure Giverecipe!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((Entity) world
				.getEntitiesWithinAABB(PlayerEntity.class,
						new AxisAlignedBB((Math.floor(x)) - (5 / 2d), (Math.floor(y)) - (5 / 2d), (Math.floor(z)) - (5 / 2d),
								(Math.floor(x)) + (5 / 2d), (Math.floor(y)) + (5 / 2d), (Math.floor(z)) + (5 / 2d)),
						null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((Math.floor(x)), (Math.floor(y)), (Math.floor(z)))).findFirst().orElse(null)) instanceof ServerPlayerEntity) {
			((ServerPlayerEntity) ((Entity) world
					.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB((Math.floor(x)) - (5 / 2d), (Math.floor(y)) - (5 / 2d), (Math.floor(z)) - (5 / 2d),
									(Math.floor(x)) + (5 / 2d), (Math.floor(y)) + (5 / 2d), (Math.floor(z)) + (5 / 2d)),
							null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((Math.floor(x)), (Math.floor(y)), (Math.floor(z)))).findFirst().orElse(null)))
							.unlockRecipes(new ResourceLocation[]{new ResourceLocation("minecraft:espblock")});
		}
		if (((Entity) world
				.getEntitiesWithinAABB(PlayerEntity.class,
						new AxisAlignedBB((Math.floor(x)) - (5 / 2d), (Math.floor(y)) - (5 / 2d), (Math.floor(z)) - (5 / 2d),
								(Math.floor(x)) + (5 / 2d), (Math.floor(y)) + (5 / 2d), (Math.floor(z)) + (5 / 2d)),
						null)
				.stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
					}
				}.compareDistOf((Math.floor(x)), (Math.floor(y)), (Math.floor(z)))).findFirst().orElse(null)) instanceof ServerPlayerEntity) {
			Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) ((Entity) world
					.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB((Math.floor(x)) - (5 / 2d), (Math.floor(y)) - (5 / 2d), (Math.floor(z)) - (5 / 2d),
									(Math.floor(x)) + (5 / 2d), (Math.floor(y)) + (5 / 2d), (Math.floor(z)) + (5 / 2d)),
							null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((Math.floor(x)), (Math.floor(y)), (Math.floor(z)))).findFirst().orElse(null))).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("espcraft:espcraftphase"));
			AdvancementProgress _ap = ((ServerPlayerEntity) ((Entity) world
					.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB((Math.floor(x)) - (5 / 2d), (Math.floor(y)) - (5 / 2d), (Math.floor(z)) - (5 / 2d),
									(Math.floor(x)) + (5 / 2d), (Math.floor(y)) + (5 / 2d), (Math.floor(z)) + (5 / 2d)),
							null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf((Math.floor(x)), (Math.floor(y)), (Math.floor(z)))).findFirst().orElse(null))).getAdvancements()
							.getProgress(_adv);
			if (!_ap.isDone()) {
				Iterator _iterator = _ap.getRemaningCriteria().iterator();
				while (_iterator.hasNext()) {
					String _criterion = (String) _iterator.next();
					((ServerPlayerEntity) ((Entity) world
							.getEntitiesWithinAABB(PlayerEntity.class,
									new AxisAlignedBB((Math.floor(x)) - (5 / 2d), (Math.floor(y)) - (5 / 2d), (Math.floor(z)) - (5 / 2d),
											(Math.floor(x)) + (5 / 2d), (Math.floor(y)) + (5 / 2d), (Math.floor(z)) + (5 / 2d)),
									null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf((Math.floor(x)), (Math.floor(y)), (Math.floor(z)))).findFirst().orElse(null))).getAdvancements()
									.grantCriterion(_adv, _criterion);
				}
			}
		}
	}
}
