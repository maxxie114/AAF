package CWR;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
// use import cn.nukkit.lang.TextContainer; instead of cn.nukkit.event.TextContainer;
import cn.nukkit.lang.TextContainer;
import cn.nukkit.event.entity.EntityDamageByEntityEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDeathEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerKickEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginLogger;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class main extends PluginBase implements Listener
{
  public static Config config;
  /*
  public String join;
  public String quit;
  public String kick;
  public String pvp;
  public String projectile;
  public String contact;
  public String fire;
  public String drowning;
  public String lava;
  public String entityExplosion;
//  /
  public String blockExplosion;
//  /
  public String fall;
  public String suicide;
//  /
  public String suffocation;
  public String deathByVoid;
  public String magic;
  public String lightning;
  public String entityAttack;
//  / not gonna mess with CUSTOM :p
*/
  
//  public main() {}
  
  @Override
  public void onEnable()
  {
    this.getServer().getPluginManager().registerEvents(this, this);
    this.getLogger().info(TextFormat.GREEN + "AAF by coke loaded.");
    this.getDataFolder().mkdirs();
    config = new Config(new File(this.getDataFolder(), "config.yml"), Config.YAML, new LinkedHashMap() {
    { put("join-message", "&7%player% joined."); 
      put("quit-message", "&7%player% quit."); 
      put("pvp", "&7%player% was mugged by %killer%"); 
      put("projectile", "&7%player% was hit by a fast moving object"); 
      put("suffocation", "&7%player% was smashed between two hard objects"); 
      put("entity", "&7%player% got mutalated by a mob of some sorts"); 
      put("potion", "&7%player% withered away by some potion"); 
      put("lightning", "&7%player% got fried by nature"); 
      put("void", "&7%player% fell into a black hole"); 
      put("cactus", "&7%player% was pricked"); 
      put("fire", "&7%player% was burnt to a crisp"); 
      put("drowning", "&7%player% drowned"); 
      put("lava", "&7%player% melted"); 
      put("entity-explosion", "&7%player% became shrapnel by mob"); 
      put("block-explosion", "&7%player% became shrapnel from tnt"); 
      put("fall", "&7%player% fell from a great height"); 
      put("suicide", "&7%player% took themselves away");
      }
     });
    /*
    join = ((String)config.get("join-message"));
    quit = ((String)config.get("quit-message"));
    kick = ((String)config.get("kick-message"));
    pvp = ((String)config.get("pvp"));
    suffocation = ((String)config.get("suffocation"));
    entityAttack = ((String)config.get("entity"));
    deathByVoid = ((String)config.get("void"));
    magic = ((String)config.get("potion"));
    lightning = ((String)config.get("lightning"));
    projectile = ((String)config.get("projectile"));
    contact = ((String)config.get("contact"));
    fire = ((String)config.get("fire"));
    drowning = ((String)config.get("drowning"));
    lava = ((String)config.get("lava"));
    entityExplosion = ((String)config.get("entity-explosion"));
    blockExplosion = ((String)config.get("block-explosion"));
    fall = ((String)config.get("fall"));
    suicide = ((String)config.get("suicide"));
    */
  }
  
    private String format(Player player, String message) {
      return
        cn.nukkit.utils.TextFormat.colorize('&', message).replaceAll("%player%", 
          player.getPlayer().getName()).replaceAll("%players%", String.valueOf(getServer().getOnlinePlayers().size())).replaceAll("%tps%", 
            String.valueOf(getServer().getTicksPerSecond())).replaceAll("%maxplayers%", String.valueOf(getServer().getMaxPlayers())).replaceAll("%motd%", 
              getServer().getMotd());
    }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
//    /Player player = event.getPlayer();
//    /format(event.getPlayer(), getConfig().getString("join-message"))
    event.setJoinMessage(format(event.getPlayer(), getConfig().getString("join-message")));
  }
  
  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
//    /Player player = event.getPlayer();
//    /format(event.getPlayer(), getConfig().getString("join-message"))
    event.setQuitMessage(format(event.getPlayer(), getConfig().getString("quit-message")));
  }
  
  @EventHandler
  public void onKick(PlayerKickEvent event) {
//    /Player player = event.getPlayer();
//    /format(event.getPlayer(), getConfig().getString("join-message"))
    event.setQuitMessage(format(event.getPlayer(), getConfig().getString("kick-message")));
  }
  
  /*
   * if (victim.getLastDamageCause() != null) {
            damage = victim.getLastDamageCause().getCause();
        } else {
            return;
        }
   */
  
  /*
   * if (damage.equals(DamageCause.ENTITY_ATTACK))
   */
  
  @EventHandler
  public void onDeath(PlayerDeathEvent event) {
    Entity entity = event.getEntity();
    DamageCause damage = null;
    if (entity.getLastDamageCause() != null) {
      damage = entity.getLastDamageCause().getCause();
    } else {
        return;
      }
      if ((entity instanceof Player)) {
        Player player = (Player)entity;
        if (damage.equals(DamageCause.SUICIDE)) {
          event.setDeathMessage(TextFormat.colorize(getConfig().getString("suicide").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.PROJECTILE)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("projectile").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.CONTACT)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("contact").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.FIRE)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("fire").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.DROWNING)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("drowning").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.LAVA)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("lava").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.ENTITY_EXPLOSION)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("entity-explosion").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.FALL)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("fall").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.ENTITY_ATTACK)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("entity").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.BLOCK_EXPLOSION)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("block-explosion").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.SUFFOCATION)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("suffocation").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.VOID)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("void").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.MAGIC)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("potion").toString().replace("%player%", player.getName())));
        } else if (damage.equals(DamageCause.LIGHTNING)) {
            event.setDeathMessage(TextFormat.colorize(getConfig().getString("lightning").toString().replace("%player%", player.getName())));
        }
      }
//        / public String suffocation; public String deathByVoid; public String magic; public String lightning;
  }
}
