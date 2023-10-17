package de.lukaslentner.harmony.domain.model.pitch;

public abstract class StandardPitches {

  public static final Pitch ZERO = new Pitch(0L);
  
  public static final Pitch ISO16 = Pitch.constructByHz(440);
  
  public static final Pitch MODERN = Pitch.constructByHz(440);
  
  public static final Pitch CLASSICAL = Pitch.constructByHz(430);
  
  public static final Pitch BAROQUE = Pitch.constructByHz(415);
  
  public static final Pitch CHORTON = Pitch.constructByHz(466);
  
}
