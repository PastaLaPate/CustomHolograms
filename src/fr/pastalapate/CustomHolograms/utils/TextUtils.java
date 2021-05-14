package fr.pastalapate.CustomHolograms.utils;

import org.bukkit.ChatColor;

public class TextUtils {
	
	public static String color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
	
	public static String implode(String[] toImplode) {
        return implode(0, toImplode);
    }
	
	public static String implode(int startIndex, String[] toImplode) {
        return implode(startIndex, toImplode, " ");
    }
	
	 public static String implode(int startIndex, String[] toImplode, String spacer) {
	        StringBuilder ret = new StringBuilder();

	        for (int i = startIndex ; i < toImplode.length ; i++) {
	            if (toImplode[i] != null) {
	                ret.append(toImplode[i]);
	            }
	            if (i < toImplode.length - 1) {
	                ret.append(spacer);
	            }
	        }

	        return color(ret.toString().trim());
	    }
}