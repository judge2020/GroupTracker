package bawbcat.gt;

import java.util.ArrayList;
import java.util.List;

public class Modifier {    
    public List<String> searchText = new ArrayList<String>();
    public boolean needed = true;
    public String display;
    public List<String> requiredMods = new ArrayList<String>(); //Only one of the required mods is actually "required" to be present. Works for now, don't change it or you break stuff
    public ModifierPos pos = ModifierPos.FRONT;

    public Modifier(String display) {
        this.display = display;
        searchText.add(display);
    }
    
    public Modifier(String display, boolean needed) {
        this.display = display;
        this.needed = needed;
        searchText.add(display);
    }
    
    public Modifier(String display, String[] searchText) {
        this.display = display;
        this.searchText.add(display);
        for (String s : searchText) {
            this.searchText.add(s);
        }
    }

    public Modifier(String display, boolean needed, String[] searchText) {
        this.display = display;
        this.needed = needed;
        this.searchText.add(display);
        for (String s : searchText) {
            this.searchText.add(s);
        }
    }
    
    public Modifier pos(ModifierPos pos) {
        this.pos = pos;
        return this;
    }
    
    public Modifier requires(String s) {
        requiredMods.add(s);
        return this;
    }
    
    public enum ModifierPos {
        FRONT,
        BACK
    }
}
