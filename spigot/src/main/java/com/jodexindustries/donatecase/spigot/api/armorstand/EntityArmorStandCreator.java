package com.jodexindustries.donatecase.spigot.api.armorstand;

import com.jodexindustries.donatecase.api.armorstand.ArmorStandCreator;
import com.jodexindustries.donatecase.api.armorstand.ArmorStandEulerAngle;
import com.jodexindustries.donatecase.api.armorstand.EquipmentSlot;
import com.jodexindustries.donatecase.api.data.storage.CaseLocation;
import com.jodexindustries.donatecase.api.tools.DCTools;
import com.jodexindustries.donatecase.spigot.tools.BukkitUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class EntityArmorStandCreator implements ArmorStandCreator {

    private final UUID animationId;
    private final ArmorStand entity;

    public EntityArmorStandCreator(UUID animationId, Location location) {
        this.animationId = animationId;
        World world = location.getWorld();
        if (world == null) {
            entity = null;
            return;
        }
        entity = world.spawn(location, ArmorStand.class);
        entity.setMetadata("case",
                new FixedMetadataValue(BukkitUtils.getDonateCase(), "case")
        );

        ArmorStandCreator.armorStands.put(entity.getEntityId(), this);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        EntityArmorStandCreator that = (EntityArmorStandCreator) object;
        return Objects.equals(entity, that.entity);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(entity);
    }

    @Override
    public void setVisible(boolean isVisible) {
        entity.setVisible(isVisible);
    }

    @Override
    public void setSmall(boolean small) {
        entity.setSmall(small);
    }

    @Override
    public void setMarker(boolean marker) {
        entity.setMarker(marker);
    }

    @Override
    public void setGlowing(boolean glowing) {
        entity.setGlowing(glowing);
    }

    @Override
    public boolean isGlowing() {
        return entity.isGlowing();
    }

    @Override
    public void setCollidable(boolean collidable) {
        entity.setCollidable(collidable);
    }

    @Override
    public void setCustomNameVisible(boolean flag) {
        entity.setCustomNameVisible(flag);
    }

    @Override
    public boolean isCustomNameVisible() {
        return entity.isCustomNameVisible();
    }

    @Override
    public void setCustomName(String displayName) {
        entity.setCustomName(DCTools.rc(displayName));
    }

    @Override
    public void teleport(CaseLocation location) {
        entity.teleport(BukkitUtils.toBukkit(location));
    }

    @Override
    public void setEquipment(EquipmentSlot equipmentSlot, Object item) {
        EntityEquipment equipment = entity.getEquipment();
        if (equipment != null) equipment.setItem(org.bukkit.inventory.EquipmentSlot.valueOf(equipmentSlot.name()), (ItemStack) item);
    }

    @Override
    public void setAngle(@NotNull ArmorStandEulerAngle angle) {
        entity.setHeadPose(
                BukkitUtils.toBukkit(angle.getHead())
        );
        entity.setBodyPose(
                BukkitUtils.toBukkit(angle.getBody())
        );
        entity.setLeftArmPose(
                BukkitUtils.toBukkit(angle.getLeftArm())
        );
        entity.setRightArmPose(
                BukkitUtils.toBukkit(angle.getRightArm())
        );
        entity.setLeftLegPose(
                BukkitUtils.toBukkit(angle.getLeftLeg())
        );
        entity.setRightLegPose(
                BukkitUtils.toBukkit(angle.getRightLeg())
        );
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        entity.setRotation(yaw, pitch);
    }

    @Override
    public CaseLocation getLocation() {
        return BukkitUtils.fromBukkit(entity.getLocation());
    }

    @Override
    public @NotNull UUID getUniqueId() {
        return entity.getUniqueId();
    }

    @Override
    public UUID getAnimationId() {
        return animationId;
    }

    @Override
    public int getEntityId() {
        return entity.getEntityId();
    }

    @Override
    public void setGravity(boolean hasGravity) {
        entity.setGravity(hasGravity);
    }

    @Override
    public void remove() {
        ArmorStandCreator.armorStands.remove(entity.getEntityId());
        entity.remove();
    }
}
