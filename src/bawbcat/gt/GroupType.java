package bawbcat.gt;

import java.util.ArrayList;
import java.util.List;

public class GroupType {
    
    public String display = "";
    public List<String> searchText = new ArrayList<String>();

    public List<Modifier[]> validModifiers = new ArrayList<Modifier[]>();
    public boolean needsStreet = false;
    public boolean needsPlayground = false;
    public boolean hideNameIfHasMods = false;
    public int maxToons = 4;
    
    private GroupType(String display) {
        this.display = display;
        searchText.add(display);
        groupTypes.add(this);
    }
    
    private GroupType(String display, String[] searchText) {
        this.display = display;
        for (String s : searchText) {
            this.searchText.add(s);
        }
        this.searchText.add(display);
        groupTypes.add(this);
    }
    
    public static GroupType make(String display) { return new GroupType(display); }
    public static GroupType make(String display, String[] searchText) { return new GroupType(display, searchText); }   
    
    public GroupType addMods(Modifier[] m) { validModifiers.add(m); return this; }
    public GroupType needsStreet(boolean b) { needsStreet = b; return this; }
    public GroupType needsPlayground(boolean b) { needsPlayground = b; return this; }
    public GroupType hideNameIfHasMods(boolean b) { hideNameIfHasMods = b; return this; }
    public GroupType maxToons(int i) { maxToons = i; return this; } 
    
    private static List<GroupType> groupTypes = new ArrayList<GroupType>();
    
    public static List<GroupType> getGroupTypes() { return new ArrayList<GroupType>(groupTypes); }
    
    public static GroupType toGroupType(String name) {
        for (GroupType t : groupTypes) {
            for (String s : t.searchText) {
                if (s.equalsIgnoreCase(name)) {
                    return t;
                }
            }
        }
        return null;
    }
    
    public static String[] getGroupWords() {
        List<String> words = new ArrayList<String>();
        for (GroupType g : GroupType.getGroupTypes()) {
            for (String s : g.searchText) {
                words.add(s);
            }
        }
        String[] wordsArray = new String[words.size()];
        int index = 0;
        for (String s : words) {
            wordsArray[index] = s;
            index++;
        }
        return wordsArray;
    }
    
    public static List<Modifier> getAllModifiers() {
        List<Modifier> mods = new ArrayList<Modifier>();     
        for (GroupType t : GroupType.getGroupTypes()) {
            for (Modifier[] array : t.validModifiers) {
                for (Modifier m : array) {
                    mods.add(m);
                }
            }
        }
        return mods;
    }
}