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

import com.gaetan.api.message.Message;
import com.gaetan.staffsecurity.manager.Manager;
import com.gaetan.staffsecurity.manager.ManagerHandler;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public final class ConfigManager extends Manager {
    /**
     * Cache
     */
    private final List<String> opPlayers, helpMessage;
    private final String opAdd, opRemove, opAlready, opIsNot, opCommand, notConsole;
    private final int opTask;

    /**
     * Constructor for the ConfigManager class.
     *
     * @param handler Reference to the ManagerHandler
     */
    public ConfigManager(final ManagerHandler handler) {
        super(handler);

        final FileConfiguration config = this.handler.getSecurityPlugin().getConfig();

        this.opPlayers = new ArrayList<>();
        this.opPlayers.addAll(config.getStringList("op.list"));
        this.opTask = config.getInt("op.time") * 20;

        this.opAdd = Message.tl(config.getString("lang.op.add"));
        this.opRemove = Message.tl(config.getString("lang.op.remove"));
        this.opAlready = Message.tl(config.getString("lang.op.is_already"));
        this.opIsNot = Message.tl(config.getString("lang.op.is_not"));
        this.opCommand = config.getString("command.op_detected");

        this.notConsole = Message.tl(config.getString("lang.not_console"));
        this.helpMessage = config.getStringList("lang.help");
    }

    /**
     * Method to save the config
     */
    public void save() {
        this.handler.getSecurityPlugin().getConfig().set("op.list", this.opPlayers);
        this.handler.getSecurityPlugin().saveConfig();
    }

    /**
     * Getter to cache reference
     *
     * @return The cache reference
     */
    public List<String> getOpPlayers() {
        return this.opPlayers;
    }

    public int getOpTask() {
        return this.opTask;
    }

    public String getOpAdd() {
        return this.opAdd;
    }

    public String getOpRemove() {
        return this.opRemove;
    }

    public String getOpAlready() {
        return this.opAlready;
    }

    public String getOpIsNot() {
        return this.opIsNot;
    }

    public String getOpCommand() {
        return this.opCommand;
    }

    public String getNotConsole() {
        return this.notConsole;
    }
    
    public List<String> getHelpMessage() {
        return this.helpMessage;
    }
}
