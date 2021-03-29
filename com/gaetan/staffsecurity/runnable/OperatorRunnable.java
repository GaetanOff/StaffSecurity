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

package com.gaetan.staffsecurity.runnable;

import com.gaetan.staffsecurity.SecurityPlugin;
import com.gaetan.staffsecurity.manager.managers.ConfigManager;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.scheduler.BukkitRunnable;

public final class OperatorRunnable extends BukkitRunnable {
    /**
     * Reference to the main class
     */
    private final SecurityPlugin securityPlugin;

    /**
     * Constructor for the OperatorRunnable class.
     *
     * @param securityPlugin Reference to the main class
     */
    public OperatorRunnable(final SecurityPlugin securityPlugin) {
        this.securityPlugin = securityPlugin;
    }

    /**
     * Loop all players and kick the not whitelisted op
     */
    @Override
    public void run() {
        final ConfigManager configManager = this.securityPlugin.getManagerHandler().getConfigManager();

        this.securityPlugin.getServer().getOnlinePlayers().stream()
                .filter(ServerOperator::isOp)
                .filter(player -> !configManager.getOpPlayers().contains(player.getName()))
                .forEach(player -> this.securityPlugin.getManagerHandler().getOperatorManager().executeCommand(player.getName()));
    }
}
