package com.crazymoose.paperdupe;

import com.mojang.logging.LogUtils;
import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Modules;
import org.slf4j.Logger;

public class Main extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();
    public static final Category CATEGORY = new Category("Paper Dupe");

    @Override
    public void onInitialize() {
        LOG.info("Initializing Paper Dupe Addon");

        // Modules
        Modules.get().add(new PaperDupe());
    }

    @Override
    public void onRegisterCategories() {
        Modules.registerCategory(CATEGORY);
    }

    @Override
    public String getPackage() {
        return "com.crazymoose.paperdupe";
    }
}
