/*
 * Copyright (c) 2014-2025 Wurst-Imperium and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package net.wurstclient.hack;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.wurstclient.WurstClient;
import net.wurstclient.event.EventManager;
import net.wurstclient.events.UpdateListener;
import net.wurstclient.hacks.*;
import net.wurstclient.util.json.JsonException;

public final class HackList implements UpdateListener
{
	// Blocks
	public final AutoBuildHack autoBuildHack = new AutoBuildHack();
	public final AutoSignHack autoSignHack = new AutoSignHack();
	public final AutoToolHack autoToolHack = new AutoToolHack();
	public final BuildRandomHack buildRandomHack = new BuildRandomHack();
	public final ExcavatorHack excavatorHack = new ExcavatorHack();
	public final InstantBunkerHack instantBunkerHack = new InstantBunkerHack();
	public final ScaffoldWalkHack scaffoldWalkHack = new ScaffoldWalkHack();
	public final TemplateToolHack templateToolHack = new TemplateToolHack();
	
	// Fun
	public final DerpHack derpHack = new DerpHack();
	public final HeadRollHack headRollHack = new HeadRollHack();
	public final LsdHack lsdHack = new LsdHack();
	public final MileyCyrusHack mileyCyrusHack = new MileyCyrusHack();
	public final RainbowUiHack rainbowUiHack = new RainbowUiHack();
	public final SkinDerpHack skinDerpHack = new SkinDerpHack();
	public final TiredHack tiredHack = new TiredHack();
	
	// Movement
	public final AutoSprintHack autoSprintHack = new AutoSprintHack();
	public final AutoSwimHack autoSwimHack = new AutoSwimHack();
	public final AutoWalkHack autoWalkHack = new AutoWalkHack();
	public final BunnyHopHack bunnyHopHack = new BunnyHopHack();
	public final DolphinHack dolphinHack = new DolphinHack();
	public final SafeWalkHack safeWalkHack = new SafeWalkHack();
	public final SneakHack sneakHack = new SneakHack();
	
	// Combat
	public final AutoLeaveHack autoLeaveHack = new AutoLeaveHack();
	public final AutoRespawnHack autoRespawnHack = new AutoRespawnHack();
	public final AutoTotemHack autoTotemHack = new AutoTotemHack();
	
	// Render
	public final BreadcrumbsHack breadcrumbsHack = new BreadcrumbsHack();
	public final FullbrightHack fullbrightHack = new FullbrightHack();
	public final HealthTagsHack healthTagsHack = new HealthTagsHack();
	public final ItemEspHack itemEspHack = new ItemEspHack();
	public final LavaWaterEspHack lavaWaterEspHack = new LavaWaterEspHack();
	public final LogoutSpotsHack logoutSpotsHack = new LogoutSpotsHack();
	public final MobEspHack mobEspHack = new MobEspHack();
	public final MobSearchHack mobSearchHack = new MobSearchHack();
	public final MobSpawnEspHack mobSpawnEspHack = new MobSpawnEspHack();
	public final NewChunksHack newChunksHack = new NewChunksHack();
	public final NoBackgroundHack noBackgroundHack = new NoBackgroundHack();
	public final NoFireOverlayHack noFireOverlayHack = new NoFireOverlayHack();
	public final NoVignetteHack noVignetteHack = new NoVignetteHack();
	public final NoWeatherHack noWeatherHack = new NoWeatherHack();
	public final OpenWaterEspHack openWaterEspHack = new OpenWaterEspHack();
	public final PlayerEspHack playerEspHack = new PlayerEspHack();
	public final PortalEspHack portalEspHack = new PortalEspHack();
	public final RadarHack radarHack = new RadarHack();
	public final SearchHack searchHack = new SearchHack();
	public final TridentEspHack tridentEspHack = new TridentEspHack();
	public final WaypointsHack waypointsHack = new WaypointsHack();
	
	// Other
	public final AntiAfkHack antiAfkHack = new AntiAfkHack();
	public final AutoFishHack autoFishHack = new AutoFishHack();
	public final AutoLibrarianHack autoLibrarianHack = new AutoLibrarianHack();
	public final AutoReconnectHack autoReconnectHack = new AutoReconnectHack();
	public final CheatDetectorHack cheatDetectorHack = new CheatDetectorHack();
	public final FeedAuraHack feedAuraHack = new FeedAuraHack();
	public final PanicHack panicHack = new PanicHack();
	public final PortalGuiHack portalGuiHack = new PortalGuiHack();
	public final TooManyHaxHack tooManyHaxHack = new TooManyHaxHack();
	
	// Items
	public final AntiDropHack antiDropHack = new AntiDropHack();
	public final AutoDisenchantHack autoDisenchantHack =
		new AutoDisenchantHack();
	public final AutoDropHack autoDropHack = new AutoDropHack();
	public final AutoEatHack autoEatHack = new AutoEatHack();
	public final AutoStealHack autoStealHack = new AutoStealHack();
	public final ChestSearchHack chestSearchHack = new ChestSearchHack();
	public final EnchantmentHandlerHack enchantmentHandlerHack =
		new EnchantmentHandlerHack();
	public final SignFramePTHack signFramePTHack = new SignFramePTHack();
	
	// UI / utility
	public final ClickGuiHack clickGuiHack = new ClickGuiHack();
	
	private final TreeMap<String, Hack> hax =
		new TreeMap<>(String::compareToIgnoreCase);
	
	private final EnabledHacksFile enabledHacksFile;
	private final FavoriteHacksFile favoriteHacksFile;
	private final Path profilesFolder =
		WurstClient.INSTANCE.getWurstFolder().resolve("enabled hacks");
	
	private final EventManager eventManager =
		WurstClient.INSTANCE.getEventManager();
	
	public HackList(Path enabledHacksFile, Path favoriteHacksFile)
	{
		this.enabledHacksFile = new EnabledHacksFile(enabledHacksFile);
		this.favoriteHacksFile = new FavoriteHacksFile(favoriteHacksFile);
		
		try
		{
			for(Field field : HackList.class.getDeclaredFields())
			{
				if(!field.getName().endsWith("Hack"))
					continue;
				
				Hack hack = (Hack)field.get(this);
				hax.put(hack.getName(), hack);
			}
			
		}catch(Exception e)
		{
			String message = "Initializing Wurst hacks";
			CrashReport report = CrashReport.create(e, message);
			throw new CrashException(report);
		}
		
		eventManager.add(UpdateListener.class, this);
	}
	
	/**
	 * Toggle the per-hack "Only above ground" filter for all hacks that
	 * expose the setting. Useful for binding to a key or calling from the
	 * command interface.
	 */
	public void setAboveGroundFilterEnabled(boolean enabled)
	{
		AboveGroundFilterManager.toggle(this, enabled);
	}
	
	/**
	 * Set the Y threshold used by hacks that expose the above-ground filter.
	 */
	public void setAboveGroundFilterY(int y)
	{
		AboveGroundFilterManager.setY(this, y);
	}
	
	@Override
	public void onUpdate()
	{
		enabledHacksFile.load(this);
		favoriteHacksFile.load(this);
		eventManager.remove(UpdateListener.class, this);
	}
	
	public void saveEnabledHax()
	{
		enabledHacksFile.save(this);
	}
	
	public void saveFavoriteHax()
	{
		favoriteHacksFile.save(this);
	}
	
	public Hack getHackByName(String name)
	{
		return hax.get(name);
	}
	
	public Collection<Hack> getAllHax()
	{
		return Collections.unmodifiableCollection(hax.values());
	}
	
	public int countHax()
	{
		return hax.size();
	}
	
	public ArrayList<Path> listProfiles()
	{
		if(!Files.isDirectory(profilesFolder))
			return new ArrayList<>();
		
		try(Stream<Path> files = Files.list(profilesFolder))
		{
			return files.filter(Files::isRegularFile)
				.collect(Collectors.toCollection(ArrayList::new));
			
		}catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void loadProfile(String fileName) throws IOException, JsonException
	{
		enabledHacksFile.loadProfile(this, profilesFolder.resolve(fileName));
	}
	
	public void saveProfile(String fileName) throws IOException, JsonException
	{
		enabledHacksFile.saveProfile(this, profilesFolder.resolve(fileName));
	}
}
