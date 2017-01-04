package trademe;

import com.eviware.soapui.plugins.PluginAdapter;
import com.eviware.soapui.plugins.PluginConfiguration;

@PluginConfiguration(groupId = "trademe.plugins", name = "Show Disabled Items Plugin", version = "0.1",
        autoDetect = true, description = "A simple plugin to show disabled items in a project",
        infoUrl = "varun gaur" )
public class PluginConfig extends PluginAdapter {
}
