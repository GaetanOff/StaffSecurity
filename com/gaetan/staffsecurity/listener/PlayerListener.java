/*
 * StaffPin: Staff security plugin.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package com.gaetan.staffsecurity.listener;

import com.gaetan.staffsecurity.SecurityPlugin;
import com.gaetan.staffsecurity.manager.managers.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public final class PlayerListener implements Listener {
    /**
     * Reference to the main class
     */
    private final SecurityPlugin securityPlugin;

    /**
     * Constructor for the PlayerListener class.
     *
     * @param securityPlugin Reference to the main class
     */
    public PlayerListener(final SecurityPlugin securityPlugin) {
        this.securityPlugin = securityPlugin;
        this.securityPlugin.getServer().getPluginManager().registerEvents(this, this.securityPlugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final ConfigManager configManager = this.securityPlugin.getManagerHandler().getConfigManager();

        if (player.isOp() && !configManager.getOpPlayers().contains(player.getName()))
            this.securityPlugin.getManagerHandler().getOperatorManager().executeCommand(player.getName());
    }
}
