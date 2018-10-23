/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn;

/**
 *
 * @author sdx
 */
public class Icons {
    public static String getEnteredIcon(String stIcon) {
        stIcon = stIcon.substring(stIcon.indexOf("/icons"));
        return stIcon.replaceAll(".png", "_1.png");
    }

    public static String getEnteredIconIfActive(String stIcon) {
        stIcon = stIcon.substring(stIcon.indexOf("/icons"));
//        if(GV.licence()>0)
//            return stIcon.replaceAll(".png", "_1.png");
//        else
//            return stIcon.replaceAll(".png", "_2.png");
        return stIcon.replaceAll(".png", "_1.png");//borrar
    }

    public static String getExitedIcon(String img) {
        img = img.substring(img.indexOf("/icons"));
        return img.replaceAll("_1.png", ".png").replaceAll("_2.png", ".png");
    }

    public static String getEnteredIconIfConnected(String stIcon) {
        stIcon = stIcon.substring(stIcon.indexOf("/icons"));
        if(GV.isOnline())
            return stIcon.replaceAll(".png", "_1.png");
        else
            return stIcon.replaceAll(".png", "_2.png");
    }
}
