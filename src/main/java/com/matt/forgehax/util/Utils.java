package com.matt.forgehax.util;

import com.google.common.collect.Lists;
import com.matt.forgehax.ForgeHaxBase;
import com.matt.forgehax.util.entity.EntityUtils;
import com.matt.forgehax.util.math.Angle;
import com.matt.forgehax.util.math.VectorUtils;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.util.math.Vec3d;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils extends ForgeHaxBase {
    public static final List<Packet> OUTGOING_PACKET_IGNORE_LIST = Collections.synchronizedList(Lists.<Packet>newArrayList());

    public static int toRGBA(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + (b << 0) + (a << 24);
    }

    public static int toRGBA(float r, float g, float b, float a) {
        return toRGBA((int) (r * 255.f), (int) (g * 255.f), (int) (b * 255.f), (int) (a * 255.f));
    }

    public static <E extends Enum<?>> String[] toArray(E[] o) {
        String[] output = new String[o.length];
        for(int i = 0; i < output.length; i++)
            output[i] = o[i].name();
        return output;
    }

    public static UUID stringToUUID(String uuid) {
        if(uuid.contains("-")) {
            // if it contains the hyphen we don't have to manually put them in
            return UUID.fromString(uuid);
        } else {
            // otherwise we have to put
            Pattern pattern = Pattern.compile("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})");
            Matcher matcher = pattern.matcher(uuid);
            return UUID.fromString(matcher.replaceAll("$1-$2-$3-$4-$5"));
        }
    }

    public static double normalizeAngle(double angle) {
        while (angle <= -180) angle += 360;
        while (angle > 180) angle -= 360;
        return angle;
    }

    public static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public static Angle getLookAtAngles(Vec3d startPos, Vec3d endPos) {
        return VectorUtils.vectorAngle(endPos.subtract(startPos)).normalize();
    }
    public static Angle getLookAtAngles(Vec3d endPos) {
        return getLookAtAngles(EntityUtils.getEyePos(MC.thePlayer), endPos);
    }
    public static Angle getLookAtAngles(Entity entity) {
        return getLookAtAngles(EntityUtils.getOBBCenter(entity));
    }

    public static double scale(double x, double from_min, double from_max, double to_min, double to_max) {
        return to_min + (to_max - to_min) * ((x - from_min) / (from_max - from_min));
    }

    public static class Colors {
        public final static int WHITE           = Utils.toRGBA(255,     255,    255,    255);
        public final static int BLACK           = Utils.toRGBA(0,       0,      0,      255);
        public final static int RED             = Utils.toRGBA(255,     0,      0,      255);
        public final static int GREEN           = Utils.toRGBA(0,       255,    0,      255);
        public final static int BLUE            = Utils.toRGBA(0,       0,      255,    255);
        public final static int ORANGE          = Utils.toRGBA(255,     128,    0,      255);
        public final static int PURPLE          = Utils.toRGBA(163,     73,     163,    255);
        public final static int GRAY            = Utils.toRGBA(127,     127,    127,    255);
        public final static int DARK_RED        = Utils.toRGBA(64,      0,      0,      255);
        public final static int YELLOW          = Utils.toRGBA(255,     255,    0,      255);
    }
}