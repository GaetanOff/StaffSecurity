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

package com.gaetan.staffsecurity.manager.managers;

import com.gaetan.staffsecurity.SecurityPlugin;
import com.gaetan.staffsecurity.manager.Manager;
import com.gaetan.staffsecurity.manager.ManagerHandler;
import com.gaetan.staffsecurity.runnable.OperatorRunnable;

public final class OperatorManager extends Manager {
    /**
     * Constructor for the OperatorManager class.
     *
     * @param handler Reference to the ManagerHandler
     */
    public OperatorManager(final ManagerHandler handler) {
        super(handler);

        this.launchTask();
    }

    /**
     * Launch the task to loop all players and kick the not whitelisted op
     */
    private void launchTask() {
        final SecurityPlugin securityPlugin = this.handler.getSecurityPlugin();

        new OperatorRunnable(securityPlugin).runTaskTimer(securityPlugin, 0L, this.handler.getConfigManager().getOpTask());
    }

    /**
     * Launch the cmd from the config
     *
     * @param name The name of the '%s' in the command
     */
    public void executeCommand(final String name) {
        final SecurityPlugin securityPlugin = this.handler.getSecurityPlugin();

        securityPlugin.getServer()
                .dispatchCommand(securityPlugin.getServer().getConsoleSender(), this.handler.getConfigManager().getOpCommand().replace("%s", name));
    }
}
