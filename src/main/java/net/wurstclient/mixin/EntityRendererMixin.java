/*
 * Copyright (c) 2014-2025 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.wurstclient.WurstClient;
import net.wurstclient.hacks.HealthTagsHack;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity, S extends EntityRenderState>
{
	/**
	 * Modifies the display name in the render state to include health
	 * information when HealthTags is enabled. This is called every frame, so
	 * the health values are always up-to-date and automatically revert when
	 * HealthTags is disabled.
	 */
	@Inject(at = @At("TAIL"),
		method = "updateRenderState(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/render/entity/state/EntityRenderState;F)V")
	private void addHealthToDisplayName(T entity, S state, float tickProgress,
		CallbackInfo ci)
	{
		if(state.displayName == null)
			return;
		if(!(entity instanceof LivingEntity le))
			return;
		
		HealthTagsHack healthTags =
			WurstClient.INSTANCE.getHax().healthTagsHack;
		if(!healthTags.isEnabled())
			return;
		
		state.displayName = healthTags.addHealth(le, state.displayName.copy());
	}
}
