# 🌸 NiceWurst — Build Guide

**NiceWurst** is a *cheat-free, survival-friendly* variant of the [Wurst7-CevAPI](https://github.com/cev-api/Wurst7-CevAPI) client.  
It’s designed for players who want Wurst 7 CevAPI’s quality-of-life and creative utilities — without unfair features.

You don’t need a separate repository or fork which is why this one is empty.
Just clone Wurst7-CevAPI and compile it with a single Gradle flag: `-Pnicewurst=1`.

---

## What Makes NiceWurst Different

When built with `-Pnicewurst=1`, the project automatically:

- **Whitelists** a limited, survival-safe set of hacks.  
- **Rebrands** the UI, Fabric metadata, and HUD to “NiceWurst”.  
- **Hides** sensitive features like Alt Manager, Anti-Fingerprint, and X-Ray block manager.  
- **Separates configs** under `.minecraft/nicewurst/`.  
- **Enforces fair rendering** — ESPs and tracers obey depth testing (no wall-peek).  
- **Changes mod ID** to `nicewurst`, allowing side-by-side installs with Wurst7-CevAPI.

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

## NiceWurst Build Flag Explained

| Area | Effect |
|------|---------|
| **Jar & metadata** | Renames output jar, swaps Fabric metadata to “NiceWurst”, and sets mod ID to `nicewurst`. |
| **Config path** | Saves configs to `.minecraft/nicewurst/`, isolating from Wurst data. |
| **Hack registry** | `NiceWurstModule` filters out non-allowed hacks before init. |
| **UI elements** | Removes buttons for Alt Manager, Anti-Fingerprint, and X-Ray blocks. |
| **Rendering** | ESP/Tracer rendering is forced to obey depth testing (no x-ray outlines). |
| **Persistence** | Enabled hacks and favorites are preserved safely across restarts. |
| **Distribution** | Separate mod ID means both Wurst and NiceWurst can be installed together. |

---

## Allowed Features

### **Blocks**
AutoBuild · AutoSign · AutoTool · BuildRandom · Excavator · InstantBunker · ScaffoldWalk · TemplateTool

### **Fun**
All fun-category hacks

### **Movement**
BunnyHop · AutoSprint · AutoWalk · AutoSwim · Dolphin · SafeWalk · Sneak · InvWalk

### **Combat**
AutoRespawn · AutoTotem · AutoLeave

### **Render**
Breadcrumbs · Fullbright · HealthTags · ItemESP · LavaWaterESP · LogoutSpots · MobESP · MobSearch · MobSpawnESP · NewChunks · NoBackground · NoFireOverlay · NoVignette · NoWeather · Freecam · OpenWaterESP · PlayerESP · PortalESP · Radar · Search · TridentESP · Waypoints

### **Other**
AntiAFK · AutoFish · AutoLibrarian · AutoReconnect · CheatDetector · FeedAura · Panic · PortalGUI · SafeTP · TooManyHax (ClickGUI/Navigator are preserved separately)

### **Items**
AntiDrop · AutoDisenchant · AutoDrop · AutoEat · AutoSteal · ChestSearch · EnchantmentHandler · SignFramePT

> Everything else is excluded from the UI and *cannot* be toggled.

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
