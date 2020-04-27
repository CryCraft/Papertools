# Papertools

## Features
- [x] disable player join/quit message
- [x] papertools config reload
- [x] npcs: config, list, behaviors (packet reader)
- [x] Guis: items, inventories, intractable
- [x] spawn join

## TODO
- [x] npc command: create/delete/info


## API
- GUI
- Utils: SendPlayerTo (server, spawn), FromConfig(location)
- NPC: create, delete
- GuiInventory: items, inventories

## Permissions
| feature/command | permission |
| --- | --- |
| /papertools | papertools.command.papertools |
| /npc | papertools.command.npc |

## Commands

### npc

```sh
/npc list
```

* list all active npcs

### papertools

```sh
/papertools
```

* get papertools version


```sh
/papertools reload
```

* reload papertools config