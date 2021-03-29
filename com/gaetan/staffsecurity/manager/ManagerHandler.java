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

package com.gaetan.staffsecurity.manager;

import com.gaetan.staffsecurity.SecurityPlugin;
import com.gaetan.staffsecurity.manager.managers.ConfigManager;
import com.gaetan.staffsecurity.manager.managers.OperatorManager;

public class ManagerHandler {
    /**
     * Reference to the main class
     */
    private final SecurityPlugin securityPlugin;

    /**
     * Manager reference
     */
    private final ConfigManager configManager;
    private final OperatorManager operatorManager;

    /**
     * Constructor for the ManagerHandler class.
     *
     * @param securityPlugin Reference to the main class
     */
    public ManagerHandler(final SecurityPlugin securityPlugin) {
        this.securityPlugin = securityPlugin;

        this.configManager = new ConfigManager(this);
        this.operatorManager = new OperatorManager(this);
    }

    /**
     * Getter to get the SecurityPlugin reference.
     *
     * @return The reference to the main class
     */
    public SecurityPlugin getSecurityPlugin() {
        return this.securityPlugin;
    }

    /**
     * Getter to get all the manager reference
     *
     * @return The reference to the managers
     */
    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public OperatorManager getOperatorManager() {
        return this.operatorManager;
    }
}
