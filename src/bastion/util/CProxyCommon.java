package bastion.util;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class CProxyCommon
{
    public void registerRendering ()
    {
    }

    public void addNames () //This code may disappear later
    {
        String langDir = "/assets/chaoticbastion/lang/";
        String[] langFiles = { "en_US.lang", "en_PT.lang", "de_DE.lang" };

        for (String langFile : langFiles)
        {
            try
            {
                LanguageRegistry.instance().loadLocalization(langDir + langFile, langFile.substring(langFile.lastIndexOf('/') + 1, langFile.lastIndexOf('.')), true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
