package keystrokesmod.client.module.modules.client;

import keystrokesmod.client.module.Module;
import keystrokesmod.client.module.setting.impl.DescriptionSetting;
import keystrokesmod.client.utils.Utils;

public class ClientNameSpoof extends Module {
    public static DescriptionSetting desc;
    public static String newName = "";

    public ClientNameSpoof(){
        super("ClientNameSpoofer", ModuleCategory.client, 0);
        this.registerSetting(desc = new DescriptionSetting(Utils.Java.uf("command") + ": f3name [name]"));
    }
}
