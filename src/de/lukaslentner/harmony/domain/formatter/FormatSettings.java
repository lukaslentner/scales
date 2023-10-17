package de.lukaslentner.harmony.domain.formatter;

import de.lukaslentner.harmony.domain.formatter.xmlLoader.DtoLanguageCode;

public enum FormatSettings {
  
  INSTANCE;
  
  public DtoLanguageCode LANGUAGE = DtoLanguageCode.EN;
  
  public CentFormat CENT_FORMAT = CentFormat.HECTO_CENT;
  
}
