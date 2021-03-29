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

package com.gaetan.staffsecurity;

import com.gaetan.api.annotation.GaetanApplication;
import com.gaetan.api.plugin.SimplePlugin;
import com.gaetan.staffsecurity.command.SecurityCommand;
import com.gaetan.staffsecurity.listener.PlayerListener;
import com.gaetan.staffsecurity.manager.ManagerHandler;

@GaetanApplication(name = "StaffSecurity", authors = "GaetanOff", version = "0.1", main = "com.gaetan.staffsecurity.SecurityPlugin", depend = "")
public final class SecurityPlugin extends SimplePlugin {
    /**
     * Reference to the ManagerHandler
     */
    private ManagerHandler managerHandler;

    /**
     * Method to launch the plugin
     * Note: This is the same as the classic onEnable
     */
    @Override
    protected void onPluginStart() {
        this.saveDefaultConfig();

        this.managerHandler = new ManagerHandler(this);

        this.registerCommands(
                new SecurityCommand(this)
        );
    }

    /**
     * Method to register listener
     * Note: This will be trigger after the loading of the server
     */
    @Override
    protected void registerListener() {
        new PlayerListener(this);
    }

    /**
     * Method to stop the plugin
     * Note: This is the same as the classic onDisable
     */
    @Override
    protected void onPluginStop() {
        this.managerHandler.getConfigManager().save();
    }

    /**
     * Getter to get the ManagerHandler reference.
     *
     * @return The reference ManagerHandler
     */
    public ManagerHandler getManagerHandler() {
        return this.managerHandler;
    }
}
