package com.matt.forgehax.mods.infodisplay;

import com.matt.forgehax.util.mod.Category;
import com.matt.forgehax.util.mod.ToggleMod;
import com.matt.forgehax.util.mod.loader.RegisterMod;

@RegisterMod
public class Saturation extends ToggleMod {

  public Saturation() {
    super(Category.GUI, "Saturation", true, "Shows your saturation level");
  }

  @Override
  public boolean isInfoDisplayElement() {
    return true;
  }

  public String getInfoDisplayText() {
    return "Saturation: " + String.format("%.1f", MC.player.getFoodStats().getSaturationLevel());
  }
}
