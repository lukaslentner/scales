package de.lukaslentner.harmony.domain.formatter;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;

import de.lukaslentner.harmony.domain.formatter.xmlLoader.DtoFurtherName;
import de.lukaslentner.harmony.domain.formatter.xmlLoader.DtoIntervalName;
import de.lukaslentner.harmony.domain.formatter.xmlLoader.DtoIntervalNames;
import de.lukaslentner.harmony.domain.formatter.xmlLoader.DtoLanguageCode;
import de.lukaslentner.harmony.domain.formatter.xmlLoader.DtoTranslation;
import de.lukaslentner.harmony.domain.model.pitchInterval.PitchInterval;

public enum Formatter {
  
  INSTANCE;
  
  private DtoIntervalNames intervalNames;
  
  private Formatter() {
    try {
      final Class<?>[] dtoClasses =
        {
          DtoIntervalNames.class,
          DtoLanguageCode.class,
          DtoTranslation.class,
          DtoIntervalName.class,
          DtoFurtherName.class };
      final JAXBContext ctx = JAXBContext.newInstance(dtoClasses);
      final InputStream stream = Formatter.class.getResourceAsStream("IntervalNames.xml");
      this.intervalNames = (DtoIntervalNames) ctx.createUnmarshaller().unmarshal(stream);
      this.intervalNames.postProcess();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public String centWithUnit(final Long value) {
    return FormatSettings.INSTANCE.CENT_FORMAT.convert(value)
      + FormatSettings.INSTANCE.CENT_FORMAT.getUnitSign().map(o -> " " + o).orElse("");
  }
  
  public String intervalCommonName(final PitchInterval pitchInterval) {
    return this.intervalNames.translationsMap.get(FormatSettings.INSTANCE.LANGUAGE).intervalNamesMap
      .get(pitchInterval.getValue()).commonName;
  }
  
}
