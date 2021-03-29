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

package com.gaetan.staffsecurity.command;

import com.gaetan.api.command.utils.annotation.Command;
import com.gaetan.api.command.utils.command.Context;
import com.gaetan.api.command.utils.target.CommandTarget;
import com.gaetan.api.message.Message;
import com.gaetan.staffsecurity.SecurityPlugin;
import com.gaetan.staffsecurity.manager.managers.ConfigManager;
import org.bukkit.command.ConsoleCommandSender;

public final class SecurityCommand {
    /**
     * Reference to the main class
     */
    private final SecurityPlugin securityPlugin;

    /**
     * Constructor for the SecurityCommand class.
     *
     * @param securityPlugin Reference to the main class
     */
    public SecurityCommand(final SecurityPlugin securityPlugin) {
        this.securityPlugin = securityPlugin;
    }

    /**
     * Command to show the commands.
     * Note: This can only be used by the console
     *
     * @param context The command argument
     */
    @Command(name = "security")
    public void handleCommand(final Context<ConsoleCommandSender> context) {
        final ConfigManager configManager = this.securityPlugin.getManagerHandler().getConfigManager();

        if (context.getTarget() != CommandTarget.CONSOLE) {
            context.sendMessage(configManager.getNotConsole());
            return;
        }

        configManager.getHelpMessage()
                .forEach(message -> context.sendMessage(Message.tl(message)));
    }

    /**
     * Command to add players to the safe op list
     * Note: This can only be used by the console
     *
     * @param context The command argument
     */
    @Command(name = "security.addop")
    public void handleCommand_AddOP(final Context<ConsoleCommandSender> context, final String name) {
        final ConfigManager configManager = this.securityPlugin.getManagerHandler().getConfigManager();

        if (context.getTarget() != CommandTarget.CONSOLE) {
            context.sendMessage(configManager.getNotConsole());
            return;
        }

        if (!configManager.getOpPlayers().contains(name)) {
            configManager.getOpPlayers().add(name);
            context.sendMessage(configManager.getOpAdd().replace("%s", name));
        } else context.sendMessage(configManager.getOpAlready().replace("%s", name));

    }

    /**
     * Command to add players to the safe op list
     * Note: This can only be used by the console
     *
     * @param context The command argument
     */
    @Command(name = "security.removeop")
    public void handleCommand_RemoveOP(final Context<ConsoleCommandSender> context, final String name) {
        final ConfigManager configManager = this.securityPlugin.getManagerHandler().getConfigManager();

        if (context.getTarget() != CommandTarget.CONSOLE) {
            context.sendMessage(configManager.getNotConsole());
            return;
        }

        if (configManager.getOpPlayers().contains(name)) {
            configManager.getOpPlayers().remove(name);
            context.sendMessage(configManager.getOpRemove().replace("%s", name));
        } else context.sendMessage(configManager.getOpIsNot().replace("%s", name));
    }
}
