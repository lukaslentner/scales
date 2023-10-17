package de.lukaslentner.harmony.domain.formatter;

import java.util.Optional;

import de.lukaslentner.harmony.domain.util.Math2;

public enum CentFormat {
  
  MILLI_CENT(1L, Optional.of("mc")),
  CENT(Math2.MILLE, Optional.of("c")),
  HECTO_CENT(Math2.HUNDRED_MILLE, Optional.empty());
  
  private final Long divisor;
  
  private final Optional<String> unitSign;
  
  private CentFormat(final Long divisor, final Optional<String> unitSign) {
    this.divisor = divisor;
    this.unitSign = unitSign;
  }
  
  public Long convert(final Long value) {
    return value / this.divisor;
  }
  
  public Optional<String> getUnitSign() {
    return this.unitSign;
  }
  
}
