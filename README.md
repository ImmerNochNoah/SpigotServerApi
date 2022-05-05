
### Coins und Level System mit MySQL für kleine und mittle große Netzwerke.

Die API wurde für die Server Version Spigot 1.8.8 erstellt und kann von jedem leicht für neuere Versionen ohne viel Aufwand umgeschrieben werden.

Falls du eine kleine Server-API für deinen Server suchst, kann dir das hier eventuell gefallen. 
Sie beinhaltet ein Basic Coin und Level System.


## Funktionen: 

- Level mit XP und voll einstellbaren Level-UP System
- Coin System
- Einstellbare config.yml


## Usage/Examples

```java
import de.immernochnoah.api.ServerAPI;

ServerAPI.getPrefix();
ServerAPI.getPlayerCoins(uuid);
ServerAPI.getPlayerLevel(uuid);
ServerAPI.getPlayerXP(uuid);
ServerAPI.getPlayerNextLevelXP(uuid);

ServerAPI.addCoins(uuid, coins);
ServerAPI.addPlayerXP(player, coins);

ServerAPI.removeCoins(uuid, coins);
```
