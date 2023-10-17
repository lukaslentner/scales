package de.lukaslentner.harmony.domain.formatter.xmlLoader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "intervalNames")
public class DtoIntervalNames {
  
  @XmlElement(name = "translation")
  public List<DtoTranslation> translations;
  
  @XmlTransient
  public Map<DtoLanguageCode, DtoTranslation> translationsMap;
  
  public void postProcess() {
    this.translationsMap = this.translations.stream().collect(Collectors.toMap(o -> o.languageCode, o -> o));
    this.translations.stream().forEach(o -> o.postProcess());
  }
  
}
