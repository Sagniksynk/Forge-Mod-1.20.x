# ⚔️ New Mod

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green?style=flat&logo=minecraft)
![Forge](https://img.shields.io/badge/Forge-47.4.20-orange?style=flat)
![Language](https://img.shields.io/badge/Language-Java-blue)
![Architecture](https://img.shields.io/badge/Architecture-Event--Driven-orange)
![License](https://img.shields.io/badge/License-MIT-success)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)

**New Mod** is a Minecraft Forge mod for **1.20.1** that introduces a diverse set of features — from a fully functional **firearms system** with ammo management and custom sounds, to **corrupted soul-themed armor & food**, a custom **ore and gem material**, and interactive utility items. Built on a clean, event-driven architecture using Forge's `DeferredRegister` and Capability system.

## 🎮 Mod Features

### 🔫 Gun System
The centrepiece of the mod — a complete, multiplayer-compatible firearm framework:

* **Three Guns** — each with unique stats:

  | Gun     | Damage | Speed | Pellets | Spread | Fire Rate (ticks) | Max Ammo |
  | :------ | :----- | :---- | :------ | :----- | :---------------- | :------- |
  | Pistol  | 8      | 3.0   | 1       | 2.0    | 10                | 12       |
  | Rifle   | 12     | 4.5   | 1       | 0.5    | 4                 | 30       |
  | Shotgun | 5×6    | 2.0   | 6       | 15.0   | 40                | 8        |

* **Bullet Entity** — a real projectile spawned server-side that travels along the player's look vector with per-gun spread.
* **Ammo Capability** — uses Forge's `ICapabilityProvider` to attach per-player ammo data (`GunCapabilityProvider`), persisted and synced via custom network packets (`AmmoDataPacket`).
* **Reload System** — press the `R` key (configurable keybind) to reload. A `ReloadPacket` is sent to the server to refill the correct gun's ammo.
* **Ammo HUD Overlay** — a custom GUI overlay renders the current gun name and ammo count (`X / MAX`) in the bottom-right corner of the screen. Turns red when empty.
* **Custom Sounds** — five registered sound events: `pistol_shoot`, `rifle_shoot`, `shotgun_shoot`, `gun_reload`, `gun_empty`. An empty-click sound plays when firing with no ammo.

### 💎 Sapphire Material
A new ore-to-crafting-material pipeline:

* **Sapphire Ore** — spawns in stone, drops 3–6 XP on mine. Requires the correct tool.
* **Raw Sapphire** — raw drop form.
* **Sapphire** — refined gem, also placed in the vanilla `INGREDIENTS` creative tab.
* **Block of Sapphire** — compressed storage block with amethyst sound type.
* **Block of Diamond** — a custom diamond block variant with amethyst sound type.

### 🧪 Food & Consumables

| Item                  | Nutrition | Saturation | Effect(s)                                                                                              |
| :-------------------- | :-------- | :--------- | :----------------------------------------------------------------------------------------------------- |
| **Sapphire Berry**    | 4         | 0.5        | Guaranteed Regeneration II + 50% chance of Speed II & Jump Boost II (20s each)                        |
| **Mysterious Flesh**  | 2         | 0.1        | 80% chance of Poison I (5s)                                                                            |
| **Corrupted Soul Stew** | 10      | 1.5        | Random good effect (20s, Amp II) + random bad effect (10s) + 10% strong Wither + 5% full heal. Always edible. Returns a Bowl. |

* **Crafting**: Smelt `Mysterious Flesh` in a furnace (200 ticks, 1.0 XP) to obtain `Corrupted Soul Stew`.

### 🛡️ Corrupted Armor Set
A full armor set crafted from Sapphire:

* **Material Stats**: Defense `3/6/8/3` (boots/leggings/chest/helmet), Toughness `2.0`, Knockback Resistance `0.1`, Enchantability `15`. Repair with Sapphire.
* **Full Set Bonus** (server-side tick event): When all four pieces are worn simultaneously, the player continuously receives:
  * Strength II
  * Speed I
  * Night Vision
  * Resistance I

### 🔍 Metal Detector
A utility tool with 100 durability:

* Right-click on any block to scan **64 blocks downward** for any ore (coal, iron, gold, diamond, emerald, lapis, redstone, copper, or any Forge `ores` tag block).
* Reports the ore type and Y-level via a system message. Loses 1 durability per use.

### 📖 Soul Cookbook
A client-side GUI item:

* Right-click to open a custom screen (`SoulCookbookScreen`) — a reference/recipe book for the mod's soul-themed items.

### 🔊 Sound Block
An interactive decorative block:

* **Right-click** to activate: plays a Didgeridoo note-block sound, sets the block to a `GLOWING=true` blockstate, then resets after 1 second (20 ticks) via a scheduled tick.

### 🪄 Magic Wand
A custom item registered in the vanilla `INGREDIENTS` creative tab alongside Sapphire and Diamond.

## 🕹️ Controls

| Key | Action |
| :-- | :----- |
| **Right Click** (holding gun) | **Fire** the equipped gun |
| **R** | **Reload** the equipped gun |

## 🛠️ Technical Architecture

### 1. Registry System
All game objects (items, blocks, sounds, entities) are registered using Forge's **`DeferredRegister`** pattern, ensuring safe, ordered loading on the Forge event bus.

### 2. Capability System (`GunCapability`)
Per-player ammo state is managed via a custom `ICapabilityProvider`. This keeps ammo data attached to the player entity, correctly separated from item NBT, and accessible from both server and client code.

### 3. Network Layer (`ModMessages`)
A dedicated networking channel handles client↔server communication:
* **`AmmoDataPacket`** — server pushes updated ammo counts to the client after each shot.
* **`ReloadPacket`** — client sends a reload request to the server when the keybind is pressed.

### 4. Event-Driven Design
* **Armor effects** run on `TickEvent.PlayerTickEvent` (server-side only).
* **HUD overlay** hooks into `RenderGuiOverlayEvent.Post` after the hotbar render.
* **Keybinds** are consumed in `TickEvent.ClientTickEvent`.

### 5. Core Classes Overview

| Class | Responsibility |
| :---- | :------------- |
| `GunItem.java` | Abstract base for all guns — handles firing logic, ammo consumption, cooldowns, and sound. |
| `GunCapabilityProvider.java` | Attaches and exposes `GunCapability` to player entities. |
| `ModMessages.java` | Registers the mod's networking channel and packets. |
| `AmmoHudOverlay.java` | Renders the in-world ammo HUD on the client. |
| `BulletEntity.java` | Server-spawned projectile entity with per-gun damage. |
| `CorruptedArmorItem.java` | Armor piece + inner static event class for full-set bonus detection. |
| `SoundBlock.java` | Stateful block with blockstate, scheduled tick, and sound playback. |
| `MetalDetectorItem.java` | Downward ore-scan tool using block tag checks. |
| `ModSounds.java` | `DeferredRegister` for all five custom sound events. |

## 🚀 How to Build & Run

### Prerequisites
* JDK 17
* IntelliJ IDEA (recommended) or any IDE

### Steps

1. **Clone the repository:**
   ```bash
   git clone <your-repo-url>
   cd Forge-Tutorial-1.20.x
