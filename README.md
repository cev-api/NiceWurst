# 🌸 NiceWurst — Build Guide

![NiceWurst](https://i.imgur.com/86vmxQi.png)

**NiceWurst** is a *cheat-free, survival-friendly* variant of the [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI) client.  
It’s designed for players who want Wurst 7 CevAPI’s quality-of-life and creative utilities — without unfair features.

You don’t need a separate repository or fork which is why this one is empty.
Just clone [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI)  and compile it with a single Gradle flag: `-Pnicewurst=1`.

This means as [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI) is updated, so is NiceWurst!

---

## Where Can I Download It?

I have pre-compiled releases within [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI/releases) but if you want the latest pre-releases you'll have to keep reading and compile it yourself!

### Modrinth

This mod has been approved for listing on [Modrinth](https://modrinth.com/mod/nicewurst) and will now be the primary source for all updated compiled releases.

---

## What Makes NiceWurst Different to Wurst 7 CevAPI?

**Config Separation**: The runtime folder is chosen with ```BuildConfig.NICE_WURST``` so config files go under ```.minecraft/nicewurst/```. This is to ensure the separation of [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI) configurations from NiceWurst.

**Whitelisting**: The allowed set is implemented in ```NiceWurstModule.java``` via ```ALLOWED_HACKS``` (only those hacks remain enabled/visible) and ```ALLOWED_NAME_ONLY``` (e.g. ClickGUI, Navigator). This means once the project is compiled all other hacks remain inaccessible permanently.

**Removing Sensitive Features**: Access to Alt Manager, Anti-Fingerprint, and the XRay blocks manager is controlled by ```NiceWurstModule.showAltManager()```, ```showAntiFingerprintControls()```, and ```showXrayBlocksManager()``` - they return false when NiceWurst is active (features remain in code but are disabled). These aren't needed, nor nice, so they are removed.

**Render / No Wall‑Peek**: Depth-testing and tracer visibility enforcement are implemented in ```NiceWurstModule.java``` (```enforceDepthTest```, ```enforceDepthTest(RenderType)```, ```shouldEnforceTracerVisibility()```, ```shouldRenderTarget()```), and are applied in render utilities so ESPs/tracers obey depth tests for most callers (exceptions and exact caller lists are enumerated in the module such as ```net.wurstclient.hacks.WaypointsHack```). _This means that all ESP rendering does **not** pass through walls_.

## White Listed/Allowed Features

These features are what remains of the original [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI) client and were selected and or modified for their utility and to ensure there is no unfair advantage.

### Blocks

- **AutoBuild**: Automatically builds templates after you place a single starter block.
- **AutoSign**: Instantly writes configured text to every sign you place or edit.
- **AutoTool**: Auto-equips the fastest applicable tool from your hotbar when breaking a block.
- **BuildRandom**: Randomly places blocks around you to create varied builds.
- **Excavator**: Automatically breaks all blocks in the selected area for fast mining.
- **InstantBunker**: Builds a small defensive bunker around you (requires 57 blocks).
- **ScaffoldWalk**: Automatically places blocks beneath your feet for safe bridging and walking.
- **TemplateTool**: Creates and applies custom AutoBuild templates by scanning existing structures.

### Fun

- **All fun-category hacks**: Enables novelty/visual/motion hacks (e.g., Derp, LSD, MileyCyrus, skin toggles) that produce humorous effects or animations.

### Movement

- **BunnyHop**: Makes you jump automatically to maintain momentum.
- **AutoSprint**: Keeps you sprinting without holding the sprint key.
- **AutoWalk**: Moves you forward continuously without input.
- **AutoSwim**: Triggers the swimming animation/behavior automatically.
- **Dolphin**: Causes you to bob up in water like a dolphin for easier aquatic movement.
- **SafeWalk**: Prevents you from falling off edges.
- **Sneak**: Automatically keeps you sneaking.
- **InvWalk**: Allows movement while inventory or other GUIs are open.

### Combat

- **AutoRespawn**: Automatically respawns you whenever you die.
- **AutoTotem**: Automatically moves totems of undying to your off-hand.
- **AutoLeave**: Automatically leaves the server when your health is critically low.

### Render

- **Breadcrumbs**: Draws a visible trail showing where you have been.
- **Fullbright**: Forces full brightness so you can see in the dark.
- **HealthTags**: Shows other players' health values in their nametags.
- **ItemESP**: Highlights nearby dropped items through terrain.
- **LavaWaterESP**: Highlights only lava and water blocks for easy spotting.
- **LogoutSpots**: Shows locations where players logged out.
- **MobESP**: Highlights nearby mobs through walls and obstacles.
- **MobSearch**: Helps find specific mobs by highlighting matches and supporting queries.
- **MobSpawnESP**: Highlights areas where mobs can spawn.
- **NewChunks**: Highlights newly generated chunks around you.
- **NoBackground**: Removes the dark background behind GUI/inventory screens.
- **NoFireOverlay**: Removes the fire overlay when you are on fire.
- **NoVignette**: Removes the dark vignette effect at screen edges.
- **NoWeather**: Changes or disables client-side weather/time/moon visuals.
- **Freecam**: Lets you move the camera independently of your player.
- **OpenWaterESP**: Shows whether you are fishing in open water and outlines the area.
- **PlayerESP**: Highlights nearby players.
- **PortalESP**: Highlights nearby portals.
- **Radar**: Displays positions of nearby entities on a radar view.
- **Search**: Highlights blocks or entities that match a search query.
- **TrialChamberESP**: Highlights trial spawners/vaults with overlays and status info.
- **TridentESP**: Highlights tridents in the world, with owner-based coloring options.
- **Waypoints**: Lets you set and display custom world markers (including deaths).

### Other

- **AntiAFK**: Simulates movement to avoid AFK detection.
- **AntiSocial**: Automatically disconnects when players enter your range.
- **AutoFish**: Automates fishing, switching to better rods when found.
- **AutoLibrarian**: Automates librarian villager training to produce specific enchanted books.
- **AutoReconnect**: Automatically reconnects after a disconnect.
- **CheatDetector**: Monitors nearby players and reports suspicious movement/combat.
- **FeedAura**: Automatically feeds nearby animals (or allies) when applicable.
- **Panic**: Instantly disables all enabled hacks as an emergency action.
- **PortalGUI**: Enables GUIs inside portals for managing portal interactions.
- **SafeTP**: Activates a Blink period and sends a teleport command while ensuring safety.
- **TooManyHax**: Blocks or restricts unwanted features so you don't accidentally enable risky hacks.

### Items

- **AntiDrop**: Prevents accidental dropping of selected items (defaults to weapons/tools/shulker boxes).
- **AutoDisenchant**: Automatically feeds items into a grindstone to remove enchantments.
- **AutoDrop**: Automatically discards unwanted items from your inventory.
- **AutoEat**: Automatically consumes food based on configured hunger/health thresholds.
- **AutoSteal**: Automatically takes items from opened chests and shulker boxes based on filters.
- **ChestSearch**: Scans opened chests for matching items and highlights valuable chests.
- **EnchantmentHandler**: Overlays enchantment breakdowns in chest GUIs with quick-take actions.
- **ItemHandler**: HUD/GUI for inspecting nearby dropped items, selecting pick/reject rules, and tracing.
- **SignFramePT**: Right-click forwards interactions on item frames or signs to the block behind them (with hold-sneak to interact normally).
- **QuickShulker**: Quickly dump your inventory or a chest to a shulker by auto placing, filling and breaking.


> Everything else is excluded from the UI and *cannot* be toggled.

## Reminder
This is **NOT** a cheat, you **CANNOT** see ESP highlights through walls. This mod complies with section 3 of the [Modrinth Content Rules](https://modrinth.com/legal/rules).

---

## Prerequisites

| Requirement | Description |
|--------------|-------------|
| **Java 21 JDK** | Required for Minecraft 1.21.10 and Fabric. Set `JAVA_HOME` accordingly. |
| **Git** | Used to clone and update the repo. |
| **Gradle Wrapper** | Included (`gradlew.bat` / `./gradlew`). No global install needed. |
| **~4 GB Free Disk Space** | For caches and build outputs. |
| **Fabric for MC1.21.10** | Lightweight modding framework and loader required to run client-side mods like Wurst7 and NiceWurst. Install the Fabric Loader for Minecraft 1.21.10 and use a compatible Fabric API version (e.g. 0.137.0 + 1.21.10). |

---

## Repository Layout

All NiceWurst logic lives **inside** the main Wurst7-CevAPI repo:

~~~
Wurst7-CevAPI/
├─ build.gradle                       # Contains the NiceWurst build flag logic
├─ src/main/java/net/wurstclient/nicewurst/NiceWurstModule.java
├─ src/main/resources/fabric.mod.json # Rebranded metadata
└─ … (rest of Wurst7-CevAPI source)
~~~

No extra repository is needed — the flag toggles everything automatically.

---

## Quick Build

~~~bash
git clone https://github.com/cev-api/Wurst7-CevAPI.git
cd Wurst7-CevAPI
./gradlew build -Pnicewurst=1     # Windows: gradlew.bat build -Pnicewurst=1
~~~

After building, check `build/libs/` for:

~~~
NiceWurst-v7.51-MC1.21.10.jar
NiceWurst-v7.51-MC1.21.10-sources.jar
~~~

Drop the `NiceWurst-v7.51-MC1.21.10.jar` into your `.minecraft/mods/` folder to test.

---

## Testing Your Build

1. Copy `NiceWurst-v7.51-MC1.21.10.jar` into `.minecraft/mods/`.  
2. Launch Fabric 1.21.10 — the HUD should say **“NiceWurst 7”**.  
3. Confirm `.minecraft/nicewurst/` exists with:
   - `enabled-hacks.json`
   - `keybinds.json`
4. Verify missing UI elements (Alt Manager, Anti-Fingerprint).  
5. Enable a few allowed hacks, restart, and ensure they persist.  
6. Test render hacks — ESPs and tracers should *not* show through walls.

---

### 🎉 Done!
You’ve built your own **NiceWurst** client.  
Tweak the whitelist, update the branding if desired, then rebuild with:

```bash
./gradlew build -Pnicewurst=1
```
