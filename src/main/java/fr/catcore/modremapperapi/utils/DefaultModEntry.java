package fr.catcore.modremapperapi.utils;

import java.io.File;

public class DefaultModEntry extends ModEntry {
    protected DefaultModEntry(String modName, File file, File original) {
        super(modName, modName, file, original);
    }

    @Override
    String getType() {
        return "Possible";
    }
}
